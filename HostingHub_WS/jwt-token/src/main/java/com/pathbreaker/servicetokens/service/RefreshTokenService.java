package com.pathbreaker.servicetokens.service;


import com.pathbreaker.servicetokens.pojo.RefreshTokenRequest;
import org.springframework.http.ResponseEntity;

public interface RefreshTokenService{

    ResponseEntity<?> generateJwtToken(RefreshTokenRequest refreshTokenRequest) throws Exception;

}
