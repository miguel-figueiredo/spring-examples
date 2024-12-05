package com.example.hexagonal.person.port.in;

import com.example.hexagonal.util.Validation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

/**
 * Adds a name to the system.
 */
@Validation
public interface SavePerson {

    void execute(@Valid NewPerson person);

    record NewPerson(
            @NotEmpty
            String firstName,
            @NotEmpty
            String lastName) {
    }
}
