package com.example.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Non spring bean with metrics.
 * The micrometer annotations do not work, because they require spring proxies.
 */
public class HelloLogic {

    private static final Logger log = LoggerFactory.getLogger(HelloLogic.class);

    private final Timer timer;

    public HelloLogic(final MeterRegistry meterRegistry) {
        this.timer = Timer.builder("hello_logic").register(meterRegistry);
    }

    public void execute() {
        timer.record(() -> log.info("Executing"));

    }
}
