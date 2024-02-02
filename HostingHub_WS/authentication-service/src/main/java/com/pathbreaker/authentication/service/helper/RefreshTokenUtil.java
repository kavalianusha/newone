package com.pathbreaker.authentication.service.helper;

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
    public RefreshTokenUtil(AccessTokenUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    private final AccessTokenUtil jwtUtil;
    public String generateRefreshToken(String username) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName",username);
        // Move the calculation of now and expirationDate here
        Date now = new Date();
        long expirationMillis = now.getTime() + 1*60*20000; // Token expiration time (1hr)
        Date expirationDate = new Date(expirationMillis);

        return createToken(claims, username, now, expirationDate);
    }

    public String createToken(Map<String, Object> claims, String subject, Date now, Date expirationDate) throws Exception {
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
