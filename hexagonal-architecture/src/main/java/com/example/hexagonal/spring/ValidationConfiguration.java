package com.example.hexagonal.spring;

import com.example.hexagonal.util.Validation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Component
public class ValidationConfiguration {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        final MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidatedAnnotationType(Validation.class);
        return processor;
    }
}
