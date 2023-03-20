package com.example.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
