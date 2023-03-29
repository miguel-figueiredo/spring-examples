package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FishApiClientConfiguration {

    @Bean
    WebClient create() {
        return WebClient.builder()
                .baseUrl("https://api.gbif.org/v1")
                .build();
    }

}
