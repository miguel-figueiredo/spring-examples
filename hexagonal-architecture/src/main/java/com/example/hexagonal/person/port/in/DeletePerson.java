package com.example.hexagonal.person.port.in;

import com.example.hexagonal.util.Validation;
import jakarta.validation.constraints.NotNull;

@Validation
public interface DeletePerson {

    void execute(@NotNull Long personId);
}
