package es.urjc.code.policysearch.infrastructure.adapter.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PoliciesSearchQueryControllerE2ETestCase {

	@LocalServerPort
	private int port;
	
	
	@BeforeEach
	void setUpBeforeEach() {
		RestAssured.port = this.port;
	}

	@Test
	void shouldBeFindAllPolicies() {
        //when
        ValidatableResponse response = given()
                .contentType("application/json")
                .when()
                .get("/api/v1/policies/1")
                .prettyPeek()
                .then();
        //then
        response.statusCode(HttpStatus.OK.value())
                .body("policies", notNullValue());
	}
}
