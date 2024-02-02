package com.example.springproject.serviceimpl;

import com.example.springproject.entity.JwtRequest;
import com.example.springproject.entity.ResultResponse;
import com.example.springproject.helper.JwtUtil;
import com.example.springproject.service.CustomUserDetailsService;
import com.example.springproject.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    @Autowired
    public JwtUtil jwtUtil;

    @Override
    public ResponseEntity<?> generateJwtToken(JwtRequest jwtRequest) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad credentials");
        }
        //fine area
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUserName());

        // Generate JWT token
        String token = this.jwtUtil.generateToken(jwtRequest.getUserName());

        System.out.println("JWT token is: " + token);
        System.out.println("the user details are : " + userDetails);

        ResultResponse response = new ResultResponse();
        response.setToken(token);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> validateToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove the "Bearer " prefix
        } else {
            ResultResponse response = new ResultResponse();
            response.setResult("Invalid Token format");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        if (isTokenValid(token)) {
            ResultResponse response = new ResultResponse();
            response.setResult("Token is valid");
            return ResponseEntity.ok(response);
        } else {
            ResultResponse response = new ResultResponse();
            response.setResult("Token is not valid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    public boolean isTokenValid(String token) {
        try {
            // Additional validation logic if needed
            return jwtUtil.validateTokenWithPrivateKey(token);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
