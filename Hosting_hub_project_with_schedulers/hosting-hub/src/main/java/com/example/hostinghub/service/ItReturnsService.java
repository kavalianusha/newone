package com.example.hostinghub.service;


import com.example.hostinghub.entity.*;
import com.example.hostinghub.exception.AdminException;
import com.example.hostinghub.mappers.ItReturnsMapper;
import com.example.hostinghub.passwordencrypt.PasswordEncoder;
import com.example.hostinghub.repository.ItReturnsRepository;
import com.example.hostinghub.repository.PasswordRepository;
import com.example.hostinghub.request.ItReturnsRequest;
import com.example.hostinghub.request.ItReturnsUpdateRequest;
import com.example.hostinghub.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ItReturnsService {

    // Injecting ItReturnsRepository and ItReturnsMappers
    private final ItReturnsRepository itReturnsRepository;
    private final ItReturnsMapper itReturnsMapper;

    private final PasswordRepository passwordRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ItReturnsService(ItReturnsRepository itReturnsRepository,
                            ItReturnsMapper itReturnsMapper,
                            PasswordRepository passwordRepository,
                            PasswordEncoder passwordEncoder) {
        this.itReturnsRepository = itReturnsRepository;
        this.itReturnsMapper = itReturnsMapper;
        this.passwordRepository = passwordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Endpoint for registering a new IT return
    public ResponseEntity<?> registerItReturns(ItReturnsRequest itReturnsRequest) {
        try {
            // Logic for generating a unique customer ID
            String highestCustomerId = itReturnsRepository.findHighestCustomerId();
            int numericPart = 1;

            if (highestCustomerId != null) {
                numericPart = Integer.parseInt(highestCustomerId.substring(4)) + 1;
            }

            String idFormat = "CUST%03d";
            String customerId = String.format(idFormat, numericPart);

            itReturnsRequest.setCustomerId(customerId);

            // Explicitly set the created date using LocalDate
            ItReturnsEntity itReturnsEntity = itReturnsMapper.requestToEntity(itReturnsRequest);
            itReturnsEntity.setPassword(passwordEncoder.encryptPassword(itReturnsEntity.getPassword()));

            itReturnsEntity.setCreatedDate(LocalDate.now().toString());
            PasswordsEntity passwordsEntity = itReturnsMapper.requestToPasswordEntity(itReturnsRequest);
            passwordsEntity.setPassword(passwordEncoder.encryptPassword(passwordsEntity.getPassword()));


            String highestPasswordId = passwordRepository.findHighestPasswordId();
            int numericPartpwd = 1;

            if (highestPasswordId != null) {
                numericPartpwd = Integer.parseInt(highestPasswordId.substring(3)) + 1;
            }

            String idFormatPwd = "PWD%03d";
            String passwordId = String.format(idFormatPwd, numericPartpwd);
            passwordsEntity.setPasswordId(passwordId);


            LocalDate registrationDate = LocalDate.parse(itReturnsRequest.getRegistrationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate expiryDate = LocalDate.parse(itReturnsRequest.getExpiryDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            long daysLeft = ChronoUnit.DAYS.between(registrationDate, expiryDate);
            passwordsEntity.setDaysLeft(daysLeft);
            itReturnsEntity.setDaysLeft(daysLeft);

           // passwordEntity.setItReturnsEntity(itReturnsEntity);
            itReturnsEntity.setPasswordsEntity(passwordsEntity);

            itReturnsRepository.save(itReturnsEntity);

            ResultResponse result = new ResultResponse();
            log.info("Registration successful for customer {}. Retrieved IT return: {}",
                    itReturnsRequest.getEmailId(), itReturnsRequest);
            log.info("Registration is succcessful for the customer it returns");
            result.setResult("Registration successful");

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Handling exceptions and logging an error message
            log.error("Error occurred during IT return registration: " + e.getMessage());
            ResultResponse errorResult = new ResultResponse();
            errorResult.setResult("Error occurred during IT return registration");

            return ResponseEntity.ok(errorResult);
        }
    }

    // Method to get an IT return by its customer ID
    public  ResponseEntity<?>  getItReturnsByCustomerId(String customerId) {
        Optional<ItReturnsEntity> domainOptional = itReturnsRepository.findById(customerId);

        try {
            if ((domainOptional.isPresent())) {
                ItReturnsEntity domainEntity = domainOptional.get();
                ItReturnsResponse response = itReturnsMapper.responseToEntity(domainEntity);

                // Decrypt the password in DomainEntity
                String decryptedPassword = passwordEncoder.decryptPassword(response.getPassword());
                response.setPassword(decryptedPassword);
                response.getPasswordsResponse().setPassword(decryptedPassword);
                System.out.println(decryptedPassword);

                LocalDate expiryDate = LocalDate.parse(response.getExpiryDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(),expiryDate);
                response.setDaysLeft(daysLeft);

                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            ResultResponse response = new ResultResponse();
            response.setResult("the erros is : "+e);
            return ResponseEntity.ok(response);

        }
        return null;
    }

    public List<ItReturnsResponse> getAllItReturns() {
        try {
            List<ItReturnsEntity> hostingEntityList = itReturnsRepository.findAll();
            log.info("The size of the domains is: {}", hostingEntityList.size());

            List<ItReturnsResponse> hostingResponses = new ArrayList<>();

            for (ItReturnsEntity hostingEntity : hostingEntityList) {
                ItReturnsResponse response = itReturnsMapper.responseToEntity(hostingEntity);
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

    public ResponseEntity<?> updateItReturns(String customerId, ItReturnsUpdateRequest updatedItReturns) {
            ItReturnsEntity existingItReturns = itReturnsRepository.findByCustomerId(customerId);
        try {
            if (Objects.isNull(existingItReturns)) {
                throw new AdminException("Customer Id not exists " + customerId);

            }
                ItReturnsEntity entity = itReturnsMapper.updateEntityFromRequest(updatedItReturns, existingItReturns);

                PasswordsEntity passwordsEntity = itReturnsMapper.requestToUpdatePasswordEntity(updatedItReturns);

                passwordsEntity.setPasswordId(existingItReturns.getPasswordsEntity().getPasswordId());
                entity.setPasswordsEntity(passwordsEntity);

                passwordsEntity.setPassword(passwordEncoder.encryptPassword(passwordsEntity.getPassword()));
                entity.setPassword(passwordEncoder.encryptPassword(entity.getPassword()));

                LocalDate registrationDate = LocalDate.parse(updatedItReturns.getRegistrationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate expiryDate = LocalDate.parse(updatedItReturns.getExpiryDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                long daysLeft = ChronoUnit.DAYS.between(registrationDate, expiryDate);
                passwordsEntity.setDaysLeft(daysLeft);
                entity.setDaysLeft(daysLeft);

            boolean isUserNameOrPasswordUpdated =
                    Objects.nonNull(entity.getUserName())
                            || Objects.nonNull(entity.getPassword());

            if (isUserNameOrPasswordUpdated) {
                passwordsEntity.setUpdateDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }
            entity.setDaysLeft(daysLeft);

            itReturnsRepository.save(entity);

                ResultResponse result = new ResultResponse();
                log.info("Update successful for IT return {}. Updated IT return: {}", customerId);
                result.setResult("Update successful");

                return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("Error occurred during IT return update: " + e.getMessage(), e);
            ResultResponse errorResult = new ResultResponse();
            errorResult.setResult("Error occurred during IT return update");

            return ResponseEntity.ok(errorResult);
        }
    }

    // Method to delete an IT return
    public ResponseEntity<?> deleteItreturnsById(String customerId) {
        ItReturnsEntity itReturnsEntity = itReturnsRepository.findByCustomerId(customerId);

        if (itReturnsEntity == null) {
            ResultResponse response = new ResultResponse();
            response.setResult("It returns with " + customerId + " is not found in the database.");
            log.info("It returns with " + customerId + " is not found in the database.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Delete associated PasswordsEntity
        PasswordsEntity passwordsEntity = itReturnsEntity.getPasswordsEntity();
            passwordRepository.delete(passwordsEntity);

        // Delete DomainEntity
        itReturnsRepository.delete(itReturnsEntity);

        ResultResponse response = new ResultResponse();
        response.setResult("Itreturns with " + customerId + " is deleted successfully.");
        log.info("It returns with " + customerId + " is deleted successfully.");
        return ResponseEntity.ok(response);
    }
}


