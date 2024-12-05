package com.example.hexagonal.name.adapter.persistence;

import com.example.hexagonal.name.adapter.rest.AddNameController;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

class AddNameControllerTest {

    @BeforeEach
    void setUp() {
        standaloneSetup(new AddNameController(name -> {}));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void addInvalidNames(String name) {
        given()
            .contentType(ContentType.JSON)
            .body(name)
        .when()
            .post("/api/names")
        .then()
            .statusCode(400);
    }
}