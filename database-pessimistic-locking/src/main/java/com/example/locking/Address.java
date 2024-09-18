package com.example.locking;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@EqualsAndHashCode
@ToString
public class Address {
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public Address() {
    }

    public Address(final String name) {
        this.name = name;
    }
}
