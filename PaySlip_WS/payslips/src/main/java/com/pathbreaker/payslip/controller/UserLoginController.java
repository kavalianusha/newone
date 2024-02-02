package com.pathbreaker.payslip.controller;

import com.pathbreaker.payslip.request.EmployeeLoginRequest;
import com.pathbreaker.payslip.request.UserLoginRequest;
import com.pathbreaker.payslip.service.EmployeeLoginService;
import com.pathbreaker.payslip.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins="*")
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody UserLoginRequest userLoginRequest) {
            return userLoginService.sendOtpToEmail(userLoginRequest);
        }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        return userLoginService.loginUser(userLoginRequest);
    }



}
