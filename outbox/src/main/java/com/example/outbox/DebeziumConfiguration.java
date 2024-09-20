package com.example.outbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class DebeziumConfiguration {

    @Value("${database.host}")
    private String databaseHost;

    @Value("${database.port}")
    private int databasePort;

    @Value("${database.name}")
    private String databaseName;

    @Value("${database.user}")
    private String databaseUser;

    @Value("${database.password}")
    private String databasePassword;

    @Bean
    public io.debezium.config.Configuration customerConnector() throws IOException {
        var offsetStorageTempFile = File.createTempFile("offsets_", ".dat");
        return io.debezium.config.Configuration.create()
                .with("name", "customer-postres-connector")
                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                .with("database.hostname", databaseHost)
                .with("database.port", databasePort)
                .with("database.user", databaseUser)
                .with("database.password", databasePassword)
                .with("database.dbname", databaseName)
                .with("table.include.list", "public.person")
                .with("include.schema.changes", "false")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", offsetStorageTempFile.getAbsolutePath())
                .with("offset.flush.interval.ms", "60000")
                .with("database.server.id", "10181")
                .with("database.server.name", "person-postgres-db-server")
                .with("database.history", "io.debezium.relational.history.MemoryDatabaseHistory")
                .with("publication.autocreate.mode", "filtered")
                .with("plugin.name", "pgoutput")
                .with("slot.name", "dbz_persondb_listener")
                .with("topic.prefix", "test")

                .build();
    }
}
