package com.example.hexagonal.person.port.in;

import com.example.hexagonal.person.business.model.Person;
import com.example.hexagonal.util.Validation;
import jakarta.validation.Valid;

@Validation
public interface UpdatePerson {

    void execute(@Valid Person person);
}
