package com.ai.codeexplainer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class FinalAiService {

    private final String API_KEY = "YOUR_TOKEN";
    private final WebClient publicWebClient;
    private final WebClient hfWebClient;

    public FinalAiService(){
        // public api client
        this.publicWebClient = WebClient.builder().baseUrl("https://api.adviceslip.com").build();
        // genai api client
        this.hfWebClient = WebClient.builder().baseUrl("https://router.huggingface.co").build();
    }

    public String explainCode(String code , String provider){
        if(provider== null){
            provider = "mock";
        }

        switch (provider.toLowerCase()){
            case "public":
                return callPublic(code);

            case "hf":
                return callHF(code);

            default:
                return mock(code);
        }
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
                                    "content", "Expalin this code :" + code
                            )
                    }
            );

            return hfWebClient.post().uri("/v1/chat/completions")
                    .header("Authorization", "Bearer "+ API_KEY)
                    .header("Content-Type", "application/json")
                    .bodyValue(reqBody).retrieve().bodyToMono(String.class).block();

        }catch (Exception ex){
            return "HuggingFace error -> fallback mock";
        }
    }
}
