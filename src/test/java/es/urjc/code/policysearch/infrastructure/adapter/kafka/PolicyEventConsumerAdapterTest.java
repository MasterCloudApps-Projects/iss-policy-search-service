package es.urjc.code.policysearch.infrastructure.adapter.kafka;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import es.urjc.code.policysearch.application.port.outgoing.PolicyViewUpdatePort;
import es.urjc.code.policysearch.domain.PolicyView;
import es.urjc.code.policysearch.service.api.v1.events.PolicyEvent;
import es.urjc.code.policysearch.service.api.v1.events.dto.PolicyDto;

class PolicyEventConsumerAdapterTest {

	private PolicyViewAssembler policyViewAssembler;
	private PolicyViewUpdatePort policyViewUpdatePort;
	private PolicyEventConsumerAdapter sut;
	
	@BeforeEach
	public void setUp() {
	   this.policyViewAssembler = Mockito.mock(PolicyViewAssembler.class);
	   this.policyViewUpdatePort = Mockito.mock(PolicyViewUpdatePort.class);
	   this.sut = new PolicyEventConsumerAdapter(policyViewAssembler, policyViewUpdatePort);
	}

	@Test
	void shouldBeProcessEvent() {
		// given
		final PolicyDto dto = getPolicyDto();
		final PolicyEvent event = new PolicyEvent.Builder().withPolicy(dto).build();
		final Message<PolicyEvent> message = MessageBuilder.withPayload(event).build();
		final PolicyView policyView = getPolicyView();
		when(policyViewAssembler.map(dto)).thenReturn(policyView);
		doNothing().when(policyViewUpdatePort).save(policyView);
		// when
		this.sut.process(message,Mockito.mock(Acknowledgment.class));
		// then
		verify(policyViewAssembler).map(dto);
		verify(policyViewUpdatePort).save(policyView);
	}
	
	private PolicyDto getPolicyDto() {
		return new PolicyDto.Builder().build();
	}
	
	private PolicyView getPolicyView() {
		return new PolicyView.Builder().build();
	}
}
