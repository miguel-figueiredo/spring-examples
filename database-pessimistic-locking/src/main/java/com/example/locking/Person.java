package com.example.locking;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public Person() {
    }

    public Person(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
