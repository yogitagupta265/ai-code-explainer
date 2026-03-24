package com.ai.codeexplainer.exception;

import com.ai.codeexplainer.dto.APIResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public APIResponse<?> handleException(Exception e){
        return new APIResponse<>("FAILED",null, e.getMessage());
    }
}
