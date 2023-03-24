package com.test.processing;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;
import java.util.function.Function;

@Service
@Slf4j
public class QueueStream {

    private Processor processor;

    public QueueStream(Processor processor) {
        this.processor = processor;
    }

    @Bean
    public Function<Flux<String>, Flux<String>> consumer() {
        return flux -> flux
                .doOnNext(message -> log.info("Received: {}", message))
                .flatMap(processor::execute)
                .doOnNext(message -> log.info("Sending: {}", message))
                .subscribeOn(Schedulers.boundedElastic()).share();
    }


}
