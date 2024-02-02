package com.pathbreaker.hostinghub.service;

import com.pathbreaker.hostinghub.entity.DomainEntity;
import com.pathbreaker.hostinghub.entity.HostDomainMapEntity;
import com.pathbreaker.hostinghub.entity.HostingEntity;
import com.pathbreaker.hostinghub.exception.AdminException;

import com.pathbreaker.hostinghub.mappers.DomainMappers;
import com.pathbreaker.hostinghub.mappers.HostDomainMapMapper;
import com.pathbreaker.hostinghub.passwordencrypt.PasswordEncoder;
import com.pathbreaker.hostinghub.repository.DomainRepository;
import com.pathbreaker.hostinghub.repository.HostDomainMapRepository;
import com.pathbreaker.hostinghub.repository.HostingRepository;
import com.pathbreaker.hostinghub.request.HostDomainMapRequest;
import com.pathbreaker.hostinghub.request.HostDomainMapUpdateRequest;
import com.pathbreaker.hostinghub.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HostDomainMapService {

    private HostDomainMapRepository hostDomainMapRepository;
    protected HostDomainMapMapper hostDomainMapMapper;

    private DomainRepository domainRepository;
    private PasswordEncoder passwordEncoder;

    private HostingRepository hostingRepository;

    private DomainMappers domainMappers;

    // Constructor with autowired dependencies
    @Autowired
    public HostDomainMapService(HostDomainMapRepository hostDomainMapRepository,
                                HostDomainMapMapper hostDomainMapMapper,
                                DomainRepository domainRepository,
                                PasswordEncoder passwordEncoder,
                                DomainMappers domainMappers,
                                HostingRepository hostingRepository
    ){
        this.hostDomainMapRepository = hostDomainMapRepository;
        this.hostDomainMapMapper = hostDomainMapMapper;
        this.domainRepository = domainRepository;
        this.domainMappers = domainMappers;
        this.hostingRepository = hostingRepository;
        this.passwordEncoder = passwordEncoder;
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

        System.out.println(hostDomainMapEntity);
        System.out.println(hostDomainMapRequest);
        // Log the entity before saving
        log.info("Entity before saving: {}", hostDomainMapEntity);

        // Save the entity
        hostDomainMapRepository.save(hostDomainMapEntity);
        // Create a new result response
        ResultResponse result = new ResultResponse();
        result.setResult("Hosting and Domain registered successfully with payment of : " + hostDomainMapRequest.getPayment());

        log.info("Hosting and Domain registered successfully with payment of :{}",
                hostDomainMapRequest.getPayment());

        return ResponseEntity.ok(result);
    }

    // Method for retrieving all Host-Domain mappings
    public List<HostDomainMapResponse> getAllHostDomains() {
        List<HostDomainMapEntity> hostDomainMapEntities = hostDomainMapRepository.findAll();
        log.info("The size of the host domain mappers is {} : ", hostDomainMapEntities.size());
        log.info("The retrieved host domain mappers are {} : ", hostDomainMapEntities);
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

    public long getDomainCount() {
        return domainRepository.count();
    }

    // Method for retrieving all hosting providers
    public long getHostingCount() {
        return hostingRepository.count();
    }

    public List<String> getAllDomainProviders() {
        log.info("The providers service is hitted :{}",domainRepository.findAllDomainProviders().size());
        return domainRepository.findAllDomainProviders();
    }

    public ResponseEntity<?> getDomainDetails(String domainName) {
        DomainEntity domainEntity = domainRepository.findDomainDetails(domainName);
        if (domainEntity != null) {
            DomainResponseView domainResponse = domainMappers.responseToView(domainEntity);
            String decryptedPassword = passwordEncoder.decryptPassword(domainResponse.getPassword());
            domainResponse.setPassword(decryptedPassword);
            // Log the decrypted password to the console
            System.out.println("Decrypted Password: " + decryptedPassword);
            log.info("the domain details userName is : ");
            return ResponseEntity.ok(domainResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> getHostingDetails(String hostingProvider) {
        HostingEntity hostingEntity = hostingRepository.findHostingDetails(hostingProvider);
        if (hostingEntity != null) {
            HostingResponseView hostingResponseView = hostDomainMapMapper.responseToView(hostingEntity);
            String decryptedPassword = passwordEncoder.decryptPassword(hostingResponseView.getPassword());
            hostingResponseView.setPassword(decryptedPassword);
            // Log the decrypted password to the console
            System.out.println("Decrypted Password: " + decryptedPassword);
            log.info("the hosting  details userName and password is : ");
            return ResponseEntity.ok(hostingResponseView);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}