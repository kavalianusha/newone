package com.pathbreaker.servicetokens.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pathbreaker.servicetokens.helper.JwtUtil;
import com.pathbreaker.servicetokens.pojo.ResultResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

@Service
public class HomeService {
    private static final String publicKeyPath = "D:\\Spring-Practice-WS\\JwtToken\\src\\main\\resources\\certificates\\publicKey.pem";

    @Autowired
    public JwtUtil jwtUtil;

    public ResponseEntity<?> welcome(HttpServletRequest request) {
        String authHead = request.getHeader("authorization");
        System.out.println("the auth header is :" +authHead);
        boolean valid = jwtUtil.isJwtTokenValid(authHead);
        System.out.println("the validation is "+valid);
        if(valid){
            String text = "Welcome to Jwt token";
            ResultResponse response = new ResultResponse();
            response.setResult(text);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(request);
    }


}
