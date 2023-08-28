package com.example.ioc.multiple;

import org.springframework.stereotype.Component;

@Component
public class HelloNameA implements HelloName {
    @Override
    public String name() {
        return "A";
    }
}
