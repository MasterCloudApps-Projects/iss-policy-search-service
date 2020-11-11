package es.urjc.code.policysearch.infrastructure.adapter.controller;

import static es.urjc.code.policysearch.infrastructure.adapter.controller.BaseE2ETestCase.Resources.V1_FIND_POLICIES_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PoliciesSearchQueryControllerE2ETestCase extends BaseE2ETestCase {



	@Test
	void shouldBeFindAllPolicies() {
        //when
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(V1_FIND_POLICIES_ENDPOINT.build())
                .prettyPeek()
                .then();
        //then
        response.statusCode(HttpStatus.OK.value())
                .body("policies", notNullValue());
	}
}
