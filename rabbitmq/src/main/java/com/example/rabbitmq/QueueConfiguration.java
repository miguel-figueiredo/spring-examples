package com.example.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

    @Value("${application.queue}")
    String queue;

    @Bean
    public Queue myQueue() {
        return new Queue(queue, false);
    }
}
