package com.ai.codeexplainer.applicationstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ai.codeexplainer")
public class CodeExplainerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeExplainerApplication.class,args);
    }
}