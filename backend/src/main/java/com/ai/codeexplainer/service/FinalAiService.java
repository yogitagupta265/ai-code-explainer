package com.ai.codeexplainer.service;

import com.ai.codeexplainer.enums.Provider;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class FinalAiService {

    @Value("${hf.api.key}")
    private  String API_KEY;
    private final WebClient publicWebClient;
    private final WebClient hfWebClient;

    private static final Logger log = LoggerFactory.getLogger(FinalAiService.class);
    //this is in memory cache
    private final Map<String, String> cache = new HashMap<>();

    public FinalAiService(){
        // public api client
        this.publicWebClient = WebClient.builder().baseUrl("https://api.adviceslip.com").build();
        // genai api client
        this.hfWebClient = WebClient.builder().baseUrl("https://router.huggingface.co").build();
    }

    public String explainCode(String code , String providerStr){
        String cacheKey = providerStr+"::"+code;
        log.info("Received explain request");
        code = code.trim();
        Provider provider = getProvider(providerStr);

        log.info("Explain Request received , provider : {}", provider);
        if(cache.containsKey(cacheKey)){
            log.info("Returning response from CACHE");
            return cache.get(cacheKey);
        }
        String result= switch (provider) {
            case PUBLIC -> {
                log.info("Using PUBLIC provider");
                yield callPublic(code);
            }
            case HF -> {
                log.info("Using HUGGINGFACE provider");
                yield callHF(code);
            }
            case MOCK -> {
                log.info("Using MOCK provider");
                yield mock(code);
            }
            //this can be removed as Provider has enum values and Java already guarantees only these values
           /* default -> {
                log.error("Invalid provider : {}", provider);
                throw new RuntimeException("Invalid provider : " + provider);
            }*/
        };

        cache.put(cacheKey,result);
        return result;
    }

/*mock code*/
    private String mock(String code){
        return "Mock explanation for : "+ code;
    }

    /*public code*/
    private String callPublic(String code){
        return publicWebClient.get().uri("/advice").retrieve().bodyToMono(String.class).block();
    }

    /*hugging face code*/
    private String callHF(String code){
        try {
            Map<String, Object> reqBody = Map.of(
                    "model","meta-llama/Meta-Llama-3-8B-Instruct",
                    "messages", new Object[]{
                            Map.of(
                                    "role", "user",
                                    "content", "Expalin this code: " + code
                            )
                    }
            );

            // returning full json
/*
            return hfWebClient.post().uri("/v1/chat/completions")
                    .header("Authorization", "Bearer "+ API_KEY)
                    .header("Content-Type", "application/json")
                    .bodyValue(reqBody).retrieve().bodyToMono(String.class).block();
*/
            // parsing the json
            JsonNode response = hfWebClient.post().uri("/v1/chat/completions")
                    .header("Authorization", "Bearer "+ API_KEY)
                    .header("Content-Type", "application/json")
                    .bodyValue(reqBody).retrieve()
                    .bodyToMono(JsonNode.class).
                    timeout(Duration.ofSeconds(5)) //Added time out if the hF id slowand hangs -> fails if it takes > 5sec
                    //retry 2 times -> improves success rate & handle temp network issue
                    /*.retry(2) */  // adding exponential backoff -> safer, industry standard and used in microservices
                    .retryWhen(reactor.util.retry.Retry.backoff(2,Duration.ofSeconds(1)))
                    // on network errors
/*
                    .retryWhen(Retry.backoff(2, Duration.ofSeconds(1)).filter(e -> e instanceof RuntimeException))
*/
                    .block();

            return response.get("choices").get(0).get("message").get("content").asText();

        }catch (Exception ex){
            // good for debugging but doesn't look great at user experience
           /* log.error("HF API Error: ",ex);
            throw new RuntimeException("Failed to get the response from HuggingFace");
*/
            //Want fallback to mock if HuggingFaces doesn't works
            log.error("HF Api fails, switching to mock",ex);
            return mock(code);
        }
    }

    private Provider getProvider(String provider){
        if(provider == null || provider.isBlank()){
            return Provider.MOCK;
        }
        try {
            return Provider.valueOf(provider.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid provider :"+provider);
        }
    }
}
