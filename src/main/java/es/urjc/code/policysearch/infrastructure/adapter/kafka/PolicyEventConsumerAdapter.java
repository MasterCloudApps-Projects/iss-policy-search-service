package es.urjc.code.policysearch.infrastructure.adapter.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.urjc.code.policysearch.application.port.outgoing.PolicyEventConsumerPort;
import es.urjc.code.policysearch.application.port.outgoing.PolicyViewUpdatePort;
import es.urjc.code.policysearch.domain.PolicyView;
import es.urjc.code.policysearch.service.api.v1.events.PolicyRegisteredEvent;
import es.urjc.code.policysearch.service.api.v1.events.PolicyTerminatedEvent;

@Service
@Transactional
public class PolicyEventConsumerAdapter implements PolicyEventConsumerPort {

	private final PolicyViewAssembler policyViewAssembler;
	private final PolicyViewUpdatePort policyViewUpdatePort;
	
	@Autowired
	public PolicyEventConsumerAdapter(PolicyViewAssembler policyViewAssembler,
			                          PolicyViewUpdatePort policyViewUpdatePort) {
		this.policyViewAssembler = policyViewAssembler;
		this.policyViewUpdatePort = policyViewUpdatePort;
	}

	@StreamListener(PoliciesStreams.INPUT_POLICY_TERMINATED)
	@Override
	public void onPolicyTerminated(PolicyTerminatedEvent event) {
		final PolicyView policyView = policyViewAssembler.map(event.getPolicy());
		policyViewUpdatePort.save(policyView);
	}
	
	@StreamListener(PoliciesStreams.INPUT_POLICY_REGISTRED)
	@Override
	public void onPolicyRegistered(PolicyRegisteredEvent event) {
		final PolicyView policyView = policyViewAssembler.map(event.getPolicy());
		policyViewUpdatePort.save(policyView);
	}
	
}
