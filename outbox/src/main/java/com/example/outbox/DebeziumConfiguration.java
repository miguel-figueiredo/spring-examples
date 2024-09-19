package com.example.outbox;

import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import org.apache.kafka.connect.source.SourceRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
public class DebeziumConfiguration {

    Logger log = LoggerFactory.getLogger(DebeziumConfiguration.class);

    @Bean
    public io.debezium.config.Configuration customerConnector() throws IOException {
        var offsetStorageTempFile = File.createTempFile("offsets_", ".dat");
        return io.debezium.config.Configuration.create()
                .with("name", "customer-postres-connector")
                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                .with("database.hostname", "localhost")
                .with("database.port", "5432")
                .with("database.user", "postgres")
                .with("database.password", "postgres")
                .with("database.dbname", "persondb")
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
