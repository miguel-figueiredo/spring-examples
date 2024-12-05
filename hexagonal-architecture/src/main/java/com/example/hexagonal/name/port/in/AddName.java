package com.example.hexagonal.name.port.in;

import jakarta.validation.constraints.NotEmpty;

/**
 * Adds a name to the system.
 */
public interface AddName {

    void execute(@NotEmpty String name);
}
