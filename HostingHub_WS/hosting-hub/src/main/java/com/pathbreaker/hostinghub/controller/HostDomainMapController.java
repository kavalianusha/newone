package com.pathbreaker.hostinghub.controller;

import com.pathbreaker.hostinghub.repository.HostDomainMapRepository;
import com.pathbreaker.hostinghub.request.HostDomainMapRequest;
import com.pathbreaker.hostinghub.request.HostDomainMapUpdateRequest;
import com.pathbreaker.hostinghub.response.HostDomainMapResponse;
import com.pathbreaker.hostinghub.response.ResultResponse;
import com.pathbreaker.hostinghub.service.HostDomainMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class HostDomainMapController {

    // Autowire the HostDomainMapService and HostDomainMapRepository
    @Autowired
    private HostDomainMapService hostDomainMapService;

    @Autowired
    private HostDomainMapRepository hostDomainMapRepository;

    // Handle POST request to add a host domain mapping
    @PostMapping("/hostdomainmap/add")
    public ResponseEntity<?> registerUser(@RequestBody HostDomainMapRequest hostDomainMapRequest) {
        return hostDomainMapService.addHostDomainAdd(hostDomainMapRequest);
    }

    // Handle GET request to retrieve all host domains
    @GetMapping("/hostdomainmap/all")
    public ResponseEntity<List<HostDomainMapResponse>> getAllHostDomains() {
        List<HostDomainMapResponse> hostDomainMapResponses = hostDomainMapService.getAllHostDomains();
        return ResponseEntity.ok(hostDomainMapResponses);
    }

    // Handle GET request to retrieve host domain by ID
    @GetMapping("/hostdomainmap/{hostDomainId}")
    public ResponseEntity<HostDomainMapResponse> getHostDomainsById(@PathVariable String hostDomainId) {
        HostDomainMapResponse hostDomainMapResponse = hostDomainMapService.getAllHostDomainsById(hostDomainId);
        try {
            if (hostDomainMapResponse == null) {
                // Return 404 if host domain is not found
                log.info("give me the correct host domain Id to get the details {} : ",hostDomainId);
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(hostDomainMapResponse);
        } catch (Exception e) {
            // Handle exceptions during host domain retrieval
            e.printStackTrace();
            log.info("give me the correct host domain Id to get the details {} : ",hostDomainMapResponse);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Handle PUT request to update host domain
    @PutMapping("/hostdomainmap/{hostDomainId}")
    public ResponseEntity<?> updateAdmin(
            @PathVariable String hostDomainId,
            @RequestBody HostDomainMapUpdateRequest hostDomainMapRequest) {
        try {
            // Check if the host domain with the given ID exists in the database
            if (!hostDomainMapService.existsById(hostDomainId)) {
                ResultResponse result = new ResultResponse();
                result.setResult("Host domain  with ID " + hostDomainId + " not found.");
                log.info("The Host Domain with the given id is not found {} : ",hostDomainId);
                return ResponseEntity.ok(result);
            }
            // Perform the update operation using the service
            hostDomainMapService.updateHostDomain(hostDomainId, hostDomainMapRequest);
            ResultResponse result = new ResultResponse();
            result.setResult("Host Domain with ID " + hostDomainId + " has been updated.");
            log.info("The Host Domain details are updated successfully!!");
            return ResponseEntity.ok(result);

        } catch (Exception ex) {
            // Log the exception
            ex.printStackTrace();

            ResultResponse result = new ResultResponse();
            result.setResult("An error occurred while updating the Host Domain: " + ex.getMessage());
            log.info("An error occurred while updating the Host Domain {}: ", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    // Handle DELETE request to delete host domain by ID
    @DeleteMapping("/hostdomainmap/{hostDomainId}")
    public ResponseEntity<?> deleteHostDomainById(@PathVariable String hostDomainId) {
        try {
            // Try to delete host domain by ID
            hostDomainMapService.deleteHostDomainById(hostDomainId);
            ResultResponse result = new ResultResponse();
            result.setResult("Host Domain with ID " + hostDomainId + " has been deleted.");
            log.info("The Host Domain is deleted successfully!!");
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            // Handle exceptions during host domain deletion
            ResultResponse result = new ResultResponse();
            result.setResult("Host Domain with ID: " + hostDomainId + " not found");
            log.info("The host Domain id is not found in the db {} ", hostDomainId);
            return ResponseEntity.ok(result);
        }
    }

    // Handle GET request to retrieve all domain names
    @GetMapping("/get/alldomains")
    public ResponseEntity<List<String>> getAllDomainNames() {
        List<String> domainNames = hostDomainMapService.getAllDomainNames();
        return ResponseEntity.ok(domainNames);
    }

    @GetMapping("/get/domain/{domainName}")
    public ResponseEntity<?> getDomainDetails(@PathVariable String domainName) {
        return hostDomainMapService.getDomainDetails(domainName);

    }
    @GetMapping("/get/hosting/{hostingProvider}")
    public ResponseEntity<?> getHostingDetails(@PathVariable String hostingProvider) {
        return hostDomainMapService.getHostingDetails(hostingProvider);

    }

    @GetMapping("/get/allproviders")
    public ResponseEntity<List<String>> getAllProviders() {
        List<String> domainProviders = hostDomainMapService.getAllDomainProviders();
        return ResponseEntity.ok(domainProviders);
    }

    // Handle GET request to retrieve all hosting providers
    @GetMapping("/get/allhosting")
    public ResponseEntity<List<String>> getAllHostingProviders() {
        List<String> hostingProviders = hostDomainMapService.getAllHostingProviders();
        return ResponseEntity.ok(hostingProviders);
    }

    @GetMapping("/counts")
    public ResponseEntity<?> getDomainAndHostingCounts() {
        Long domainCount = hostDomainMapService.getDomainCount();
        Long hostingCount = hostDomainMapService.getHostingCount();

        Map<String, Long> counts = new HashMap<>();
        counts.put("domainCount", domainCount);
        counts.put("hostingCount", hostingCount);

        return ResponseEntity.ok(counts);
    }


}






