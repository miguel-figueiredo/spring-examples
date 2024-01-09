package com.example.jpa.collections.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

@Entity
public class House {

    @Id
    private UUID id;

    @OneToMany
    private List<Door> doors;

    public House() {
    }

    public House(final UUID id, final List<Door> doors) {
        this.id = id;
        this.doors = doors;
    }
}
