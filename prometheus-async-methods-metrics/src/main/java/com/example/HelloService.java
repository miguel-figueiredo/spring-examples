package com.example;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloService {

    @SneakyThrows
    public void execute() {
        Thread.sleep(5000);
        log.info("Executing async task");
    }
}
