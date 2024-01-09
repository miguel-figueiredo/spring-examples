package com.example.jpa.collections.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Door {

    @Id
    private UUID id;

    public Door() {
    }

    public Door(final UUID id) {
        this.id = id;
    }
}
