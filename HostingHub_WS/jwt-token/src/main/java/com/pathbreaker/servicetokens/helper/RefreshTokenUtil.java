package com.pathbreaker.servicetokens.helper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class RefreshTokenUtil {

    @Autowired
    public JwtUtil jwtUtil;

    public String generateJwtToken(String username) throws Exception {
        Map<String, Object> claims = new HashMap<>();

        // Move the calculation of now and expirationDate here
        Date now = new Date();
        long expirationMillis = now.getTime() + 1*60*60000; // Token expiration time (1 hr)
        Date expirationDate = new Date(expirationMillis);

        return createJwtToken(claims, username, now, expirationDate);
    }

    public String createJwtToken(Map<String, Object> claims, String subject, Date now, Date expirationDate) throws Exception {
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(jwtUtil.loadPrivateKey(), SignatureAlgorithm.RS256)
                .compact();

        System.out.println("Token created with expiration: " + expirationDate);
        return token;
    }

}
