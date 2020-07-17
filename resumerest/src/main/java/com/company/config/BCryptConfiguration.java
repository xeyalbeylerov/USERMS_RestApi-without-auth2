package com.company.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BCryptConfiguration {
    @Bean
    public BCrypt.Hasher getHash() {
        BCrypt.Hasher getHash=BCrypt.withDefaults();
        return getHash;
    }
}
