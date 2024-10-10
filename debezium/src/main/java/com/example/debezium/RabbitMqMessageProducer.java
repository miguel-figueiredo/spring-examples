package com.example.debezium;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RabbitMqMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(
                "person.exchange",
                "person.routingKey",
                message);
    }
}
