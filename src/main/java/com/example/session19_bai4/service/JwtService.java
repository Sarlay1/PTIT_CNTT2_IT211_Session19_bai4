package com.example.session19_bai4.service;

import com.example.session19_bai4.entity.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtService {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkeymysecretkey";

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(
                SECRET.getBytes()
        );
    }

    public String generateAccessToken(Employee employee) {

        Map<String,Object> claims = new HashMap<>();

        claims.put(
                "roles",
                employee.getRoles()
                        .stream()
                        .map(role -> role.getName())
                        .toList()
        );

        return buildToken(
                claims,
                employee,
                1000 * 60 * 15
        );
    }

    public String generateRefreshToken(Employee employee) {

        return buildToken(
                new HashMap<>(),
                employee,
                1000L * 60 * 60 * 24 * 7
        );
    }

    private String buildToken(
            Map<String,Object> claims,
            UserDetails userDetails,
            long expiration
    ){

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()+expiration
                        )
                )
                .signWith(getSignKey())
                .compact();
    }

    public String extractUsername(String token){

        return extractAllClaims(token)
                .getSubject();
    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails
    ){

        String username = extractUsername(token);

        return username.equals(
                userDetails.getUsername()
        );
    }

    private Claims extractAllClaims(String token){

        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}