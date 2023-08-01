package com.example.restclienttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private EchoClient echoClient;

    @Autowired
    public HelloController(EchoClient echoClient) {
        this.echoClient = echoClient;
    }

    @GetMapping("{name}")
    public String hello(@PathVariable("name") String name) {
        return "Hello, " + echoClient.echo(name);
    }
}
