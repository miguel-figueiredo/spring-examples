package com.example.postgresha;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Bird {

    @Id
    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;

    protected Bird() {
        // Required by JPA
    }
}
