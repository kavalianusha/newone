package com.pathbreaker.hostinghub.controller;

import com.pathbreaker.hostinghub.request.ItReturnsRequest;
import com.pathbreaker.hostinghub.request.ItReturnsUpdateRequest;
import com.pathbreaker.hostinghub.response.ItReturnsResponse;
import com.pathbreaker.hostinghub.service.ItReturnsService;
import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
