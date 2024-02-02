package com.example.hostinghub.controller;

import com.example.hostinghub.entity.ItReturnsEntity;
import com.example.hostinghub.request.ItReturnsRequest;
import com.example.hostinghub.request.ItReturnsUpdateRequest;
import com.example.hostinghub.response.AdminResponse;
import com.example.hostinghub.response.ItReturnsResponse;
import com.example.hostinghub.response.ResultResponse;
import com.example.hostinghub.service.ItReturnsService;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ItReturnsController {

    @Autowired
    private ItReturnsService itReturnsService;

    // Endpoint to register a new IT return
    @PostMapping("/api/itreturns")
    public ResponseEntity<?> registerItReturns(@RequestBody ItReturnsRequest itReturnsRequest) {
        return itReturnsService.registerItReturns(itReturnsRequest);
    }

    // Endpoint to get an IT return by its customer ID
    @GetMapping("/api/itreturns/{customerId}")
    public ResponseEntity<?> getItReturnsByCustomerId(@PathVariable String customerId) {
        return itReturnsService.getItReturnsByCustomerId(customerId);
    }

    @GetMapping("/api/itreturns")
    public ResponseEntity<List<ItReturnsResponse>> getAllItReturns() {
        List<ItReturnsResponse> itResponses = itReturnsService.getAllItReturns();
        return ResponseEntity.ok(itResponses);
    }
    // Endpoint to update an IT return
    @PutMapping("/api/itreturns/{customerId}")
    public ResponseEntity<?> updateItReturns(@PathVariable String customerId, @RequestBody ItReturnsUpdateRequest updatedItReturns) {
        return itReturnsService.updateItReturns(customerId, updatedItReturns);

    }

    // Endpoint to delete an IT return
    @DeleteMapping("/api/itreturns/{customerId}")
        public ResponseEntity<?> deleteItReturns(@PathVariable String customerId) {
            return itReturnsService.deleteItreturnsById(customerId);
        }

    }
