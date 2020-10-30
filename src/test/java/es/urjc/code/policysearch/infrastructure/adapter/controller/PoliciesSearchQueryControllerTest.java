package es.urjc.code.policysearch.infrastructure.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.codeurjc.policysearch.command.bus.Bus;
import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.FindPolicyQuery;
import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

class PoliciesSearchQueryControllerTest {

	public static final String  QUERY_TEST="ABCD";
	
	private Bus bus;
	private PoliciesSearchQueryController sut;
	
	@BeforeEach
	public void setUp() {
		this.bus = Mockito.mock(Bus.class);
		this.sut = new PoliciesSearchQueryController(bus);
	}
	
	@Test
	void shouldBeInvokeFindPolicies() {
		// given
		final FindPolicyQuery query =  getFindPolicyQuery();
		final FindPolicyQueryResult result = getFindPolicyQueryResult();
		when(bus.executeQuery(query)).thenReturn(result);
		// when
		ResponseEntity<FindPolicyQueryResult>  response = this.sut.policies(QUERY_TEST);
		// then
		verify(bus).executeQuery(query);
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(response.getBody(), result);
	}
	
	private FindPolicyQueryResult getFindPolicyQueryResult() {
	  	return new FindPolicyQueryResult.Builder().build();
	}
	
    private FindPolicyQuery getFindPolicyQuery() {
       return new FindPolicyQuery.Builder().withQueryText(QUERY_TEST).build();
    }
}
