package com.test.processing;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.AcknowledgableDelivery;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Receiver;
import reactor.rabbitmq.Sender;

import java.nio.charset.StandardCharsets;

import static com.test.processing.QueueConfiguration.EXCHANGE;
import static com.test.processing.QueueConfiguration.IN_QUEUE;
import static com.test.processing.QueueConfiguration.OUT_QUEUE;


@Service
@Slf4j
public class QueueStream {

    private Sender sender;
    private Receiver receiver;
    private Processor processor;

    @Autowired
    public QueueStream(Sender sender, Receiver receiver, Processor processor) {
        this.sender = sender;
        this.receiver = receiver;
        this.processor = processor;
    }

    @PostConstruct
    public void consume() {
        receiver.consumeManualAck(IN_QUEUE)
                .doOnNext(delivery -> log.info("Received: {}", getPayload(delivery)))
                .flatMap(delivery -> processor.execute(getPayload(delivery))
                        .thenReturn(delivery)
                )
                .flatMap(delivery -> sender.send(Mono.just(new OutboundMessage(EXCHANGE, OUT_QUEUE, delivery.getBody())))
                        .doOnSuccess(signalType -> ack(delivery)))
                .subscribe();
    }

    private void ack(final AcknowledgableDelivery delivery) {
        delivery.ack();
        log.info("Acked: {}", getPayload(delivery));
    }

    private String getPayload(AcknowledgableDelivery delivery) {
        return new String(delivery.getBody(), StandardCharsets.UTF_8);
    }


}
