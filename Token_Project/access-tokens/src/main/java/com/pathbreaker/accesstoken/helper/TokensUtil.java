package com.pathbreaker.accesstoken.helper;


import com.pathbreaker.accesstoken.exception.Exceptions;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class TokensUtil {

    private static final String privateKeyPath = "D:\\Token_Project\\access-tokens\\src\\main\\resources\\certificates\\privateKey.pem";

    public static RSAPrivateKey loadPrivateKey() throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get(privateKeyPath));
        System.out.println("the key bytes : "+keyBytes);
        String privateKeyPEM = new String(keyBytes);
        System.out.println("the private key pem : "+privateKeyPEM);
        privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(privateKeyPEM);
        System.out.println("the decoded is : "+decoded);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        System.out.println("the key factory is : "+keyFactory);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        System.out.println("the key spec is the "+keySpec);

        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

    public String generateAccessToken(String username) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        // Move the calculation of now and expirationDate here
        Date now = new Date();
        long expirationMillis = now.getTime() + 1*60*60000; // Token expiration time (1 hr)
        Date expirationDate = new Date(expirationMillis);

        return createToken(claims, username, now, expirationDate);
    }

    public String generateRefreshToken(String username) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        // Move the calculation of now and expirationDate here
        Date now = new Date();
        long expirationMillis = now.getTime() + 12 * 60 * 60000; // Token expiration time (12 hrs)
        Date expirationDate = new Date(expirationMillis);

        return createToken(claims, username, now, expirationDate);
    }
    public String createToken(Map<String, Object> claims, String subject, Date now, Date expirationDate) throws Exception
    {
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(loadPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
        return token;
    }

}
