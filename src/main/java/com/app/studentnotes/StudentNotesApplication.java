package com.app.studentnotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class StudentNotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentNotesApplication.class, args);
	}

}
