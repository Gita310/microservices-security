package com.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

String SECRET =
"mysupersecretkeymysupersecretkey";

public String generateToken( String username, String role){

return Jwts.builder()

.setSubject(username)

.claim("roles", role) 

.setIssuedAt(new Date())

.setExpiration(
new Date(System.currentTimeMillis()
+3600000))

.signWith(
Keys.hmacShaKeyFor(
SECRET.getBytes()
)
)

.compact();
}
}