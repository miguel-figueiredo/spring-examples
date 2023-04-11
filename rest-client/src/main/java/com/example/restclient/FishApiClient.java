package com.example.restclient;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class FishApiClient {

    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate;

    public FishApiClient() {
        restTemplate = new RestTemplate();
        retryTemplate = new RetryTemplate();
        retryTemplate.registerListener(new FishApiRetryListener());
    }

    @Cacheable("fishes")
    public String get(String id) {
        // Does not require the @EnableRetry annotation
        return retryTemplate.execute(context -> doGet(id));
    }

    private String doGet(final String id) {
        // Path variables with string concatenation
        return restTemplate.getForEntity("https://api.gbif.org/v1/species/" + id, String.class)
                .getBody();
    }

    @Cacheable("fishesNames")
    @Retryable
    public String getName(String id) {
        // Path variables with UriComponentsBuilder
        final String urlTemplate = UriComponentsBuilder.fromUriString("https://api.gbif.org/v1/species/")
                .pathSegment("{id}", "name")
                .encode()
                .toUriString();

        final Map<String, String> params = Map.of("id", id);

        return restTemplate.exchange(urlTemplate, HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), String.class, params)
                .getBody();
    }

}
