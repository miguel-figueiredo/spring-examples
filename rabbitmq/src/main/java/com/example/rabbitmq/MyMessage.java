package com.example.rabbitmq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Jacksonized
public class MyMessage implements Serializable {

    @JsonProperty("message")
    private String message;
}
