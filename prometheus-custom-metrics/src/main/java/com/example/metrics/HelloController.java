package com.example.metrics;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    MeterRegistry meterRegistry;

    @GetMapping
    @Timed("hello_time")
    public String get() {
        new HelloLogic(meterRegistry).execute();
        return "Hello, world!";
    }
}
