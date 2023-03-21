package org.test.reactiverabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Delivery;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.Receiver;

@Configuration
public class DefaultExchangeQueue {

    static final String QUEUE = "default.exchange.queue";

    @Autowired
    Mono<Connection> connectionMono;
    @Autowired
    AmqpAdmin amqpAdmin;

    @PostConstruct
    public void init() {
//        Exchange ex = ExchangeBuilder.directExchange(EXCHANGE)
//                .durable(true)
//                .build();
//        amqpAdmin.declareExchange(ex);
        Queue q = QueueBuilder.durable(
                        QUEUE)
                .build();
        amqpAdmin.declareQueue(q);
//        Binding b = BindingBuilder.bind(q)
//                .to(ex)
//                .with(QUEUE)
//                .noargs();
//        amqpAdmin.declareBinding(b);
    }

    @PreDestroy
    public void close() throws Exception {
        connectionMono.block().close();
    }

    @Bean
    Flux<Delivery> deliveryFlux(Receiver receiver) {
        return receiver.consumeNoAck(QUEUE);
    }
}
