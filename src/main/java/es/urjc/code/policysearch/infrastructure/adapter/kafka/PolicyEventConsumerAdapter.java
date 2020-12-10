package es.urjc.code.policysearch.infrastructure.adapter.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(PolicyEventConsumerAdapter.class);

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
	public void process(Message<PolicyEvent> event, @Header(KafkaHeaders.ACKNOWLEDGMENT ) Acknowledgment  acknowledgment) {
    	PolicyEvent payload =  event.getPayload();
    	LOGGER.info("payload received {}", payload);
		final PolicyView policyView = policyViewAssembler.map(payload.getPolicy());
		policyViewUpdatePort.save(policyView);
        LOGGER.info("Acknowledgment provided");
        acknowledgment.acknowledge();
	}

	
}
