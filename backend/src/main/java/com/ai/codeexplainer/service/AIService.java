package com.ai.codeexplainer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;


@Service
public class AIService {

    private final String OPENAI_API_KEY = "YOUR_TOKEN"; // Your OpenAI key
    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.openai.com/v1/chat/completions")
            .defaultHeader("Authorization", "Bearer " + OPENAI_API_KEY)
            .build();

    public String explainCode(String code){


        Map<String, Object> reqBody = Map.of(
                "model", "gpt-3.5-turbo",
                "messages", new Object[]{
                        Map.of("role", "user", "content", "Explain this code:\n" + code)
                },
                "temperature", 0.7
        );
        String response = webClient.post()
                .bodyValue(reqBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(map -> {
                    var choices = (Iterable<Map<String, Object>>) map.get("choices");
                    var first = choices.iterator().next();
                    var message = (Map<String, Object>) first.get("message");
                    return (String) message.get("content");
                })
                .block();

        return response;
    }

}
