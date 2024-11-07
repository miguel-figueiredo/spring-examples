package com.example.validation;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final Bye bye;

    public HelloController(Bye bye) {
        this.bye = bye;
    }

    @GetMapping("/hello")
    public String hello(@NotEmpty String name) {
        return "Hello, " + name + ".\n";
    }

    @GetMapping("/bye")
    public String bye(String name) {
        return bye.bye(name);
    }
}
