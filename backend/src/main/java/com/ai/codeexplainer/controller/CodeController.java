package com.ai.codeexplainer.controller;

import com.ai.codeexplainer.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CodeController {

    @Autowired
    private AIService aiService;

    @PostMapping("/explain")
    public String explainCode(@RequestBody String code){

        return aiService.explainCode(code);
    }
}
