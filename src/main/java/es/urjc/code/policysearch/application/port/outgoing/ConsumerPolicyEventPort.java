package es.urjc.code.policysearch.application.port.outgoing;

import es.urjc.code.policysearch.service.api.v1.events.PolicyRegisteredEvent;
import es.urjc.code.policysearch.service.api.v1.events.PolicyTerminatedEvent;

public interface ConsumerPolicyEventPort {
	public void onPolicyTerminated(PolicyTerminatedEvent event);
	public void onPolicyRegistered(PolicyRegisteredEvent event);
	
}
