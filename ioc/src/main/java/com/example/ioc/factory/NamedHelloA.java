package com.example.ioc.factory;

import org.springframework.stereotype.Component;

@Component("A")
public class NamedHelloA implements NamedHello {
    @Override
    public String name() {
        return "A";
    }
}
