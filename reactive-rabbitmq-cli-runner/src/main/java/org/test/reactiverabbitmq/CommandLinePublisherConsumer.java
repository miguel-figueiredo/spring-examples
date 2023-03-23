package org.test.reactiverabbitmq;

import com.rabbitmq.client.Delivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
class CommandLinePublisherConsumer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootSample.class);

    final Sender sender;
    final Flux<Delivery> deliveryFlux;
    final AtomicBoolean latchCompleted = new AtomicBoolean(false);

    CommandLinePublisherConsumer(Sender sender, Flux<Delivery> deliveryFlux) {
        this.sender = sender;
        this.deliveryFlux = deliveryFlux;
    }

    @Override
    public void run(String... args) throws Exception {
        int messageCount = 10;
        CountDownLatch latch = new CountDownLatch(messageCount);
        deliveryFlux.subscribe(m -> {
            LOGGER.info("Received message {}", new String(m.getBody()));
            latch.countDown();
        });
        LOGGER.info("Sending messages...");
        sender.send(Flux.range(1, messageCount).map(i -> new OutboundMessage("", DefaultExchangeQueue.QUEUE, ("Message_" + i).getBytes())))
                .subscribe();
        latchCompleted.set(latch.await(5, TimeUnit.SECONDS));
    }
}