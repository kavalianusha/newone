package com.pathbreaker.servicetokens.serviceimpl;


import com.pathbreaker.servicetokens.pojo.RefreshTokenRequest;
import com.pathbreaker.servicetokens.pojo.ResultResponse;
import com.pathbreaker.servicetokens.helper.RefreshTokenUtil;
import com.pathbreaker.servicetokens.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public RefreshTokenUtil refreshTokenUtil;


    @Override
    public ResponseEntity<?> generateJwtToken(RefreshTokenRequest refreshTokenRequest) throws Exception {

       String jwtToken = refreshTokenUtil.generateJwtToken(refreshTokenRequest.getRefreshToken());

       ResultResponse response = new ResultResponse();
       response.setJwtToken(jwtToken);
       return ResponseEntity.ok(response);
    }

    /*@Override
    public ResponseEntity<?> validateRefreshToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove the "Bearer " prefix
        } else {
            ResultResponse response = new ResultResponse();
            response.setResult("Invalid Token format");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        if (refreshTokenUtil.verifyRefreshToken(token)) {
            ResultResponse response = new ResultResponse();
            response.setResult("Token is valid");
            return ResponseEntity.ok(response);
        } else {
            ResultResponse response = new ResultResponse();
            response.setResult("Token is not valid");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }*/
}


