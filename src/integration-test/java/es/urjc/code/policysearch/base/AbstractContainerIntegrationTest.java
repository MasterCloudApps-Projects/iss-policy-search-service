package es.urjc.code.policysearch.base;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class AbstractContainerIntegrationTest {
	
    private static final KafkaContainer kafkaContainer = new KafkaContainer("5.5.1");

    private static final ElasticsearchContainer elasticsearchContainer = new PolicyElasticsearchContainer();
    
    @DynamicPropertySource
    private static void dynamicProperties(DynamicPropertyRegistry registry) {
        Network network = Network.SHARED;
        
        // Kafka
        kafkaContainer.withNetwork(network).withNetworkAliases("kafka")
                .withExternalZookeeper("zookeeper:2181")
                .withExposedPorts(9092, 9093);
        kafkaContainer.start();

        // elasticsearch
		elasticsearchContainer.withNetwork(network).withNetworkAliases("elasticsearch");
		elasticsearchContainer.start();
		
		registry.add("spring.kafka.bootstrap-servers",kafkaContainer::getBootstrapServers);
    }

	@BeforeAll
	static void setUpAll() {
		if (!kafkaContainer.isRunning()) {
			kafkaContainer.start();
		}
		if (!elasticsearchContainer.isRunning()) {
			elasticsearchContainer.start();
		}
	}
	
	@Test
	void shouldBeRunning() {
		assertTrue(kafkaContainer.isRunning());
		assertTrue(elasticsearchContainer.isRunning());
	}

}