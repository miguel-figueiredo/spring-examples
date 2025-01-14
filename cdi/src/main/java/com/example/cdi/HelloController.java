package com.example.cdi;

import jakarta.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private final GreetingProvider provider;

    @Inject
    public HelloController(GreetingProvider provider) {
        this.provider = provider;
    }

    @GetMapping
    public String hello() {
        return provider.getGreeting();
    }
}
