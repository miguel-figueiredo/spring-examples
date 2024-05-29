package com.example.configuration.validation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class ConfigurationController {

    private final ValidatedConfiguration configuration;

    public ConfigurationController(ValidatedConfiguration configuration) {
        this.configuration = configuration;
    }

    @GetMapping
    public String get() {
        return "Hello World: " + configuration.min();
    }
}
