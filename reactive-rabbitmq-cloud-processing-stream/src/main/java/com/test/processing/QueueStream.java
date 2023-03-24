package com.test.processing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Function;

@Service
@Slf4j
public class QueueStream {

    @Bean
    public Function<Flux<String>, Flux<String>> consumer() {
        return flux -> flux
                .doOnEach(consumer -> log.info("Receiving: {}", consumer.get()))
                .delayElements(Duration.ofMillis(1500))
                .subscribeOn(Schedulers.boundedElastic()).share();
    }
}
