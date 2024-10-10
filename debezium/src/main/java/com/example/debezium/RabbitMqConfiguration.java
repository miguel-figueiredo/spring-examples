package com.example.debezium;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Bean
    DirectExchange personExchange() {
        final DirectExchange exchange = new DirectExchange("person.exchange");
        exchange.setShouldDeclare(true);
        return exchange;
    }

    @Bean
    Queue personQueue() {
        final Queue queue = QueueBuilder.durable("person.queue")
                .build();
        queue.setShouldDeclare(true);
        return queue;
    }

    @Bean
    Binding personBinding(DirectExchange personExchange, Queue personQueue) {
        final Binding binding = BindingBuilder.bind(personQueue)
                .to(personExchange)
                .with("person.routingKey");
        binding.setShouldDeclare(true);
        return binding;
    }
}
