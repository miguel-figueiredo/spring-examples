package com.example.cdi;


import jakarta.inject.Named;

@Named
public class GreetingProvider {

    public String getGreeting() {
        return "Hello, World!";
    }
}
