package com.example.auth_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // boleh diakses tanpa token, untuk gateway dan postman
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/login").permitAll()

                        // endpoint lain tetap protected
                        .anyRequest().authenticated()
                )
                .build();
    }
}