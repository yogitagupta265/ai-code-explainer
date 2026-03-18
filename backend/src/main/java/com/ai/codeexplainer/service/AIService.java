package com.ai.codeexplainer.service;

import org.springframework.stereotype.Service;

@Service
public class AIService {
    public String explainCode(String code){
        return "Explanation for code : "+code;
    }

}
