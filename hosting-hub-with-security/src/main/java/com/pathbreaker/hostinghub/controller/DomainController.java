package com.pathbreaker.hostinghub.controller;

import com.pathbreaker.hostinghub.request.DomainRequest;
import com.pathbreaker.hostinghub.request.DomainUpdateRequest;
import com.pathbreaker.hostinghub.response.DomainResponse;
import com.pathbreaker.hostinghub.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class DomainController {

    @Autowired
    public DomainService domainService;

    @PostMapping("/api/domain")
    public ResponseEntity<?> registerDomain(@RequestBody DomainRequest domainRequest) {
        return domainService.registerDomain(domainRequest);
    }

    // Endpoint to get a domain by its ID
    @GetMapping("/api/domain/{domainId}")
    public ResponseEntity<?> getDomainById(@PathVariable String domainId) {
        return domainService.getDomainById(domainId);
    }

    // Endpoint to get all domains
    @GetMapping("/api/domain")
    public List<DomainResponse> getAllDomains() {
        // Call the service to get all domains
        return domainService.getAllDomains();
    }

    // Endpoint to update a domain
    @PutMapping("/api/domain/{domainId}")
    public ResponseEntity<?> updateDomain(@PathVariable String domainId, @RequestBody DomainUpdateRequest updatedDomainRequest) {
        return domainService.updateDomain(domainId, updatedDomainRequest);
    }

    // Endpoint to delete a domain
    @DeleteMapping("/api/domain/{domainId}")
    public ResponseEntity<?> deleteDomain(@PathVariable String domainId) {
        return domainService.deleteDomainById(domainId);
    }

}
