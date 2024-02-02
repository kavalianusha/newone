package com.pathbreaker.servicetokens.service;

import com.pathbreaker.servicetokens.pojo.JwtRequest;
import org.springframework.http.ResponseEntity;

public interface JwtTokenService {


    ResponseEntity<?> generateJwtToken(JwtRequest jwtRequest) throws Exception;





}
