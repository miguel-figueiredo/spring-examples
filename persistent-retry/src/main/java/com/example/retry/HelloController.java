package com.example.retry;

import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private AtomicInteger counter = new AtomicInteger();

    @GetMapping
    @Retryable(stateful = true)
    public String get() {
        final int i = counter.incrementAndGet();
        if(i % 3 == 0) {
            return "Hello, world!";
        } else {
            throw new RuntimeException();
        }
    }
}
