package com.example.ioc.factory;

import org.springframework.stereotype.Component;

@Component("B")
public class NamedHelloB implements NamedHello {
    @Override
    public String name() {
        return "B";
    }
}
