package com.example.hostinghub.service;

import com.example.hostinghub.entity.HostingEntity;
import com.example.hostinghub.entity.PasswordsEntity;
import com.example.hostinghub.mappers.HostingMapper;
import com.example.hostinghub.passwordencrypt.PasswordEncoder;
import com.example.hostinghub.repository.HostingRepository;
import com.example.hostinghub.repository.PasswordRepository;
import com.example.hostinghub.request.HostingRequest;
import com.example.hostinghub.request.HostingUpdateRequest;
import com.example.hostinghub.response.HostingResponse;
import com.example.hostinghub.response.PasswordsResponse;
import com.example.hostinghub.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Slf4j
public class HostingService {

    // Autowired HostingRepository and HostingMapper
    public HostingRepository hostingAddRepo;
    public HostingMapper hostAddMapping;

    public PasswordRepository passwordRepository;

    public PasswordEncoder passwordEncoder;

    // Constructor with autowired dependencies
    @Autowired
    public  HostingService(HostingRepository hostingAddRepo,
                           HostingMapper hostAddMapping,
                           PasswordRepository passwordRepository,
                           PasswordEncoder passwordEncoder){
        this.hostAddMapping = hostAddMapping;
        this.hostingAddRepo = hostingAddRepo;
        this.passwordRepository = passwordRepository;
        this.passwordEncoder= passwordEncoder;
    }

    // Method to generate a new Hosting ID
    public String generateHostingId() {
        String hostIdDetails = hostingAddRepo.findHighestHostId();

        int numericPart = 1;

        if (hostIdDetails != null) {
            numericPart = Integer.parseInt(hostIdDetails.substring(2)) + 1;
        }

        String idFormat = "HA%03d";
        System.out.println("the id is :" + String.format(idFormat,numericPart));
        return String.format(idFormat, numericPart);

    }

    // Method to retrieve all Hosting records
    public List<HostingResponse> getAllHosting() {
        try {
            // Finding all domain entities
            List<HostingEntity> hostingEntityList = hostingAddRepo.findAll();
            log.info("The size of the domains is: {}", hostingEntityList.size());

            List<HostingResponse> hostingResponses = new ArrayList<>();

            for (HostingEntity hostingEntity : hostingEntityList) {
                HostingResponse response = hostAddMapping.responseToEntity(hostingEntity);
                // Decrypt the password in DomainEntity
                System.out.println(response.getPasswordsResponse());
                PasswordsResponse passwordsResponse = response.getPasswordsResponse();

                LocalDate expiryDate = LocalDate.parse(response.getExpiryDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(),expiryDate);
                response.setDaysLeft(daysLeft);

                if(Objects.nonNull(passwordsResponse)){
                    String decryptedPassword = passwordEncoder.decryptPassword(passwordsResponse.getPassword());
                    response.setPassword(decryptedPassword);
                    System.out.println(decryptedPassword);
                    passwordsResponse.setPassword(decryptedPassword);
                }
                hostingResponses.add(response);
            }

            return hostingResponses;
        } catch (Exception e) {
            // Handle any exceptions and log an error message
            log.error("Error occurred while retrieving all domains: " + e.getMessage(),e);
            return null;
        }
    }

    public ResponseEntity<?> getHostingById(String hostingId) {

        Optional<HostingEntity> hostingEntity = hostingAddRepo.findById(hostingId);

        try {
            HostingEntity hosting = hostingEntity.get();
            HostingResponse response = hostAddMapping.responseToEntity(hosting);

            // Decrypt the password in DomainEntity
            String decryptedPassword = passwordEncoder.decryptPassword(response.getPassword());
            response.setPassword(decryptedPassword);
            response.getPasswordsResponse().setPassword(decryptedPassword);

            LocalDate expiryDate = LocalDate.parse(response.getExpiryDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(),expiryDate);
            response.setDaysLeft(daysLeft);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            ResultResponse response = new ResultResponse();
            response.setResult("The hosting with id "+hostingId+ " is not found in DB");
            return ResponseEntity.ok(response);

        }
    }

