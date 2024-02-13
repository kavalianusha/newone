package com.pathbreaker.accesstoken.service;

import com.pathbreaker.accesstoken.request.AdminRequest;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    ResponseEntity<?> sendOtpToEmail(AdminRequest request);

    ResponseEntity<?> loginAdmin(AdminRequest loginRequest);

    ResponseEntity<?> sendOtpToPhone(AdminRequest request);
}
