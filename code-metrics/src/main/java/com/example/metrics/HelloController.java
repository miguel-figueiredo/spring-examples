package com.example.metrics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Some javadoc to please checkstyle.
 */
@RestController
@RequestMapping("/hello")
public final class HelloController {

    @GetMapping
    public String hello() {
        return "Hello world!";
    }
}
