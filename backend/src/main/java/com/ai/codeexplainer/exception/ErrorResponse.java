package com.ai.codeexplainer.exception;

public class ErrorResponse {
    String message;
    String status;

    public ErrorResponse(){}

    public ErrorResponse(String message, String status){
        this.message =message;
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
