package com.pathbreaker.hostinghub.service;

import com.pathbreaker.hostinghub.entity.DomainEntity;
import com.pathbreaker.hostinghub.entity.PasswordsEntity;
import com.pathbreaker.hostinghub.exception.AdminException;
import com.pathbreaker.hostinghub.mappers.DomainMappers;
import com.pathbreaker.hostinghub.mappers.PasswordsMapper;
import com.pathbreaker.hostinghub.passwordencrypt.PasswordEncoder;
import com.pathbreaker.hostinghub.repository.DomainRepository;
import com.pathbreaker.hostinghub.repository.PasswordRepository;
import com.pathbreaker.hostinghub.request.DomainRequest;
import com.pathbreaker.hostinghub.request.DomainUpdateRequest;
import com.pathbreaker.hostinghub.response.DomainResponse;
import com.pathbreaker.hostinghub.response.PasswordsResponse;
import com.pathbreaker.hostinghub.response.ResultResponse;
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
public class DomainService {

    // Injecting DomainRepository and DomainMappers
    private final DomainRepository domainRepository;
    private final DomainMappers domainMappers;
    private final PasswordRepository passwordRepository;

    private final PasswordEncoder passwordEncoder;

    private final PasswordsMapper passwordsMapper;


    @Autowired
    public DomainService(DomainRepository domainRepository,
                         DomainMappers domainMappers,
                         PasswordRepository passwordRepository,
                         PasswordEncoder passwordEncoder,
                         PasswordsMapper passwordsMapper) {
        this.domainRepository = domainRepository;
        this.domainMappers = domainMappers;
        this.passwordRepository = passwordRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordsMapper = passwordsMapper;
    }

