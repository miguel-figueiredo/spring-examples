package com.example.hexagonal.person.adapter.persistence;

import com.example.hexagonal.person.adapter.rest.AddPersonController;
import com.example.hexagonal.person.business.model.Person;
import com.example.hexagonal.person.port.in.AddPerson;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = AddPersonDouble.class)
class AddNameControllerTest {

    @Autowired
    AddPerson addName;

    @BeforeEach
    void setUp() {
        standaloneSetup(new AddPersonController(addName));
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
    void addInvalidPersons(Person person) {
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
            .body("Miguel Figueiredo")
        .when()
            .post("/api/persons")
        .then()
            .statusCode(200);
    }

    public static Stream<Arguments> invalidPersons() {
        return Stream.of(
            Arguments.of(new Person(null, null)),
            Arguments.of(new Person("First", null)),
            Arguments.of(new Person(null, "Last"))
        );
    }
}