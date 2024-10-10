package com.example.debezium;

import io.debezium.config.Configuration;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class DebeziumListener {

    Logger log = LoggerFactory.getLogger(DebeziumListener.class);

    private final Executor executor = Executors.newSingleThreadExecutor();
    private final DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine;
    private final RabbitMqMessageProducer messageProducer;

    public DebeziumListener(Configuration personConnectorConfiguration, RabbitMqMessageProducer messageProducer) {
        this.debeziumEngine = DebeziumEngine.create(ChangeEventFormat.of(Connect.class))
                .using(personConnectorConfiguration.asProperties())
                .notifying(this::handleChangeEvent)
                .build();
        this.messageProducer = messageProducer;
    }

    private void handleChangeEvent(RecordChangeEvent<SourceRecord> sourceRecordRecordChangeEvent) {
        var sourceRecord = sourceRecordRecordChangeEvent.record();
        log.info("Key = {}, Value = {}", sourceRecord.key(), sourceRecord.value());
        var sourceRecordChangeValue= (Struct) sourceRecord.value();
        log.info("SourceRecordChangeValue = '{}'", sourceRecordChangeValue);
        final String name = sourceRecordChangeValue.getStruct("after").getString("name");
        messageProducer.send(name);
    }

    @PostConstruct
    private void start() {
        this.executor.execute(debeziumEngine);
    }

    @PreDestroy
    private void stop() throws IOException {
        if (Objects.nonNull(this.debeziumEngine)) {
            this.debeziumEngine.close();
        }
    }
}
