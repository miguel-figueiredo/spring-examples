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

import java.util.List;

@Configuration
public class DebeziumConfiguration {

    Logger log = LoggerFactory.getLogger(DebeziumConfiguration.class);

    @Bean
    public io.debezium.config.Configuration customerConnector() {
        return io.debezium.config.Configuration.create()
                .with("name", "customer-posgres-connector")
                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                .with("database.hostname", "localhost")
                .with("database.port", "5432")
                .with("database.user", "postgres")
                .with("database.password", "postgres")
                .with("database.dbname", "postgres")
                .with("database.include.list", "postgres")
                .with("include.schema.changes", "false")
                .build();
    }

    @Bean
    public DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumMessageProducer(
            io.debezium.config.Configuration configuration) {

        return DebeziumEngine.create(ChangeEventFormat.of(Connect.class))
                .using(configuration.asProperties())
                .notifying(this::handleEvent)
                .build();
    }

    private void handleEvent(
            List<RecordChangeEvent<SourceRecord>> recordChangeEvents,
            DebeziumEngine.RecordCommitter<RecordChangeEvent<SourceRecord>> recordChangeEventRecordCommitter) {
        log.info("Event");
    }
}
