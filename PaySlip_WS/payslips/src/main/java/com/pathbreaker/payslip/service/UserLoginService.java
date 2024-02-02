package com.pathbreaker.payslip.service;

import com.pathbreaker.payslip.request.UserLoginRequest;
import org.springframework.http.ResponseEntity;

public interface UserLoginService {
    ResponseEntity<?> sendOtpToEmail(UserLoginRequest userLoginRequest);

    ResponseEntity<?> loginUser(UserLoginRequest userLoginRequest);
}
