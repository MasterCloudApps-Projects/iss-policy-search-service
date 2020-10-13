package es.urjc.code.policysearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import es.codeurjc.policysearch.command.bus.Bus;
import es.codeurjc.policysearch.command.bus.Registry;
import es.codeurjc.policysearch.command.bus.SpringBus;

@EnableJpaRepositories("es.urjc.code.policysearch.infrastructure.adapter.repository.jpa")
@SpringBootApplication
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
