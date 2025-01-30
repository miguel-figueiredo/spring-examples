package com.example.jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(path = "/private")
    public String privateHello() {
        return "Private: Hello World";
    }

    @GetMapping(path = "/public")
    public String publicHello() {
        return "Public: Hello World";
    }
}
