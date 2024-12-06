package com.example.hexagonal.person.adapter.in.rest;

import com.example.hexagonal.person.port.in.SavePerson;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MockBeanConfiguration {
    @Bean
    public SavePerson savePerson() {
        return Mockito.mock(SavePerson.class);
    }
}
