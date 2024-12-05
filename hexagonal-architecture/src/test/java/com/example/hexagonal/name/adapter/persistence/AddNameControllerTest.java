package com.example.hexagonal.name.adapter.persistence;

import com.example.hexagonal.name.adapter.rest.AddNameController;
import com.example.hexagonal.name.business.usecase.AddNameUseCase;
import com.example.hexagonal.name.port.in.AddName;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = AddNameDouble.class)
class AddNameControllerTest {

    @Autowired
    AddName addName;

    @BeforeEach
    void setUp() {
        standaloneSetup(new AddNameController(addName));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void addInvalidNames(String name) {
        given()
            .contentType(ContentType.TEXT)
            .body(name)
        .when()
            .post("/api/names")
        .then()
            .statusCode(400);
    }

    @Test
    void validName() {
        given()
            .contentType(ContentType.TEXT)
            .body("Miguel Figueiredo")
        .when()
            .post("/api/names")
        .then()
            .statusCode(200);
    }
}