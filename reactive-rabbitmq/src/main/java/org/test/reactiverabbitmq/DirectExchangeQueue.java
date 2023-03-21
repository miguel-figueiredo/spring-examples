package org.test.reactiverabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Delivery;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.Receiver;

import static org.springframework.amqp.core.Binding.DestinationType.EXCHANGE;

@Configuration
public class DirectExchangeQueue {

    static final String EXCHANGE = "direct.exchange";
    static final String QUEUE = "direct.exchange.queue";

    @Autowired
    Mono<Connection> connectionMono;
    @Autowired
    AmqpAdmin amqpAdmin;

    @PostConstruct
    public void init() {
        Exchange ex = ExchangeBuilder.directExchange(EXCHANGE)
                .durable(true)
                .build();
        amqpAdmin.declareExchange(ex);
        Queue q = QueueBuilder.durable(
                        QUEUE)
                .build();
        amqpAdmin.declareQueue(q);
        Binding b = BindingBuilder.bind(q)
                .to(ex)
                .with(QUEUE)
                .noargs();
        amqpAdmin.declareBinding(b);
    }

    @PreDestroy
    public void close() throws Exception {
        connectionMono.block().close();
    }
}
