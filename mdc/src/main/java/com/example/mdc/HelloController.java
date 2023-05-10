package com.example.mdc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {

    private HelloService service;

    public HelloController(HelloService service) {
        this.service = service;
    }

    @GetMapping
    public String hello() {
        log.info("Received request");
        return service.execute();
    }
}
