package com.pathbreaker.servicetokens.helper;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;

import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtil {

    private static final String publicKeyPath = "D:\\Spring-Practice-WS\\JwtToken\\src\\main\\resources\\certificates\\publicKey.pem";
    private static final String privateKeyPath = "D:\\Spring-Practice-WS\\JwtToken\\src\\main\\resources\\certificates\\privateKey.pem";

    public boolean isJwtTokenValid(String token) {
        try {
            buildJWTVerifier().verify(token.replace("Bearer ", ""));
            // if token is valid no exception will be thrown
            return true;
        } catch (CertificateException e) {
            //if CertificateException comes from buildJWTVerifier()
            e.printStackTrace();
            return false;
        } catch (JWTVerificationException e) {
            // if JWT Token in invalid
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            // If any other exception comes
            e.printStackTrace();
            return false;
        }
    }
    private JWTVerifier buildJWTVerifier() throws Exception {
        Algorithm algo = Algorithm.RSA256(loadPublicKey(),null);
        System.out.println("The build jwt verifier algorithm is : "+algo);
        System.out.println(JWT.require(algo).build());
        return JWT.require(algo).build();
    }


    public static RSAPublicKey loadPublicKey() throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(publicKeyPath));
        System.out.println("The key bytes: " + keyBytes);

        String publicKeyPEM = new String(keyBytes);
        publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        System.out.println("The public key pem: " + publicKeyPEM);

        byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
        System.out.println("The decoded public key: " + new String(decoded));

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        System.out.println("The key factory: " + keyFactory);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        System.out.println("The key spec: " + keySpec);

        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }


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

    public String generateTokens(String username) throws Exception {
        Map<String, Object> claims = new HashMap<>();
        // Move the calculation of now and expirationDate here
        Date now = new Date();
        long expirationMillis = now.getTime() + 1*60*60000; // Token expiration time (1 hr)
        Date expirationDate = new Date(expirationMillis);

        return createTokens(claims, username, now, expirationDate);
    }
    public String createTokens(Map<String, Object> claims, String subject, Date now, Date expirationDate) throws Exception
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
