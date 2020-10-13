package es.urjc.code.policysearch.infrastructure.adapter.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PoliciesStreams {

	final String INPUT_POLICY_REGISTRED = "policy-registered-in";
	final String INPUT_POLICY_TERMINATED = "policy-terminated-in";
	
    @Input(INPUT_POLICY_TERMINATED)
    SubscribableChannel inboundTerminated();

    @Input(INPUT_POLICY_REGISTRED)
    SubscribableChannel inboundRegistred();

}
