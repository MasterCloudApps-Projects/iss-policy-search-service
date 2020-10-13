package es.urjc.code.policysearch.infrastructure.adapter.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.urjc.code.policysearch.application.port.outgoing.ConsumerPolicyEventPort;
import es.urjc.code.policysearch.application.port.outgoing.UpdatePolicyViewPort;
import es.urjc.code.policysearch.domain.PolicyView;
import es.urjc.code.policysearch.service.api.v1.events.PolicyRegisteredEvent;
import es.urjc.code.policysearch.service.api.v1.events.PolicyTerminatedEvent;

@Component
@Transactional
public class PolicyEventConsumerAdapter implements ConsumerPolicyEventPort {

	private final PolicyViewAssembler policyViewAssembler;
	private final UpdatePolicyViewPort updatePolicyViewPort;
	
	@Autowired
	public PolicyEventConsumerAdapter(PolicyViewAssembler policyViewAssembler,UpdatePolicyViewPort updatePolicyViewPort) {
		this.policyViewAssembler = policyViewAssembler;
		this.updatePolicyViewPort = updatePolicyViewPort;
	}

	@StreamListener(PoliciesStreams.INPUT_POLICY_TERMINATED)
	@Override
	public void onPolicyTerminated(PolicyTerminatedEvent event) {
		final PolicyView policyView = policyViewAssembler.map(event.getPolicy());
		updatePolicyViewPort.save(policyView);
	}
	
	@StreamListener(PoliciesStreams.INPUT_POLICY_REGISTRED)
	@Override
	public void onPolicyRegistered(PolicyRegisteredEvent event) {
		final PolicyView policyView = policyViewAssembler.map(event.getPolicy());
		updatePolicyViewPort.save(policyView);
	}
	
}
