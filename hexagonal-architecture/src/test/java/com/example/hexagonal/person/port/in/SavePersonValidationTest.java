package com.example.hexagonal.person.port.in;

import com.example.hexagonal.person.port.in.SavePerson.NewPerson;
import com.example.hexagonal.spring.HexagonalArchitectureApplication;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

// Required because the main class is not in the root package.
// BOGUS: makes port or use cases depend on spring, and causes a failure in the Arch Unit tests.
@SpringBootTest(classes = HexagonalArchitectureApplication.class)
public class SavePersonValidationTest {

    @Autowired
    SavePerson savePerson;

    @ParameterizedTest
    @MethodSource("invalidPersons")
    void shouldThrowExceptionForInvalidPersons(NewPerson person) {
        Assertions.assertThrows(ConstraintViolationException.class, () -> savePerson.execute(person));
    }

    public static Stream<Arguments> invalidPersons() {
        return Stream.of(
            Arguments.of(new NewPerson(null, "Last")),
            Arguments.of(new NewPerson("", "Last")),
            Arguments.of(new NewPerson("First", "")),
            Arguments.of(new NewPerson("First", null))
        );
    }

}