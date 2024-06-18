package com.example;

import io.micrometer.core.instrument.LongTaskTimer;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    HelloService helloService;

    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    LongTaskTimer timer;

    public HelloController(
            final HelloService helloService,
            final ThreadPoolTaskExecutor threadPoolTaskExecutor,
            final MeterRegistry meterRegistry) {
        this.helloService = helloService;
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.timer = LongTaskTimer
                .builder("executor_task_hello")
                .register(meterRegistry);
    }

    @GetMapping
    public String hello() {
        final LongTaskTimer.Sample taskId = timer.start();
        threadPoolTaskExecutor.submitCompletable(() -> helloService.execute())
                .thenAccept(unused -> taskId.stop());
        return "Hello World";
    }
}
