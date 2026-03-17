package com.ai.codeexplainer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CodeController {

    @PostMapping("/explain")
    public String explainCode(@RequestBody Map<String, String> body){
        String code = body.get("code");
        return "Recieved"+ code;
    }
}
