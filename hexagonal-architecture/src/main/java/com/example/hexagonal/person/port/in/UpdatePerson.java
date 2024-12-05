package com.example.hexagonal.person.port.in;

import com.example.hexagonal.person.business.model.Person;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UpdatePerson {

    void execute(@Valid Person person);
}
