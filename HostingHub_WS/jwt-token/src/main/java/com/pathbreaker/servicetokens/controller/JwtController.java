package com.pathbreaker.servicetokens.controller;

import com.pathbreaker.servicetokens.pojo.JwtRequest;
import com.pathbreaker.servicetokens.pojo.RefreshTokenRequest;
import com.pathbreaker.servicetokens.service.CustomUserDetailsService;
import com.pathbreaker.servicetokens.service.RefreshTokenService;
import com.pathbreaker.servicetokens.serviceimpl.JwtTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Autowired
    private JwtTokenServiceImpl jwtTokenService;

    @Autowired
    private RefreshTokenService refreshTokenService;


    @RequestMapping(value = "/generate/jwttoken",method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtTokenService.generateJwtToken(jwtRequest);
    }

    @PostMapping("/generate/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws Exception {
        return refreshTokenService.generateJwtToken(refreshTokenRequest);
    }

    /* @GetMapping("/validate/jwttoken")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        return jwtTokenService.validateToken(token);
    }*/

/*    @GetMapping("/validate/refresh")
    public ResponseEntity<?> validateRefreshToken(@RequestHeader("Authorization") String token) {
        return refreshTokenService.validateRefreshToken(token);
    }*/

}
