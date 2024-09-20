package com.example.debezium;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class DebeziumConfiguration {

    @Value("${postgres.host}")
    private String postgresHost;

    @Value("${postgres.port}")
    private int postgresPort;

    @Value("${postgres.database}")
    private String postgresDatabase;

    @Value("${postgres.user}")
    private String postgresUser;

    @Value("${postgres.password}")
    private String postgresPassword;

    @Value("${redis.address}")
    private String redisAddress;

    @Bean
    public io.debezium.config.Configuration customerConnector() throws IOException {
        return io.debezium.config.Configuration.create()
                .with("name", "person-connector")
                .with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
                .with("database.hostname", postgresHost)
                .with("database.port", postgresPort)
                .with("database.user", postgresUser)
                .with("database.password", postgresPassword)
                .with("database.dbname", postgresDatabase)
                .with("database.server.id", "10181")
                .with("database.server.name", "person-postgres-db-server")
                .with("table.include.list", "public.person")
                .with("include.schema.changes", "false")
                .with("offset.storage", "io.debezium.storage.redis.offset.RedisOffsetBackingStore")
                .with("offset.flush.interval.ms", "60000")
                .with("offset.storage.redis.address", redisAddress)
                .with("publication.autocreate.mode", "filtered")
                .with("plugin.name", "pgoutput")
                .with("slot.name", "dbz_persondb_listener")
                .with("topic.prefix", "person-topic")
                .build();
    }
}
