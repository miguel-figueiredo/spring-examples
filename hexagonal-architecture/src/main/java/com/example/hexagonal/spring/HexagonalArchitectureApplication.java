package com.example.hexagonal.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// Required because the classes are in different root package.
@ComponentScan({"com.example.hexagonal"})
public class HexagonalArchitectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(HexagonalArchitectureApplication.class, args);
    }
}
