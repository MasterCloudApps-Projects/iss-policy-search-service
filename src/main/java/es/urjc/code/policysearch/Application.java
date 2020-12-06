package es.urjc.code.policysearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import es.codeurjc.policysearch.command.bus.Bus;
import es.codeurjc.policysearch.command.bus.Registry;
import es.codeurjc.policysearch.command.bus.SpringBus;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {
    
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public Registry registry(ApplicationContext applicationContext) {
        return new Registry(applicationContext);
    }

    @Bean
    public Bus commandBus(Registry registry) {
        return new SpringBus(registry);
    }

}
