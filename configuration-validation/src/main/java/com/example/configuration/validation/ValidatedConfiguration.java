package com.example.configuration.validation;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("my.property")
public record ValidatedConfiguration(@Min(1) int min) {
}
