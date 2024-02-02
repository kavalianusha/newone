package com.pathbreaker.payslip.controller;

import com.pathbreaker.payslip.request.EmployeeLoginRequest;
import com.pathbreaker.payslip.service.EmployeeLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins="*")
@RequestMapping("/employee")
public class EmployeeLoginController {

    @Autowired
    private EmployeeLoginService loginService;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody EmployeeLoginRequest request) {
            return loginService.sendOtpToEmail(request);
        }
    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody EmployeeLoginRequest loginRequest) {
        return loginService.loginAdmin(loginRequest);
    }



}
