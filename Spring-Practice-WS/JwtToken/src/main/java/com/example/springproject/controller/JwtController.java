package com.example.springproject.controller;

import com.example.springproject.entity.JwtRequest;
import com.example.springproject.entity.ResultResponse;
import com.example.springproject.helper.JwtUtil;
import com.example.springproject.service.CustomUserDetailsService;
import com.example.springproject.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class JwtController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtTokenService jwtTokenService;


    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        return jwtTokenService.validateToken(token);
    }
        // Extract the token from the Authorization header
        @RequestMapping(value = "/token",method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtTokenService.generateJwtToken(jwtRequest);
    }
}
