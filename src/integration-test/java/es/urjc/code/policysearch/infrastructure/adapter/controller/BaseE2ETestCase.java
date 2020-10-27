package es.urjc.code.policysearch.infrastructure.adapter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.web.server.LocalServerPort;

import io.restassured.RestAssured;

public abstract class BaseE2ETestCase {
	
	@LocalServerPort
	private int port;
	
	
	@BeforeEach
	void setUpBeforeEach() {
		RestAssured.port = this.port;
	}
	
	 enum Resources {
	        V1_FIND_POLICIES_ENDPOINT("/api/v1/policies/1");

	        private final String endpoint;

	        Resources(String endpoint) {
	            this.endpoint = endpoint;
	        }

	        public String build() {
	            return endpoint;
	        }
	    }
}
