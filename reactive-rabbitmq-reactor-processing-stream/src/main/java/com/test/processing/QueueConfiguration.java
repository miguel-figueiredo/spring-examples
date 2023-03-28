package com.test.processing;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QueueConfiguration {
    static final String EXCHANGE = "test-exchange";
    static final String IN_QUEUE = "in-queue";
    static final String OUT_QUEUE = "out-queue";

    @Autowired
    AmqpAdmin amqpAdmin;

    @PostConstruct
    void createQueues() {
        createQueue(EXCHANGE, IN_QUEUE);
        createQueue(EXCHANGE, OUT_QUEUE);
    }

    private void createQueue(final String exchange, final String queue) {
        Exchange ex = ExchangeBuilder.topicExchange(exchange)
                .durable(true)
                .build();
        amqpAdmin.declareExchange(ex);
        Queue q = QueueBuilder.durable(
                        queue)
                .build();
        amqpAdmin.declareQueue(q);
        Binding b = BindingBuilder.bind(q)
                .to(ex)
                .with(queue)
                .noargs();
        amqpAdmin.declareBinding(b);
    }
}
