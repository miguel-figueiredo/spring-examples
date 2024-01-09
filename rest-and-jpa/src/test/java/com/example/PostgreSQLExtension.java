package com.example;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLExtension implements BeforeAllCallback {

    private static PostgreSQLContainer<?> container;

    @Override
    public void beforeAll(final ExtensionContext extensionContext) {
        if(container == null) {
            container = new PostgreSQLContainer<>("postgres:13.10")
                        .withUsername("sa")
                        .withPassword("sa");
            container.start();

            System.setProperty("spring.datasource.url", container.getJdbcUrl() + "ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
            System.setProperty("spring.datasource.username", container.getUsername());
            System.setProperty("spring.datasource.password", container.getPassword());
            System.setProperty("spring.flyway.url", container.getJdbcUrl() + "ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
            System.setProperty("spring.flyway.user", container.getUsername());
            System.setProperty("spring.flyway.password", container.getPassword());
        }
    }
}
