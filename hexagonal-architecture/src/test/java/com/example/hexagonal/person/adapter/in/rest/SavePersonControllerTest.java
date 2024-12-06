package com.example.hexagonal.person.adapter.in.rest;

import com.example.hexagonal.person.port.in.SavePerson;
import com.example.hexagonal.person.port.in.SavePerson.NewPerson;
import com.example.hexagonal.spring.CustomErrorHandler;
import io.restassured.http.ContentType;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class SavePersonControllerTest {

    @Mock
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

    @Test
    void validationError() {
        doThrow(new ConstraintViolationException(Set.of())).when(savePerson).execute(any());

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
            .body(new NewPerson("First", "Last"))
        .when()
                .post("/api/persons")
        .then()
            .statusCode(200);
    }
}