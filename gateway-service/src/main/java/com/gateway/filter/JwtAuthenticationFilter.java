package com.gateway.filter;

import com.gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // Allow login API
        if (path.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        System.out.println("PATH = " + path);
        System.out.println("TOKEN = " + token);

        boolean valid = jwtUtil.validateToken(token);

System.out.println("TOKEN VALID = " + valid);

if (!valid) {
    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
    return exchange.getResponse().setComplete();
}
        // if (!jwtUtil.validateToken(token)) {
        //     exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        //     return exchange.getResponse().setComplete();
        // }


        String roles = jwtUtil.extractRoles(token);

        System.out.println("ROLES = " + roles);
        
        // String roles = jwtUtil.extractClaims(token).get("roles", String.class);

        // System.out.println("Roles: " + roles);

        if (roles == null || (path.contains("/users") && !(roles.contains("ROLE_USER") || roles.contains("ROLE_ADMIN")))) {
    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
    return exchange.getResponse().setComplete();
        }

        if (path.contains("/admin") && !roles.contains("ROLE_ADMIN")) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
       
       
        String username = jwtUtil.extractUsername(token);

ServerWebExchange mutatedExchange = exchange.mutate()
        .request(
                exchange.getRequest()
                        .mutate()
                        .header("X-User", username)
                        .build())
        .build();

return chain.filter(mutatedExchange);

       
    }
}