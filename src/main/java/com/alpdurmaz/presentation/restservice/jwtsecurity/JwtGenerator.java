package com.alpdurmaz.presentation.restservice.jwtsecurity;

import com.alpdurmaz.presentation.restservice.models.jwtmodels.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {

    public String generate(JwtUser jwtUser){

        Claims claims = Jwts.claims().setSubject(jwtUser.getEmail());

        claims.put("password", String.valueOf(jwtUser.getPassword()));
        claims.put("role", jwtUser.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "soto babi")
                .compact();
    }
}