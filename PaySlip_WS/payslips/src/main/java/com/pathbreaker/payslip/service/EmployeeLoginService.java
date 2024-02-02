package com.pathbreaker.payslip.service;

import com.pathbreaker.payslip.request.EmployeeLoginRequest;
import org.springframework.http.ResponseEntity;


public interface EmployeeLoginService {

    ResponseEntity<?> sendOtpToEmail(EmployeeLoginRequest request);

    ResponseEntity<?> loginAdmin(EmployeeLoginRequest loginRequest);
}
