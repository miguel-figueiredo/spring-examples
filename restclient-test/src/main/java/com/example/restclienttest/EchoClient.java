package com.example.restclienttest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.Map;

@Service
public class EchoClient {

    private final RestTemplate restTemplate;

    public EchoClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String echo(String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("cookie", "cookie-value");

        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().addAll(httpHeaders);
            return execution.execute(request, body);
        });

        final EchoResponse response = restTemplate.getForObject("https://postman-echo.com/get?name=" + name, EchoResponse.class);
        return response != null ? response.getName() : "John Doe";
    }

    static class EchoResponse implements Serializable {

        @JsonProperty("args")
        Map<String, String> args;

        public String getName() {
            return args.get("name");
        }

        // Used by the RestTemplate
        public void setArgs(final Map<String, String> args) {
            this.args = args;
        }
    }
}
