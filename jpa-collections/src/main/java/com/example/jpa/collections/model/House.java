package com.example.jpa.collections.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;
import java.util.UUID;

@Entity
public class House {

    @Id
    private UUID id;

    @OneToMany
    // Avoids an association table
    @JoinColumn(name="house_id")
    // Allows for persisting doors when persisting the house
    @Cascade(CascadeType.ALL)
    private List<Door> doors;

    public House() {
    }

    public House(final UUID id, final List<Door> doors) {
        this.id = id;
        this.doors = doors;
    }
}
