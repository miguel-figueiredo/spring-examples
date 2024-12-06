package com.example.hexagonal.person.adapter.in.rest;

import com.example.hexagonal.person.port.in.SavePerson;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class MockBeanConfiguration {
    @Bean
    public SavePerson savePerson() {
        return mock(SavePerson.class);
    }
}
