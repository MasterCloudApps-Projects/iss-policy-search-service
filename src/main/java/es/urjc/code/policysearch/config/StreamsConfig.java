package es.urjc.code.policysearch.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import es.urjc.code.policysearch.infrastructure.adapter.kafka.PoliciesStreams;

@EnableBinding(PoliciesStreams.class)
public class StreamsConfig {

}
