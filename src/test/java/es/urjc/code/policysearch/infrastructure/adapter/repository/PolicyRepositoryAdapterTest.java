package es.urjc.code.policysearch.infrastructure.adapter.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import es.urjc.code.policysearch.domain.PolicyView;
import es.urjc.code.policysearch.infrastructure.adapter.repository.es.PolicyViewRepository;

class PolicyRepositoryAdapterTest {

	private static final String QUERY_TEXT = "queryText";
	private PolicyViewRepository policyViewRepository;
	private PolicyRepositoryAdapter sut;
	
	@BeforeEach
	public void setUp() {
		this.policyViewRepository = Mockito.mock(PolicyViewRepository.class);
		this.sut = new PolicyRepositoryAdapter(policyViewRepository);
	}
	
	@Test
    void shouldBeReturnAllPolicyViews() {
		// given
		final List<PolicyView> list = Arrays.asList(getPolicyView());
		when(policyViewRepository.findAll(QUERY_TEXT)).thenReturn(list);
		// when
		final List<PolicyView> response = this.sut.findAll(QUERY_TEXT);
		// then
		verify(policyViewRepository).findAll(QUERY_TEXT);
		assertThat(response, is(list));
    }
	
	@Test
    void shouldBeSavePolicyView() {
		// given
		final PolicyView policyView = getPolicyView();
		doNothing().when(policyViewRepository).save(policyView);
		// when
		this.sut.save(policyView);
		// then
		verify(policyViewRepository).save(policyView);
    }
	
	private PolicyView getPolicyView() {
		return new PolicyView.Builder().build();
	}
}
