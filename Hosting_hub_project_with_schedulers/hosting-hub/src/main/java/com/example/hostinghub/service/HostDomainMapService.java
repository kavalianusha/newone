package com.example.hostinghub.service;

import com.example.hostinghub.entity.HostDomainMapEntity;
import com.example.hostinghub.exception.AdminException;

import com.example.hostinghub.mappers.HostDomainMapMapper;
import com.example.hostinghub.repository.DomainRepository;
import com.example.hostinghub.repository.HostDomainMapRepository;
import com.example.hostinghub.repository.HostingRepository;
import com.example.hostinghub.request.HostDomainMapRequest;
import com.example.hostinghub.request.HostDomainMapUpdateRequest;
import com.example.hostinghub.response.HostDomainMapResponse;
import com.example.hostinghub.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HostDomainMapService {

    private HostDomainMapRepository hostDomainMapRepository;
    protected HostDomainMapMapper hostDomainMapMapper;

    private DomainRepository domainRepository;

    private HostingRepository hostingRepository;

    // Constructor with autowired dependencies
    @Autowired
    public HostDomainMapService(HostDomainMapRepository hostDomainMapRepository,
                                HostDomainMapMapper hostDomainMapMapper,
                                DomainRepository domainRepository,
                                HostingRepository hostingRepository
    ){
        this.hostDomainMapRepository = hostDomainMapRepository;
        this.hostDomainMapMapper = hostDomainMapMapper;
        this.domainRepository = domainRepository;
        this.hostingRepository = hostingRepository;
    }

    // Method for adding a new Host-Domain mapping
    public ResponseEntity<?> addHostDomainAdd(HostDomainMapRequest hostDomainMapRequest) {
        // Generate a new hostDomainId
        String highestHostDomainId = hostDomainMapRepository.findHighestHostDomainId();
        int numericPart = 1;

        if (highestHostDomainId != null && highestHostDomainId.length() > 4) {
            numericPart = Integer.parseInt(highestHostDomainId.substring(4)) + 1;
        }

        String idFormat = "HD%03d"; // The %03d means a 3-digit zero-padded number
        String highestId = String.format(idFormat, numericPart);
        hostDomainMapRequest.setHostDomainId(highestId);

        // Convert request to entity and save it
        HostDomainMapEntity hostDomainMapEntity = hostDomainMapMapper.entityToRequest(hostDomainMapRequest);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate registrationDate = LocalDate.parse(hostDomainMapRequest.getRegistrationDate(), formatter);
        LocalDate expiryDate = LocalDate.parse(hostDomainMapRequest.getExpiryDate(), formatter);
        long daysLeft = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);

        hostDomainMapEntity.setDaysLeft(daysLeft);

        // Log the calculated daysLeft
        log.info("Calculated daysLeft: {}", daysLeft);

        // Log the entity before saving
        log.info("Entity before saving: {}", hostDomainMapEntity);

        // Save the entity
        HostDomainMapEntity savedEntity = hostDomainMapRepository.save(hostDomainMapEntity);

        // Log the saved entity
        log.info("Saved HostDomainMapEntity: {}", savedEntity);

        // Create a new result response
        ResultResponse result = new ResultResponse();
        result.setResult("Hosting and Domain registered successfully with payment of : " + hostDomainMapRequest.getPayment()
                + ". Days left: " + daysLeft);

        log.info("Hosting and Domain registered successfully with payment of : {}. Days left: {}",
                hostDomainMapRequest.getPayment(), daysLeft);

        return ResponseEntity.ok(result);
    }

    // Method for retrieving all Host-Domain mappings
    public List<HostDomainMapResponse> getAllHostDomains() {
        List<HostDomainMapEntity> hostDomainMapEntities = hostDomainMapRepository.findAll();
        log.info("The size of the host domain mappers is {} : ", hostDomainMapEntities.size());
        log.info("The retrieved host domain mappers are {} : ", hostDomainMapEntities);

        // Calculate daysLeft for each entity
        hostDomainMapEntities.forEach(hostDomainMapEntity -> {
            String expiryDateString = hostDomainMapEntity.getExpiryDate().trim();
            log.info("Expiry date string: {}", expiryDateString);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate expiryDate = LocalDate.parse(expiryDateString, formatter);
                long daysLeft = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
                hostDomainMapEntity.setDaysLeft(daysLeft);
            } catch (DateTimeParseException e) {
                log.error("Error parsing expiry date: {}", e.getMessage());
                // Handle the parsing error, perhaps set a default value or log it for further investigation.
            }
        });

        return hostDomainMapMapper.responseToEntity(hostDomainMapEntities);
    }

    // Method for retrieving a Host-Domain mapping by its ID
    public HostDomainMapResponse getAllHostDomainsById(String hostDomainId) {
        Optional<HostDomainMapEntity> hostDomainMapOptional = hostDomainMapRepository.findById(hostDomainId);

        if (hostDomainMapOptional.isPresent()) {
            log.info("The size of the host domain is {} : ", hostDomainMapOptional.isPresent());
            log.info("The retrieved host domain are {} : ", hostDomainMapOptional);
            return hostDomainMapMapper.entityToResponse(hostDomainMapOptional.get());// Return the mapped result
        } else {
            return null; // Return null if the customer is not found
        }
    }

    // Method for checking if a Host-Domain mapping with a specified ID exists
    public boolean existsById(String hostDomainId) {
        return hostDomainMapRepository.existsById(hostDomainId);
    }

    // Method for updating an existing Host-Domain mapping
    public HostDomainMapEntity updateHostDomain(String hostDomainId, HostDomainMapUpdateRequest hostDomainMapRequest) {
        // Find the existing host domain entity by hostDomainId
        HostDomainMapEntity existingHostDomain = hostDomainMapRepository.findByHostDomainId(hostDomainId);

        // Check if the host domain exists
        if (existingHostDomain == null) {
            log.info("The Host Domain id is not found in the db {}", existingHostDomain.getHostDomainId());
            ResultResponse response = new ResultResponse();
            response.setResult("Host Domain with ID " + hostDomainId + " not found.");
            throw new AdminException(response);
        }

        // Update the existing entity with the new values
        HostDomainMapEntity newHostDomain = hostDomainMapMapper.updateEntityFromRequest(hostDomainMapRequest, existingHostDomain);

        // Parse registration and expiry dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate registrationDate = LocalDate.parse(hostDomainMapRequest.getRegistrationDate(), formatter);
        LocalDate expiryDate = LocalDate.parse(hostDomainMapRequest.getExpiryDate(), formatter);

        // Calculate daysLeft based on the updated registration and expiry dates
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);

        // Set the calculated daysLeft in the updated entity
        newHostDomain.setDaysLeft(daysLeft);

        // Save the updated entity
        HostDomainMapEntity savedEntity = hostDomainMapRepository.save(newHostDomain);

        // Return the saved entity
        return savedEntity;
    }



    // Method for deleting a Host-Domain mapping by its ID
    public void deleteHostDomainById(String hostDomainId) {
        HostDomainMapEntity hostDomainMapEntity = hostDomainMapRepository.findByHostDomainId(hostDomainId);
        if (hostDomainMapEntity == null) {
            log.info("The Host domain id is not found in the db {} ", hostDomainMapEntity.getHostDomainId());
            throw new AdminException("Host domain with ID " + hostDomainId + " not found.");
        }
        hostDomainMapRepository.deleteById(hostDomainId);
    }

    // Method for retrieving all domain names
    public List<String> getAllDomainNames() {
        return domainRepository.findAllDomainNames();
    }

    // Method for retrieving all hosting providers
    public List<String> getAllHostingProviders() {
        return hostingRepository.findAllHostingProvider();
    }
}