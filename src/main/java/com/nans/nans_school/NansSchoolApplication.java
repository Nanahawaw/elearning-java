package com.nans.nans_school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class NansSchoolApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(NansSchoolApplication.class, args);
	}

}
