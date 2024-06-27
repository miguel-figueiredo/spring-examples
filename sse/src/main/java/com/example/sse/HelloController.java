package com.example.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    private final HelloScheduler helloScheduler;

    public HelloController(final HelloScheduler helloScheduler) {
        this.helloScheduler = helloScheduler;
    }

    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter();
        helloScheduler.addEmitter(emitter);
        return emitter;
    }
}

