package com.example.restapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfiguration {

    @Value("${application.name}")
    private String applicationName;

    public String getApplicationName() {
        return applicationName;
    }
}
