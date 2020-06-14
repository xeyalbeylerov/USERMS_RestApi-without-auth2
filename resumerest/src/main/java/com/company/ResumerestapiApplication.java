package com.company;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dto.ResponseDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ResumerestapiApplication {

	@Bean
	public BCrypt.Hasher getHash() {
		BCrypt.Hasher getHash=BCrypt.withDefaults();
		return getHash;
	}
	public static void main(String[] args) {
		SpringApplication.run(ResumerestapiApplication.class, args);
	}

}
