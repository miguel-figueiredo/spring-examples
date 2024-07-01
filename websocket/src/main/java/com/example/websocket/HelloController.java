package com.example.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class HelloController {

    @MessageMapping("/echo")
    @SendTo("/topic/events")
    public String hello(@Payload String input) {
        log.info("Received: {}", input);
        return input;
    }
}
