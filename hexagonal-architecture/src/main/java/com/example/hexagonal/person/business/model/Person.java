package com.example.hexagonal.person.business.model;

import jakarta.validation.constraints.NotNull;

public record Person(
        @NotNull
        String firstName,
        @NotNull
        String lastName) {
}
