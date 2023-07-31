package com.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @RabbitListener(id="listener", queues = "${application.queue}")
    public void listen(MyMessage message) {
        log.info("Message read: {}", message);
    }
}
