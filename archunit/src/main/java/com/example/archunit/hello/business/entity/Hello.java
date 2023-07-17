package com.example.archunit.hello.business.entity;

import com.example.archunit.hello.adapter.postgres.JpaHelloRepository;
import com.example.archunit.hello.business.port.in.CreateHello;
import com.example.archunit.hello.business.usecase.CreateHelloUseCase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Hello {
    @Id
    private UUID id;

    private String value;

    // Required by Hibernate
    protected Hello() {
    }

    public Hello(final UUID id, final String value) {
        this.id = id;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