    // Method to save a new Hosting record
    public ResponseEntity<?> saveHosting(HostingRequest reqDto) {
        try {
            String hostingId = generateHostingId();
            reqDto.setHostingId(hostingId);

            HostingEntity hostingEntity = hostAddMapping.entityToRequest(reqDto);
            hostingEntity.setPassword(passwordEncoder.encryptPassword(hostingEntity.getPassword()));


            PasswordsEntity passwordsEntity = hostAddMapping.requestToPasswordEntity(reqDto);

            String highestPasswordId = passwordRepository.findHighestPasswordId();
            int numericPartpwd = 1;

            if (highestPasswordId != null) {
                numericPartpwd = Integer.parseInt(highestPasswordId.substring(3)) + 1;
            }

            String idFormatPwd = "PWD%03d";
            String passwordId = String.format(idFormatPwd, numericPartpwd);
            passwordsEntity.setPasswordId(passwordId);
            passwordsEntity.setPassword(passwordEncoder.encryptPassword(passwordsEntity.getPassword()));

            LocalDate registrationDate = LocalDate.parse(reqDto.getRegistrationDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate expiryDate = LocalDate.parse(reqDto.getExpiryDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            long daysLeft = ChronoUnit.DAYS.between(registrationDate, expiryDate);
            passwordsEntity.setDaysLeft(daysLeft);
            hostingEntity.setDaysLeft(daysLeft);

            hostingEntity.setPasswordsEntity(passwordsEntity);

            hostingAddRepo.save(hostingEntity);

            ResultResponse responseResult = new ResultResponse();
            log.info("Adding new hosting information {} : ",hostingEntity);

            responseResult.setResult("Registration successfully for hosting");
            return ResponseEntity.ok(responseResult);
        } catch (Exception e) {
            log.error("Error saving hosting information",  e);

            ResultResponse errorResponse = new ResultResponse();
            errorResponse.setResult("Error saving hosting information");
            return ResponseEntity.ok(errorResponse);
        }
    }

    // Method to update an existing Hosting record
    public ResultResponse updateHosting(String hostingId, HostingUpdateRequest hostingUpdateRequest) {
        try {
            HostingEntity hostingEntity = hostingAddRepo.findByHostingId(hostingId);

            if (hostingEntity != null) {
                // Use domainMappers to update the properties
                HostingEntity updatedEntity = hostAddMapping.updateEntityFromRequest(hostingEntity,hostingUpdateRequest);

                PasswordsEntity passwordsEntity = hostAddMapping.requestToUpdatePasswordEntity(hostingUpdateRequest);
                passwordsEntity.setPasswordId(hostingEntity.getPasswordsEntity().getPasswordId());

                updatedEntity.setPasswordsEntity(passwordsEntity);

                passwordsEntity.setPassword(passwordEncoder.encryptPassword(passwordsEntity.getPassword()));
                updatedEntity.setPassword(passwordEncoder.encryptPassword(updatedEntity.getPassword()));

                LocalDate registrationDate = LocalDate.parse(hostingUpdateRequest.getRegistrationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate expiryDate = LocalDate.parse(hostingUpdateRequest.getExpiryDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                long daysLeft = ChronoUnit.DAYS.between(registrationDate, expiryDate);
                passwordsEntity.setDaysLeft(daysLeft);
                hostingEntity.setDaysLeft(daysLeft);

                boolean isUserNameOrPasswordUpdated =
                        Objects.nonNull(updatedEntity.getUserName())
                                || Objects.nonNull(updatedEntity.getPassword());

                if (isUserNameOrPasswordUpdated) {
                    passwordsEntity.setUpdateDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }

                // Set the updated password entity in the existing domain entity
                updatedEntity.setDaysLeft(daysLeft);

                hostingAddRepo.save(updatedEntity);

                ResultResponse result = new ResultResponse();
                log.info("Update successful for hosting {}. Updated hosting: {}", hostingId, hostingUpdateRequest);
                result.setResult("Update successful");

                return result;
            } else {
                ResultResponse result = new ResultResponse();
                log.info("hosting not found for ID: {}", hostingId);
                result.setResult("hosting not found");

                return result;
            }
        } catch (Exception e) {
            log.error("Error occurred during hosting update: " + e.getMessage());
            ResultResponse errorResult = new ResultResponse();
            errorResult.setResult("Error occurred during hosting update");

            return errorResult;
        }
    }

    // Method to delete a Hosting record by ID
    public ResponseEntity<?> deleteHostingById(String hostingId) {
        HostingEntity hostingEntity = hostingAddRepo.findByHostingId(hostingId);

        if (hostingEntity == null) {
            ResultResponse response = new ResultResponse();
            response.setResult("Hosting with " + hostingId + " is not found in the database.");
            log.info("Hosting with " + hostingId + " is not found in the database.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Delete associated PasswordsEntity
        PasswordsEntity passwordsEntity = hostingEntity.getPasswordsEntity();
        if (passwordsEntity != null) {
            passwordRepository.delete(passwordsEntity);
        }

        // Delete DomainEntity
        hostingAddRepo.delete(hostingEntity);

        ResultResponse response = new ResultResponse();
        response.setResult("Hosting with " + hostingId + " is deleted successfully.");
        log.info("Hosting with " + hostingId + " is deleted successfully.");
        return ResponseEntity.ok(response);
    }
}
