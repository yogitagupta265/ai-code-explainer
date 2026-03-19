package com.ai.codeexplainer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PublicApiService {

    private final WebClient webClient;

    public PublicApiService(){
        this.webClient = WebClient.builder().baseUrl("https://api.adviceslip.com").build();

    }

    public String getAdvice(){
        String response = webClient.get().uri("/advice").retrieve().bodyToMono(String.class).block();
        return  response;
    }
}
