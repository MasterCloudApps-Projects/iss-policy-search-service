package es.urjc.code.policysearch.infrastructure.adapter.kafka;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.urjc.code.policysearch.domain.PolicyView;
import es.urjc.code.policysearch.service.api.v1.events.dto.PolicyDto;

class PolicyViewAssemblerTest {

	private static final String POLICY_HOLDER = "François Poirier";
	private static final LocalDate DATE_TO = LocalDate.of(2018, 12, 31);
	private static final LocalDate DATE_FROM = LocalDate.of(2018, 1, 1);
	private static final String POLICY_NUMBER = "P1212121";
	private PolicyViewAssembler sut;
	
	@BeforeEach
	public void setUp() {
		this.sut = new PolicyViewAssembler();
	}			
	
	@Test
	void shouldBeReturnPolicyView() {
		// when
		final PolicyView response = this.sut.map(getPolicyDto());
		assertThat(response.getPolicyHolder(), is(POLICY_HOLDER));
		assertThat(response.getNumber(), is(POLICY_NUMBER));
		assertThat(response.getDateFrom(), is(DATE_FROM));
		assertThat(response.getDateTo(), is(DATE_TO));
	}

	private PolicyDto getPolicyDto() {
		return new PolicyDto.Builder().withPolicyHolder(POLICY_HOLDER).withNumber(POLICY_NUMBER).withFrom(DATE_FROM)
				.withTo(DATE_TO).build();
	}
}
