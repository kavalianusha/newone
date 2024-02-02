package com.example.springproject.service;

import com.example.springproject.entity.JwtRequest;
import org.springframework.http.ResponseEntity;

public interface JwtTokenService {


    ResponseEntity<?> generateJwtToken(JwtRequest jwtRequest) throws Exception;


    ResponseEntity<?> validateToken(String token);


}
