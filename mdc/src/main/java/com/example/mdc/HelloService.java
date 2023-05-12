package com.example.mdc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloService {

    public String execute() {
        log.info("Executing");
        return "Hello world!";
    }
}