    public ResponseEntity<?> registerDomain(DomainRequest domainRequest) {
        try {
            // Logic for generating a unique domain ID
            String highestDomainId = domainRepository.findHighestDomainId();
            int numericPart = 1;

            if (highestDomainId != null) {
                numericPart = Integer.parseInt(highestDomainId.substring(3)) + 1;
            }

            String idFormat = "DOM%03d";
            String domainId = String.format(idFormat, numericPart);
            domainRequest.setDomainId(domainId);
            System.out.println(domainRequest.getDomainId());
            System.out.println(domainRequest);
            DomainEntity domainEntity = domainMappers.entityToRequest(domainRequest);
            domainEntity.setPassword(passwordEncoder.encryptPassword(domainEntity.getPassword()));

            String highestPasswordId = passwordRepository.findHighestPasswordId();
            int numericPartPwd = 1;

            if (highestPasswordId != null) {
                numericPartPwd = Integer.parseInt(highestPasswordId.substring(3)) + 1;
            }

            String idFormatPwd = "PWD%03d";
            String passwordId = String.format(idFormatPwd, numericPartPwd);

            PasswordsEntity passwordsEntity = domainMappers.entityPasswordToRequest(domainRequest);
            passwordsEntity.setPasswordId(passwordId);
            System.out.println(passwordsEntity.getPasswordId());
            System.out.println(passwordsEntity);
            passwordsEntity.setPassword(passwordEncoder.encryptPassword(passwordsEntity.getPassword()));

            domainEntity.setPasswordsEntity(passwordsEntity);


            LocalDate registrationDate = LocalDate.parse(domainRequest.getRegistrationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate expiryDate = LocalDate.parse(domainRequest.getExpiryDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            long daysLeft = ChronoUnit.DAYS.between(registrationDate, expiryDate);
            passwordsEntity.setDaysLeft(daysLeft);
            domainEntity.setDaysLeft(daysLeft);

            System.out.println(domainEntity);

            domainRepository.save(domainEntity);

            ResultResponse result = new ResultResponse();
            log.info("Registration successful for domain {}. Retrieved user: {}", domainEntity.getRegistrationEmail(), domainEntity);
            result.setResult("Registration successful");

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Handling exceptions and logging an error message
            log.error("Error occurred during domain registration: " + e.getMessage());
            ResultResponse errorResult = new ResultResponse();
            errorResult.setResult("Error occurred during domain registration");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResult);
        }
    }


    // Method to get a domain by its ID
    public ResponseEntity<?> getDomainById(String domainId) {

        Optional<DomainEntity> domainOptional = domainRepository.findById(domainId);

        try {
                DomainEntity domainEntity = domainOptional.get();
                DomainResponse response = domainMappers.domainEntityToResponse(domainEntity);

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
            response.setResult("The domain with id "+domainId+ " is not found in DB");
            return ResponseEntity.ok(response);

        }
    }

    public List<DomainResponse> getAllDomains() {
        try {
            // Finding all domain entities
            List<DomainEntity> domains = domainRepository.findAll();
            log.info("The size of the domains is: {}", domains.size());

            List<DomainResponse> domainResponses = new ArrayList<>();

            for (DomainEntity domainEntity : domains) {
                DomainResponse response = domainMappers.domainEntityToResponse(domainEntity);
                System.out.println(response.getPasswordsResponse());
                PasswordsResponse passwordsResponse = response.getPasswordsResponse();

                LocalDate expiryDate = LocalDate.parse(response.getExpiryDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(),expiryDate);
                response.setDaysLeft(daysLeft);
                System.out.println(response.getDaysLeft());


                if(Objects.nonNull(passwordsResponse)){
                    String decryptedPassword = passwordEncoder.decryptPassword(passwordsResponse.getPassword());
                    response.setPassword(decryptedPassword);
                    passwordsResponse.setPassword(decryptedPassword);
                }
                domainResponses.add(response);
            }

            return domainResponses;
        } catch (Exception e) {
            // Handle any exceptions and log an error message
            log.error("Error occurred while retrieving all domains: " + e.getMessage(),e);
            return null;
        }
    }
   //method for update a domain

    public ResponseEntity<?> deleteDomainById(String domainId) {
        DomainEntity domainEntity = domainRepository.findByDomainId(domainId);

        if (domainEntity == null) {
            ResultResponse response = new ResultResponse();
            response.setResult("Domain with " + domainId + " is not found in the database.");
            log.info("Domain with " + domainId + " is not found in the database.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Delete associated PasswordsEntity
        PasswordsEntity passwordsEntity = domainEntity.getPasswordsEntity();
        if (passwordsEntity != null) {
            passwordRepository.delete(passwordsEntity);
        }

        // Delete DomainEntity
        domainRepository.delete(domainEntity);

        ResultResponse response = new ResultResponse();
        response.setResult("Domain with " + domainId + " is deleted successfully.");
        log.info("Domain with " + domainId + " is deleted successfully.");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> updateDomain(String domainId, DomainUpdateRequest updatedDomainRequest) {
        DomainEntity domainEntity = domainRepository.findByDomainId(domainId);
        try {
            if (Objects.isNull(domainEntity)) {
                log.info("The Domain id is not found in the db: {}", domainId);
                ResultResponse response = new ResultResponse();
                response.setResult("Domain with ID " + domainId + " not found.");
                throw new AdminException(response);
             }
            // Update DomainEntity fields
            DomainEntity updatedEntity = domainMappers.updateEntityFromRequest(domainEntity, updatedDomainRequest);

            PasswordsEntity passwordsEntity = domainMappers.requestToUpdatePasswordEntity(updatedDomainRequest);

            passwordsEntity.setPasswordId(domainEntity.getPasswordsEntity().getPasswordId());
            updatedEntity.setPasswordsEntity(passwordsEntity);

            passwordsEntity.setPassword(passwordEncoder.encryptPassword(passwordsEntity.getPassword()));
            updatedEntity.setPassword(passwordEncoder.encryptPassword(updatedDomainRequest.getPassword()));

            System.out.println(updatedEntity);

            LocalDate registrationDate = LocalDate.parse(updatedDomainRequest.getRegistrationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate expiryDate = LocalDate.parse(updatedDomainRequest.getExpiryDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            long daysLeft = ChronoUnit.DAYS.between(registrationDate, expiryDate);
            passwordsEntity.setDaysLeft(daysLeft);
            updatedEntity.setDaysLeft(daysLeft);

            boolean isUserNameOrPasswordUpdated =
                    Objects.nonNull(updatedEntity.getUserName())
                            || Objects.nonNull(updatedEntity.getPassword());

            if (isUserNameOrPasswordUpdated) {
                passwordsEntity.setUpdateDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }

            // Set the updated password entity in the existing domain entity
            updatedEntity.setPasswordsEntity(passwordsEntity);
            updatedEntity.setDaysLeft(daysLeft);


            domainRepository.save(updatedEntity);

            ResultResponse response = new ResultResponse();
            response.setResult("Domain with " + domainId + " is updated successfully....");
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            throw new AdminException("An error occurred while updating the domain: " + e.getMessage());
        }
    }

}



