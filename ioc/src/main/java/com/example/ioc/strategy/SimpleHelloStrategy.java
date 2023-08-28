package com.example.ioc.strategy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "hello.strategy", havingValue = "simple", matchIfMissing = true)
public class SimpleHelloStrategy implements HelloStrategy {
    @Override
    public String hello() {
        return "Hello!";
    }
}
