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
public class JWTToken {
    
    @Value("${security.config.key}")
    private String secret;

    @Value("${security.config.prefix}")
    private String prefix;

    @Value("${security.config.expiration}")
    private int timeExpiration;

    public JWTObject generateToken(String username) {

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); // não mecha nisso

        String jwtToken = Jwts.builder()
                .setSubject(username)
                .setIssuer("Key-Controller")
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date( (System.currentTimeMillis() + timeExpiration * 1000) ) )
                .compact();

        JWTObject jwt = new JWTObject(jwtToken);
        
        return jwt;
    }

    public void authorize(String tokenEncoded){
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(tokenEncoded.substring(prefix.length()).trim());      

    }
}