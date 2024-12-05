package com.example.hexagonal.person.port.in;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

/**
 * Adds a name to the system.
 */
// TODO: add an alias
@Validated
public interface SavePerson {
    void execute(@Valid NewPerson person);

    public record NewPerson(
            @NotEmpty
            String firstName,
            @NotEmpty
            String lastName) {
    }
}
