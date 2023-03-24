package com.test.reactivecloudstream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class ReactiveCloudStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveCloudStreamApplication.class, args);
	}

	@Bean
	public Supplier<Flux<String>> stringSupplier() {
		return () -> Flux.fromStream(Stream.generate(() -> "Hello"))
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(value -> log.info("Sending: {}", value))
				.subscribeOn(Schedulers.boundedElastic()).share();
	}

	@Bean
	public Function<Flux<String>, Mono<Void>> stringConsumer() {
		return flux -> flux
				.doOnNext(value -> log.info("Receiving: {}", value))
				.then();
	}

}
