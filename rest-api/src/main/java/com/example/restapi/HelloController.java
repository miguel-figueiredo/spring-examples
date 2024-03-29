package com.example.restapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("hello")
public class HelloController {

    private HelloConfiguration configuration;

    @Autowired
    public HelloController(final HelloConfiguration helloConfiguration) {
        this.configuration = helloConfiguration;
    }

    @GetMapping("/{name}")
    public String greeting(@PathVariable String name) {
        return "Hello " + name + ". My name is " + configuration.getApplicationName() + ".";
    }

    @PostMapping
    public void post(@RequestBody String payload) {
        log.info("Received payload: {}", payload);
    }
}
