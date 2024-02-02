package com.pathbreaker.servicetokens.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pathbreaker.servicetokens.pojo.ResultResponse;
import com.pathbreaker.servicetokens.service.HomeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@RestController
@CrossOrigin(origins="*")
public class HomeController {
    private static final String publicKeyPath = "D:\\Spring-Practice-WS\\JwtToken\\src\\main\\resources\\certificates\\publicKey.pem";

    @Autowired
    public HomeService homeService;

    @GetMapping("/welcome/hi")
    public ResponseEntity<?> welcome(HttpServletRequest request) {
        return homeService.welcome(request);
    }

    @GetMapping("/hello")
    public ResultResponse hello(HttpServletRequest request) {
        String authHead = request.getHeader("authorization");
        boolean valid = isTokenValid(authHead);
        String text = "I am Anusha and not hell0 to access this page";
        ResultResponse result = new ResultResponse();
        result.setResult(text+ "  the validation  " +valid);
        return result;
    }

    public boolean isTokenValid(String token) {
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
        Algorithm algo = Algorithm.RSA256(loadPublicKey(), null);
        return JWT.require(algo).build();
    }

    public static RSAPublicKey loadPublicKey() throws Exception {
            byte[] keyBytes = Files.readAllBytes(Paths.get(publicKeyPath));
            String publicKeyPEM = new String(keyBytes);
            publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "");
            publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");
            publicKeyPEM = publicKeyPEM.replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        }

}
