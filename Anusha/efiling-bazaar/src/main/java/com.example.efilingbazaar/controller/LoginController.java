package com.example.efilingbazaar.controller;

import com.example.efilingbazaar.request.ResetPasswordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.efilingbazaar.exception.InvalidOtpException;
import com.example.efilingbazaar.request.LogRequest;
import com.example.efilingbazaar.request.LoginRequest;
import com.example.efilingbazaar.request.OtpRequest;
import com.example.efilingbazaar.response.ResultResponse;
import com.example.efilingbazaar.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins="*")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // Endpoint for sending OTP to the email address
    @PostMapping("/sendotp")
    public ResponseEntity<ResultResponse> sendOtp(@RequestBody LoginRequest request) {
        try {
            if (request.getEmailId() == null || request.getPassword() == null) {
                throw new IllegalArgumentException("Email and password cannot be null");
            }
            loginService.sendOtp(request.getEmailId(), request.getPassword());
            ResultResponse result = new ResultResponse();
            result.setResult("OTP sent to the email address.");
            log.info("otp sent successfully for email: {}", request.getEmailId());
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            log.warn("error on login: {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            log.warn("error on login: {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult("Failed to send OTP");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }


    // Endpoint for login using OTP
    @PostMapping( value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE},produces =  {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResultResponse> login(@RequestBody LoginRequest request) {
        try {

            loginService.login(request.getEmailId(), request.getOtp());
            System.out.println("otp " + request.getOtp());
            ResultResponse result = new ResultResponse();
            result.setResult("login successful");
            log.info("login successful {} email : ", request.getEmailId());
            return ResponseEntity.ok(result);

        } catch (InvalidOtpException e) {
            // Handle invalid OTP exception
            //log.warn("error on login {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            //log.info("otp : {} is invalid ", e.getMessage());
            result.setResult("Invalid otp");
            //log.info("otp is invalid");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        } catch (Exception e) {
            // Handle any other exception
            log.warn("error on login {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult("Failed to login");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }

    }

    // Endpoint for logout
    @PostMapping("/logout")
    public ResponseEntity<ResultResponse> logout(@RequestBody LogRequest request) {
        try {
            System.out.println("logout"+request.getEmailId());
            loginService.logout(request.getEmailId());
            ResultResponse result = new ResultResponse();
            result.setResult("Logout successful");
            log.info("logout successful for email id : {} ", request.getEmailId());
            //log.info("email is : {}  ", request.getEmailId());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.warn("error on logout {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            log.warn("No employee found for ID: {}", request.getEmailId()); // Log a warning when no employee is found
            result.setResult("Failed to logout");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
    @PostMapping("/initiate-password-reset")
    public ResponseEntity<ResultResponse> initiatePasswordReset(@RequestBody OtpRequest request) {
        try {
            loginService.initiatePasswordReset(request.getEmailId());
            ResultResponse result = new ResultResponse();
            result.setResult("Password reset OTP sent to the email address.");
            log.info("Password reset OTP sent successfully for email: {}", request.getEmailId());
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            log.warn("Error on initiating password reset: {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            log.warn("Error on initiating password reset: {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult("Failed to send password reset OTP");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResultResponse> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            loginService.resetPassword(request.getEmailId(), request.getOtp(), request.getPassword());
            ResultResponse result = new ResultResponse();
            result.setResult("Password reset successful");
            log.info("Password reset successful for email: {}", request.getEmailId());
            return ResponseEntity.ok(result);
        } catch (InvalidOtpException e) {
            log.warn("Error on password reset: {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult("Invalid OTP");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        } catch (Exception e) {
            log.warn("Error on password reset: {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult("Failed to reset password");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }


}