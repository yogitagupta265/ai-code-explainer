package com.ai.codeexplainer.dto;

public class ExplainResponse {
    private String provider;
    private String result;

    public ExplainResponse(){}

    public ExplainResponse(String provider, String result){
        this.result = result;
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
