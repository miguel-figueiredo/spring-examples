package com.test.processing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@Service
@Slf4j
public class Processor {

    private Random random = new Random();

    public Mono<String> execute(final String message) {
        return Mono.just(message.toUpperCase()).delayElement(Duration.ofMillis(random.nextInt(5000)))
                .doOnNext(s -> log.info("Processed: {}", message));
    }

}
