package com.pathbreaker.accesstoken.controller;

import com.pathbreaker.accesstoken.request.AdminRequest;
import com.pathbreaker.accesstoken.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins="*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody AdminRequest request) {
        return adminService.sendOtpToEmail(request);
    }

    @PostMapping("/phone/send-otp")
    public ResponseEntity<?> sendOtpToPhone(@RequestBody AdminRequest request) {
        return adminService.sendOtpToPhone(request);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody AdminRequest loginRequest) {
        return adminService.loginAdmin(loginRequest);
    }


}
