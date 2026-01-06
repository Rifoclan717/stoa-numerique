package com.example.stoa_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Permet l'auditing avec les date et tout
public class StoaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoaBackendApplication.class, args);
	}

}
