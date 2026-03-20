package com.ai.codeexplainer.controller;

import com.ai.codeexplainer.service.AIService;
import com.ai.codeexplainer.service.FinalAiService;
import com.ai.codeexplainer.service.PublicApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class CodeController {

    @Autowired
    private AIService aiService;
    @Autowired
    private PublicApiService publicApiService;
    @Autowired
    private FinalAiService finalAiService;

    public CodeController(AIService aiService) {
        this.aiService = aiService;
    }
    @PostMapping("/explain")
    public String explainCode(@RequestBody String code){
//        String code = body.get("code");
        return aiService.explainCode(code);
    }

    @PostMapping("/testPublic")
    public String testPublic(){
        return publicApiService.getAdvice();
    }

    @PostMapping("/explainCode")
    public String explainCode(@RequestBody Map<String, String> body, @RequestParam(required = false) String provider){
        return finalAiService.explainCode(body.get("code"), provider);
    }
}
