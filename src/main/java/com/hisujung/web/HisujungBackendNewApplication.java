package com.hisujung.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HisujungBackendNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(HisujungBackendNewApplication.class, args);
	}

}
