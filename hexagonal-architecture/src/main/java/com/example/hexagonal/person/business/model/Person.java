package com.example.hexagonal.person.business.model;

import jakarta.validation.constraints.NotNull;
import lombok.With;

public record Person(
        @With
        Long id,
        @NotNull
        String firstName,
        @NotNull
        String lastName) {

    public static Person fromName(String firstName, String lastName) {
        return new Person(null, firstName, lastName);
    }
}
