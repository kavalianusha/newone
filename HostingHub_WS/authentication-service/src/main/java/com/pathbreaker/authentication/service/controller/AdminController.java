package com.pathbreaker.authentication.service.controller;

import com.pathbreaker.authentication.service.request.TokenDetailsPayload;
import com.pathbreaker.authentication.service.request.TokenDetailsUpdateRequest;
import com.pathbreaker.authentication.service.response.TokenDetailsResponse;
import com.pathbreaker.authentication.service.serviceimpl.TokenDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    public TokenDetailsServiceImpl tokenDetailsService;

    @PostMapping("/token-details")
    public TokenDetailsPayload saveTokenDetails(@RequestBody TokenDetailsPayload request) {
        return tokenDetailsService.saveTokenDetails(request);
    }

    // Read operations
    @GetMapping("/token-details")
    public List<TokenDetailsResponse> getAllTokenDetails() {
        return tokenDetailsService.getAllTokenDetails();
    }

    @GetMapping("/token-details/{id}")
    public TokenDetailsResponse getTokenDetailsById(@PathVariable Long id) {
        return tokenDetailsService.getTokenDetailsById(id);
    }

    @GetMapping("/token-details/client/{clientId}")
    public TokenDetailsResponse getTokenByClientId(@PathVariable String clientId) {
        return tokenDetailsService.getTokenByClientId(clientId);
    }

    // Update operation
    @PutMapping("/token-details/{id}")
    public TokenDetailsUpdateRequest updateTokenDetails(@PathVariable Long id, @RequestBody TokenDetailsUpdateRequest tokenDetailsUpdateRequest) {
        return tokenDetailsService.updateTokenDetails(id,tokenDetailsUpdateRequest);
    }
    // Delete operation
    @DeleteMapping("/token-details/{id}")
    public void deleteTokenDetailsById(@PathVariable Long id) {
        tokenDetailsService.deleteTokenDetailsById(id);
    }


}
