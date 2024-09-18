package com.example.locking;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@EqualsAndHashCode
@ToString
public class Person {
    @Id
    @GeneratedValue
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("addresses")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses;

    public Person() {
    }

    public Person(final String name, final List<Address> addresses) {
        this.name = name;
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
