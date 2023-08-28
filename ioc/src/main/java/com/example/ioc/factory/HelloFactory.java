package com.example.ioc.factory;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HelloFactory {

    private Map<String, NamedHello> hellos;

    public HelloFactory(Map<String, NamedHello> hellos) {
        this.hellos = hellos;
    }

    public NamedHello get(String name) {
        return hellos.getOrDefault(name, () -> "not found");
    }
}
