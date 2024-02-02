package com.pathbreaker.servicetokens.serviceimpl;

import com.pathbreaker.servicetokens.entity.UserEntity;
import com.pathbreaker.servicetokens.helper.RefreshTokenUtil;
import com.pathbreaker.servicetokens.pojo.ResultResponse;
import com.pathbreaker.servicetokens.repository.UserRepository;
import com.pathbreaker.servicetokens.service.JwtTokenService;
import com.pathbreaker.servicetokens.helper.JwtUtil;
import com.pathbreaker.servicetokens.pojo.JwtRequest;
import com.pathbreaker.servicetokens.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    public CustomUserDetailsService customUserDetailsService;
    @Autowired
    public JwtUtil jwtUtil;
    @Autowired
    public RefreshTokenUtil refreshTokenUtil;

    @Autowired
    public UserRepository userRepository;

    @Override
    public ResponseEntity<?> generateJwtToken(JwtRequest jwtRequest) throws Exception {

        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));
        //fine area
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUserName());

        String jwtToken = jwtUtil.generateTokens(jwtRequest.getUserName());

        // Generate Refresh token
        String refreshToken = jwtUtil.generateTokens(jwtRequest.getUserName());

        System.out.println("JWT token is: " + jwtToken);
        System.out.println("The user details are: " + userDetails);
        System.out.println("Refresh token is: " + refreshToken);

        UserEntity user = userRepository.findByUserName(jwtRequest.getUserName());

        if (user != null) {
            // Set the refresh token and save the user
            user.setRefreshToken(refreshToken);
            userRepository.save(user);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        if(jwtRequest.equals(refreshToken)){
            System.out.println("both are same");
        }
        else {
            System.out.println("the tokens are not same");
        }

        ResultResponse response = new ResultResponse();
        response.setJwtToken(jwtToken);
        response.setRefreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }

   /* @Override
    public ResponseEntity<?> validateToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove the "Bearer " prefix
        } else {
            ResultResponse response = new ResultResponse();
            response.setResult("Invalid Token format");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        if (jwtUtil.verifyJwtToken(token)) {
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
