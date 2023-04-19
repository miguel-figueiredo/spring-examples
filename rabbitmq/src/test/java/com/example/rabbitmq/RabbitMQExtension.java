package com.example.rabbitmq;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.RabbitMQContainer;

public class RabbitMQExtension implements BeforeAllCallback {

    private static RabbitMQContainer container;

    @Override
    public void beforeAll(final ExtensionContext extensionContext) throws Exception {
        if(container == null) {
            container = new RabbitMQContainer();
            container.start();

            System.setProperty("spring.rabbitmq.port", container.getAmqpPort().toString());
            System.setProperty("spring.rabbitmq.username", container.getAdminUsername());
            System.setProperty("spring.rabbitmq.password", container.getAdminPassword());
        }
    }
}
