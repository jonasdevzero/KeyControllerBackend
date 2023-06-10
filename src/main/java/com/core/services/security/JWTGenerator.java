package com.core.services.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.core.services.dto.JWTObject;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service 
public class JWTGenerator {
    
    @Value("${security.config.key}")
    private String secret;

    public JWTObject generateToken(String username) {

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); // não mecha nisso

        JWTObject jwt = new JWTObject();

        String jwtToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        jwt.setToken(jwtToken);

        return jwt;
    }
}