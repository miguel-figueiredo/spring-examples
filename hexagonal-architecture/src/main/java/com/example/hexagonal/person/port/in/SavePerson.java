package com.example.hexagonal.person.port.in;

import com.example.hexagonal.person.business.model.Person;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

/**
 * Adds a name to the system.
 */
// TODO: add an alias
@Validated
public interface SavePerson {
    void execute(@Valid Person person);
}
