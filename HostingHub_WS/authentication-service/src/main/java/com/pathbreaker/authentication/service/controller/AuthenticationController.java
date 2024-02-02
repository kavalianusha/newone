package com.pathbreaker.authentication.service.controller;

import com.pathbreaker.authentication.service.request.AuthenticationDetailsPayload;
import com.pathbreaker.authentication.service.service.AuthenticationService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDetailsPayload request) throws  Exception{
        return service.login(request);
    }

    // Handle POST request to verify OTP
    /*@PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody AuthenticationDetailsPayload request) {
            return service.verifyOtp(request);
    }*/



}
