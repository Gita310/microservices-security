package com.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;   // IMPORTANT IMPORT
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;

import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

@Bean
public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

    return http
            .csrf(csrf -> csrf.disable())

            .authorizeExchange(auth -> auth
                    .pathMatchers("/auth/**").permitAll()
                    .anyExchange().authenticated()
            )

            .oauth2ResourceServer(oauth2 ->
                    oauth2.jwt(Customizer.withDefaults())
            )

            .build();
}
}