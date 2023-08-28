package com.example.ioc.multiple;

import org.springframework.stereotype.Component;

@Component
public class HelloNameB implements HelloName {
    @Override
    public String name() {
        return "B";
    }
}
