package com.example.validation;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

@Validated
public interface Bye {

    String bye(@NotEmpty String name);
}
