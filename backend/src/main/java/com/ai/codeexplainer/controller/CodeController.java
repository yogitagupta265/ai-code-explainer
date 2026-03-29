package com.ai.codeexplainer.controller;

import com.ai.codeexplainer.dto.APIResponse;
import com.ai.codeexplainer.dto.ExplainRequest;
import com.ai.codeexplainer.dto.ExplainResponse;
import com.ai.codeexplainer.enums.Provider;
import com.ai.codeexplainer.service.AIService;
import com.ai.codeexplainer.service.FinalAiService;
import com.ai.codeexplainer.service.PublicApiService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log =  LoggerFactory.getLogger(CodeController.class);
    //not needed since already using @Autowired fields
    /*public CodeController(AIService aiService) {
        this.aiService = aiService;
    }*/
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
    public APIResponse<ExplainResponse> explainCode(@Valid @RequestBody ExplainRequest request, @RequestParam(required = false) String provider){

        log.info("API explainCode called with provider : {}",provider);
        String result = finalAiService.explainCode(request.getCode(), provider);

        ExplainResponse response = new ExplainResponse(provider,result);
        return new APIResponse<>("SUCCESS",response,null);
    }
}
