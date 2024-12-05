package com.example.hexagonal.person.domain.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record Person(
        @NotNull
        Long id,
        @NotEmpty
        String firstName,
        @NotEmpty
        String lastName) {
}
