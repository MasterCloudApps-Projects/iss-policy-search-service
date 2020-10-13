package es.urjc.code.policysearch.queries;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.urjc.code.policysearch.domain.PolicyView;
import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

class PolicyQueryResultAssemblerTest {

	private static final String POLICY_HOLDER = "François Poirier";
	private static final LocalDate DATE_TO = LocalDate.of(2018, 12, 31);
	private static final LocalDate DATE_FROM = LocalDate.of(2018, 1, 1);
	private static final String POLICY_NUMBER = "P1212121";
	private PolicyQueryResultAssembler sut;

	@BeforeEach
	public void setUp() {
		this.sut = new PolicyQueryResultAssembler();
	}

	@Test
	void shouldBeReturnPolicyQueryResult() {
		// given
		final List<PolicyView> list = Arrays.asList(getPolicyView());
		// when
		final FindPolicyQueryResult response = this.sut.constructResult(list);
		// then
		assertThat(response.getPolicies().get(0).getPolicyHolder(), is(POLICY_HOLDER));
		assertThat(response.getPolicies().get(0).getNumber(), is(POLICY_NUMBER));
		assertThat(response.getPolicies().get(0).getDateFrom(), is(DATE_FROM));
		assertThat(response.getPolicies().get(0).getDateTo(), is(DATE_TO));
	}

	private PolicyView getPolicyView() {
		return new PolicyView.Builder().withPolicyHolder(POLICY_HOLDER).withNumber(POLICY_NUMBER)
				.withDateFrom(DATE_FROM).withDateTo(DATE_TO).build();
	}
}
