package com.WAT.BEJURYU.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DrinkApiControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("전체 주류 리스트를 조회할 수 있다")
    @Test
    void findAll() {
        given().log().all()
                .get("/drinks")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @DisplayName("해당하는 이름의 주류 리스트를 조회할 수 있다")
    @Test
    void findByName() {
        given().log().all()
                .get("/drinks/name/{name}", "참이슬")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .body("name", blankOrNullString());
    }

    @DisplayName("주류 ID로 리뷰들을 조회할 수 있다")
    @Test
    void findReviewsById() {
        given().log().all()
                .get("/drinks/{drink_id}/reviews", 1L)
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
