package com.ai.codeexplainer.exception;

import com.ai.codeexplainer.dto.APIResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public APIResponse<?> handleGenericException(Exception e){
        return new APIResponse<>("FAILED",null, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public APIResponse<?> handleValidationException(MethodArgumentNotValidException ex){
        String msg = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        return new APIResponse<>("FAILED", null, msg);
    }
}
