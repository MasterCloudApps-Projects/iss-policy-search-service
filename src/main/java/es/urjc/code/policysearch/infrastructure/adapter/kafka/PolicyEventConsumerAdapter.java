package es.urjc.code.policysearch.infrastructure.adapter.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.urjc.code.policysearch.application.port.outgoing.PolicyEventConsumerPort;
import es.urjc.code.policysearch.application.port.outgoing.PolicyViewUpdatePort;
import es.urjc.code.policysearch.domain.PolicyView;
import es.urjc.code.policysearch.service.api.v1.events.PolicyEvent;

@Component
@Transactional
@EnableBinding(Sink.class)
public class PolicyEventConsumerAdapter implements PolicyEventConsumerPort {

	private final PolicyViewAssembler policyViewAssembler;
	private final PolicyViewUpdatePort policyViewUpdatePort;
	
	@Autowired
	public PolicyEventConsumerAdapter(PolicyViewAssembler policyViewAssembler,
			                          PolicyViewUpdatePort policyViewUpdatePort) {
		this.policyViewAssembler = policyViewAssembler;
		this.policyViewUpdatePort = policyViewUpdatePort;
	}

    @StreamListener(Sink.INPUT)
	@Override
	public void process(Message<PolicyEvent> event) {
    	PolicyEvent payload =  event.getPayload();
		final PolicyView policyView = policyViewAssembler.map(payload.getPolicy());
		policyViewUpdatePort.save(policyView);
	}

	
}
