package com.pathbreaker.services.filters.filter;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class AccessToken {

    private static final String publicKeyPath = "D:\\Token_Project\\access-tokens\\src\\main\\resources\\certificates\\publicKey.pem";

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
        System.out.println("the build one is :" +JWT.require(algo).build());
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

}
