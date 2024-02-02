package com.pathbreaker.authentication.service.service;

import com.pathbreaker.authentication.service.request.AuthenticationDetailsPayload;
import org.springframework.http.ResponseEntity;
//import com.pathbreaker.authentication.service.response.AdminResponseUserName;

public interface AuthenticationService {

    public ResponseEntity<?> login(AuthenticationDetailsPayload request) throws Exception;

    public void validateRequest(AuthenticationDetailsPayload request);

    public ResponseEntity<?> generateTokens(AuthenticationDetailsPayload request) throws Exception;

    ResponseEntity<?> verifyOtp(AuthenticationDetailsPayload request);

    //public AdminResponseUserName getAdminByUserName(String userName);
}
