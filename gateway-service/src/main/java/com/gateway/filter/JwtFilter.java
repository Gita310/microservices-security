package com.gateway.filter;

import com.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {

        String path = exchange.getRequest()
                              .getURI()
                              .getPath();

        System.out.println("Gateway Path = " + path);

        // ✅ Skip authentication for auth-service endpoints
        if (path.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        String authHeader =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst("Authorization");

        if (authHeader == null ||
            !authHeader.startsWith("Bearer ")) {

            return Mono.error(
                new RuntimeException("Missing JWT Token")
            );
        }

        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {

            return Mono.error(
                new RuntimeException("Invalid JWT Token")
            );
        }

        return chain.filter(exchange);
    }
}