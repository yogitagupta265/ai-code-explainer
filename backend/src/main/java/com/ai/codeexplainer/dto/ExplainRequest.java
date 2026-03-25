package com.ai.codeexplainer.dto;
import jakarta.validation.constraints.NotBlank;

public class ExplainRequest {
    @NotBlank(message = "Code cannot be empty")
    private String code;

    public ExplainRequest(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
