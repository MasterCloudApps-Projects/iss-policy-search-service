package es.urjc.code.policysearch.queries;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import es.urjc.code.policysearch.application.port.outgoing.PolicyViewPort;
import es.urjc.code.policysearch.domain.PolicyView;
import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.FindPolicyQuery;
import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

class FindPolicyQueryHandlerTest {

	private static final String QUERY_TEXT = "queryText";
	
	private PolicyViewPort policyViewPort;
	private PolicyQueryResultAssembler policyQueryResultAssembler;
	private FindPolicyQueryHandler sut;
	
	@BeforeEach
	public void setUp() {
		this.policyViewPort = Mockito.mock(PolicyViewPort.class);
		this.policyQueryResultAssembler = Mockito.mock(PolicyQueryResultAssembler.class);
		this.sut = new FindPolicyQueryHandler(policyViewPort,policyQueryResultAssembler); 
	}
	
	@Test
	void shouldBeFindAll() {
		// given
		final List<PolicyView> list = Arrays.asList(getPolicyView());
		final FindPolicyQueryResult findPolicyQueryResult = getFindPolicyQueryResult();
		when(policyViewPort.findAll(QUERY_TEXT)).thenReturn(list);
		when(policyQueryResultAssembler.constructResult(list)).thenReturn(findPolicyQueryResult);
		// when
		FindPolicyQueryResult response = this.sut.handle(new FindPolicyQuery.Builder().withQueryText(QUERY_TEXT).build());
		// then
		verify(policyViewPort).findAll(QUERY_TEXT);
		verify(policyQueryResultAssembler).constructResult(any());
		assertNotNull(response);
	}
	
	private FindPolicyQueryResult getFindPolicyQueryResult() {
		return new FindPolicyQueryResult.Builder().build();
	}

	private PolicyView getPolicyView() {
		return new PolicyView.Builder().build();
	}
}
