package com.example.restapi;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc(mockMvc);
    }

    @Test
    @DisplayName("Given a valid request," +
            "when get," +
            "then successful response")
    void get() throws IOException {
        when()
            .get("/hello/miguel")
        .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("Given a valid request," +
            "when post," +
            "then successful response")
    void post() throws IOException {
        given()
            .body("miguel")
        .when()
            .post("/hello")
        .then()
            .statusCode(200);
    }

    @Test
    @DisplayName("Given an invalid request," +
            "when post," +
            "then successful response")
    void invalidPost() throws IOException {
        given()
            .body("")
        .when()
            .post("/hello")
        .then()
            .statusCode(400);
    }
}