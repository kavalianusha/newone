package com.example.springproject.helper;

//method for generating jwt token
// expiry time
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final String privateKeyPath = "D:\\Spring-Practice-WS\\JwtToken\\src\\main\\resources\\certificates\\privateKey.pem";
    private static final String publicKeyPath = "D:\\Spring-Practice-WS\\JwtToken\\src\\main\\resources\\certificates\\publicKey.pem";

    private static PrivateKey loadPrivateKey() throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(privateKeyPath));
        String privateKeyPEM = new String(keyBytes);

        // Remove the first and last lines of the PEM private key
        privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "");
        privateKeyPEM = privateKeyPEM.replace("-----END PRIVATE KEY-----", "");

        // Remove newline characters
        privateKeyPEM = privateKeyPEM.replaceAll("\\s", "");

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.getDecoder().decode(privateKeyPEM);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }



    private static PublicKey loadPublicKey() throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(publicKeyPath));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    public String generateToken(String username) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) throws Exception {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + 3600000); // Token expiration time (1 hour)

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(loadPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }


    public boolean validateTokenWithPrivateKey(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(loadPrivateKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            // Additional validation logic if needed

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
