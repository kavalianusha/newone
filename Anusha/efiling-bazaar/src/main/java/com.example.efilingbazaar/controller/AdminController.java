
package com.example.efilingbazaar.controller;


import com.example.efilingbazaar.exception.InvalidOtpException;
import com.example.efilingbazaar.request.AdminRequest;
import com.example.efilingbazaar.request.ResetPasswordRequest;
import com.example.efilingbazaar.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.efilingbazaar.entity.Admin;
import com.example.efilingbazaar.service.AdminService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="*")
@Slf4j
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/sendotp")
    public ResponseEntity<ResultResponse> sendOtp(@RequestBody 	AdminRequest admin) {
        try {
            adminService.sendOtp(admin.getEmailId(), admin.getPassword());
            ResultResponse result = new ResultResponse();
            result.setResult("OTP sent to the email address.");
            log.info("otp sent successully {} ", admin.getEmailId());
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            // Invalid email or password
            log.warn("error on login {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult(e.getMessage());
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            log.warn("error on login {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult("Failed to send OTP");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }


    @PostMapping( value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE},produces =  {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResultResponse> login(@RequestBody Admin admin) {
        try {

            adminService.login(admin.getEmailId(), admin.getOtp(), admin.getPassword());
            System.out.println("otp " + admin.getOtp());
            ResultResponse result = new ResultResponse();
            result.setResult("login successful");
            log.info("login successful {} email : ", admin.getEmailId());
            return ResponseEntity.ok(result);

        } catch (InvalidOtpException e) {
            // Handle invalid OTP exception
            ResultResponse result = new ResultResponse();
            result.setResult("Invalid otp or password or email");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        } catch (Exception e) {
            // Handle any other exception
            log.warn("error on login {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult("Failed to login");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }

    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Admin admin) {
        return adminService.logout(admin.getEmailId());
    }

    @PostMapping("/initiate-password-reset")
    public ResponseEntity<ResultResponse> initiatePasswordReset(@RequestBody AdminRequest admin) {
        try {
            adminService.initiatePasswordReset(admin.getEmailId());
            ResultResponse result = new ResultResponse();
            result.setResult("Password reset OTP sent to the email address.");
            log.info("Password reset OTP sent successfully for email: {}", admin.getEmailId());
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
            adminService.resetPassword(request.getEmailId(), request.getOtp(), request.getPassword());
            ResultResponse result = new ResultResponse();
            result.setResult("Password reset successful");
            log.info("Password reset successful for email: {}", request.getEmailId());
            return ResponseEntity.ok(result);
        } catch (InvalidOtpException e) {
            log.warn("Error on password reset: {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult("Invalid OTP or email or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        } catch (Exception e) {
            log.warn("Error on password reset: {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult("Failed to reset password");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

}