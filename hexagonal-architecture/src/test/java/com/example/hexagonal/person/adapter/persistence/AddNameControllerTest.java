package com.example.hexagonal.person.adapter.persistence;

import com.example.hexagonal.person.adapter.in.rest.SavePersonController;
import com.example.hexagonal.person.port.in.SavePerson;
import com.example.hexagonal.person.port.in.SavePerson.NewPerson;
import com.example.hexagonal.spring.CustomErrorHandler;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.Mock;

import java.util.stream.Stream;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

class AddNameControllerTest {

    @Mock
    SavePerson addName;

    @BeforeEach
    void setUp() {
        standaloneSetup(new SavePersonController(addName), CustomErrorHandler.class);
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

    @Test
    void validationError() {
//        doThrow(new ValidationException()).when(addName).execute(any());

        given()
            .contentType(ContentType.JSON)
            .body(new NewPerson("First", "Last"))
        .when()
            .post("/api/persons")
        .then()
            .statusCode(400);
    }

    @Test
    void validPerson() {
        given()
            .contentType(ContentType.JSON)
            .body("Miguel Figueiredo")
        .when()
            .post("/api/persons")
        .then()
            .statusCode(200);
    }

    public static Stream<Arguments> invalidPersons() {
        return Stream.of(
            Arguments.of(new NewPerson(null, null)),
            Arguments.of(new NewPerson("First", null)),
            Arguments.of(new NewPerson(null, "Last"))
        );
    }
}