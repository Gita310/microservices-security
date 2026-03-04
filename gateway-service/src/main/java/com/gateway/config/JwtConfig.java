package com.gateway.config;

import javax.crypto.spec.SecretKeySpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;

@Configuration
public class JwtConfig {

    private final String SECRET =
            "mysupersecretkeymysupersecretkey";

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {

        SecretKeySpec key =
                new SecretKeySpec(
                        SECRET.getBytes(),
                        "HmacSHA256"
                );

        return NimbusReactiveJwtDecoder
                .withSecretKey(key)
                .build();
    }
}