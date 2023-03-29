package com.example;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FishApiClient {

    RestTemplate restTemplate = new RestTemplate();

    String get(String id) {
        return restTemplate.getForEntity("https://api.gbif.org/v1/species/" + id, String.class)
                        .getBody();
    }

}
