package com.example.hexagonal.person.adapter.in.rest;

import com.example.hexagonal.person.port.in.SavePerson;
import com.example.hexagonal.person.port.in.SavePerson.NewPerson;
import com.example.hexagonal.spring.CustomErrorHandler;
import com.example.hexagonal.spring.HexagonalArchitectureApplication;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
                HexagonalArchitectureApplication.class
        }
)
class SavePersonControllerTest {

    @Autowired
    SavePerson savePerson;

    @BeforeEach
    void setUp() {
        standaloneSetup(new SavePersonController(savePerson), CustomErrorHandler.class);
    }

    @Test
    void invalidContentType() {
        given()
            .contentType(ContentType.TEXT)
        .when()
            .post("/api/persons")
        .then()
            .statusCode(415);
    }

    @Test
    void noBody() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/api/persons")
        .then()
            .statusCode(400);
    }

    @ParameterizedTest
    @MethodSource("invalidPersons")
    void validationError(NewPerson person) {
        given()
            .contentType(ContentType.JSON)
            .body(person)
        .when()
            .post("/api/persons")
        .then()
            .statusCode(400);
    }
 
    @Test
    void validPerson() {
        given()
            .contentType(ContentType.JSON)
            .body(new NewPerson("First", "Last"))
        .when()
                .post("/api/persons")
        .then()
            .statusCode(200);

        verify(savePerson).execute(new NewPerson("First", "Last"));
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