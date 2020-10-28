package es.urjc.code.policysearch.application.port.outgoing;

import org.springframework.messaging.Message;

import es.urjc.code.policysearch.service.api.v1.events.PolicyEvent;

public interface PolicyEventConsumerPort {
	
	public void process(Message<PolicyEvent> event);
}
