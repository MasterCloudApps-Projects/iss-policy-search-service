package es.urjc.code.policysearch.infrastructure.adapter.kafka;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import es.urjc.code.policysearch.application.port.outgoing.UpdatePolicyViewPort;
import es.urjc.code.policysearch.domain.PolicyView;
import es.urjc.code.policysearch.service.api.v1.events.PolicyRegisteredEvent;
import es.urjc.code.policysearch.service.api.v1.events.PolicyTerminatedEvent;
import es.urjc.code.policysearch.service.api.v1.events.dto.PolicyDto;

class PolicyEventConsumerAdapterTest {

	private PolicyViewAssembler policyViewAssembler;
	private UpdatePolicyViewPort updatePolicyViewPort;
	private PolicyEventConsumerAdapter sut;
	
	@BeforeEach
	public void setUp() {
	   this.policyViewAssembler = Mockito.mock(PolicyViewAssembler.class);
	   this.updatePolicyViewPort = Mockito.mock(UpdatePolicyViewPort.class);
	   this.sut = new PolicyEventConsumerAdapter(policyViewAssembler, updatePolicyViewPort);
	}

	@Test
	void shouldBeHandlePolicyTerminatedEvent() {
		// given
		final PolicyDto dto = getPolicyDto();
		final PolicyTerminatedEvent event = new PolicyTerminatedEvent.Builder().withPolicy(dto).build();
		final PolicyView policyView = getPolicyView();
		when(policyViewAssembler.map(dto)).thenReturn(policyView);
		doNothing().when(updatePolicyViewPort).save(policyView);
		// when
		this.sut.onPolicyTerminated(event);
		// then
		verify(policyViewAssembler).map(dto);
		verify(updatePolicyViewPort).save(policyView);
	}
	
	@Test
	void shouldBeHandlePolicyRegisteredEvent() {
		// given
		final PolicyDto dto = getPolicyDto();
		final PolicyRegisteredEvent event = new PolicyRegisteredEvent.Builder().withPolicy(dto).build();
		final PolicyView policyView = getPolicyView();
		when(policyViewAssembler.map(dto)).thenReturn(policyView);
		doNothing().when(updatePolicyViewPort).save(policyView);
		// when
		this.sut.onPolicyRegistered(event);
		// then
		verify(policyViewAssembler).map(dto);
		verify(updatePolicyViewPort).save(policyView);
	}
	
	private PolicyDto getPolicyDto() {
		return new PolicyDto.Builder().build();
	}
	
	private PolicyView getPolicyView() {
		return new PolicyView.Builder().build();
	}
}