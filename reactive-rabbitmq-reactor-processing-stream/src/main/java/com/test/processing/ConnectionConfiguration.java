package com.test.processing;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.rabbitmq.AcknowledgableDelivery;
import reactor.rabbitmq.ConsumeOptions;
import reactor.rabbitmq.RabbitFlux;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.ReceiverOptions;
import reactor.rabbitmq.Sender;
import reactor.rabbitmq.SenderOptions;

@Configuration
public class ConnectionConfiguration {

    @Bean
    public Receiver receiver() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.useNio();

        ReceiverOptions receiverOptions =  new ReceiverOptions()
                .connectionFactory(connectionFactory)
                .connectionSubscriptionScheduler(Schedulers.boundedElastic());

        return RabbitFlux.createReceiver(receiverOptions);
    }

    @Bean
    public Sender sender() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.useNio();

        SenderOptions senderOptions =  new SenderOptions()
                .connectionFactory(connectionFactory)
                .connectionSubscriptionScheduler(Schedulers.boundedElastic());

        return RabbitFlux.createSender(senderOptions);
    }
}
