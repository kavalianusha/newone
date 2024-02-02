package com.example.hostinghub.controller;


import com.example.hostinghub.request.HostingRequest;
import com.example.hostinghub.request.HostingUpdateRequest;
import com.example.hostinghub.response.HostingResponse;
import com.example.hostinghub.response.ResultResponse;
import com.example.hostinghub.service.HostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
//@CrossOrigin(origins="*")
public class HostingAddController {
    @Autowired
    public HostingService hostServices;

    @PostMapping("/api/hosting")
    public ResponseEntity<?> saveHosting(@RequestBody HostingRequest reqDto) {

        return hostServices.saveHosting(reqDto);
    }
    // With the help of GetMapping .We can get the data
    //Using List to get all data
    @GetMapping("/api/hosting")
    public List<HostingResponse> getAllHosting(){

       return hostServices.getAllHosting();

    }

    @GetMapping("/api/hosting/{hostingId}")
    public ResponseEntity<?> hostingData(@PathVariable String hostingId) {
        return hostServices.getHostingById(hostingId);
    }

    //We want to update data using PutMapping .with the help of Id .We will update the data
    @PutMapping("/api/hosting/{hostingId}")
    public ResponseEntity<ResultResponse> updateHosting(@PathVariable String hostingId, @RequestBody HostingUpdateRequest hostingUpdateRequest) {
        // Call the service to update the domain
        ResultResponse result = hostServices.updateHosting(hostingId, hostingUpdateRequest);
        // Check the result and return appropriate response
        if (result.getResult().equals("Update successful")) {
            return ResponseEntity.ok(result); // Return a 200 OK response with the result
        } else if (result.getResult().equals("Domain not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result); // Return a 404 Not Found response with the result
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result); // Return a 500 Internal Server Error response with the result
        }

    }
    //We want delete Particular Id.using DeleteMapping
    @DeleteMapping("/api/hosting/{hostingId}")
    public ResponseEntity<?> deleteHosting(@PathVariable String hostingId) {
        return hostServices.deleteHostingById(hostingId);
    }


}
