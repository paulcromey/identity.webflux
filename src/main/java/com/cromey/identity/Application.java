package com.cromey.identity;

import com.cromey.identity.repository.ProfileRepository;
import com.cromey.identity.model.Profile;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(ProfileRepository repository){
		return args -> {
			Flux<Profile> profileFlux = Flux.just(
					new Profile(null, "success@simulator.amazonses.com"))
					.flatMap(repository::save);

			profileFlux.thenMany(repository.findAll()).subscribe();
		};
	}
}
