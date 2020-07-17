package com.company;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ResumerestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumerestapiApplication.class, args);
	}

}
