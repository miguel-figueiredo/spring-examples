package com.example.ioc.strategy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "hello.strategy", havingValue = "empty", matchIfMissing = false)
public class EmptyHelloStrategy implements HelloStrategy {
    @Override
    public String hello() {
        return "";
    }
}
