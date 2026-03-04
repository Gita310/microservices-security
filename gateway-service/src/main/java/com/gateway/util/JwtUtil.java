package com.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

    private final String SECRET = "mysupersecretkeymysupersecretkey";

    private final Key key =
            Keys.hmacShaKeyFor(SECRET.getBytes());


    // Extract all claims
    public Claims extractClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)   // FIXED
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public String extractRoles(String token) {
        return extractClaims(token)
                .get("roles", String.class);
    }


    public String extractUsername(String token) {
        return extractClaims(token)
                .getSubject();
    }


    public boolean validateToken(String token) {

        try {
            extractClaims(token);
            return true;

        } catch (Exception e) {

            System.out.println("JWT ERROR = " + e.getMessage());

            return false;
        }
    }

}