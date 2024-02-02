package com.example.hostinghub.service;

import com.example.hostinghub.entity.AdminEntity;
import com.example.hostinghub.entity.EmailEntity;
import com.example.hostinghub.mappers.EmailMapper;
import com.example.hostinghub.passwordencrypt.PasswordEncoder;
import com.example.hostinghub.repository.EmailRepository;
import com.example.hostinghub.request.EmailRequest;
import com.example.hostinghub.request.EmailUpdateRequest;
import com.example.hostinghub.response.EmailResponse;
import com.example.hostinghub.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmailService {

    private EmailRepository emailRepository;  // Injecting the EmailRepository
    private EmailMapper emailMapper;  // Injecting the EmailMapper

    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmailService(EmailRepository emailRepository,
                        EmailMapper emailMapper,
                        PasswordEncoder passwordEncoder){
        this.emailRepository = emailRepository;
        this.emailMapper = emailMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Method to register a new email
    public ResultResponse registerEmail(EmailRequest emailEntity) {
        try {
            // Get the highest email ID
            String highestEmailId = emailRepository.findHighestEmailId();

            int numericPart = 1;

            // Calculate the numeric part for the new email ID
            if (highestEmailId != null) {
                numericPart = Integer.parseInt(highestEmailId.substring(2)) + 1;
            }

            // Format the new email ID
            String idFormat = "EM%03d";
            String emailId = String.format(idFormat, numericPart);

            // Set the email ID for the emailEntity
            emailEntity.setEmailId(emailId);

            // Convert the EmailRequest to EmailEntity using the mapper
            EmailEntity req = emailMapper.entityToRequest(emailEntity);
            req.setPassword(passwordEncoder.encryptPassword(emailEntity.getPassword()));

            // Save the emailEntity in the repository
            emailRepository.save(req);

            // Create a success response
            ResultResponse result = new ResultResponse();

            // Log the success message with email information
            log.info("Registration successful for email with ID: {}. Details: Email={}, Username={}, Password={}",
                    emailId, emailEntity.getEmail(), emailEntity.getUsername(), emailEntity.getPassword());

            result.setResult("Registration successful");  // Set the result message

            return result;  // Return the result
        } catch (Exception e) {
            // Handle any exceptions and log an error message
            log.error("Error occurred during email registration: " + e.getMessage());

            // Create an error response
            ResultResponse errorResult = new ResultResponse();
            errorResult.setResult("Error occurred during email registration");

            return errorResult;  // Return the error result
        }
    }
    //
    public ResponseEntity<?> getEmailById(String emailId) {
        try {
            // Try to find the email by its ID
            Optional<EmailEntity> emailOptional = emailRepository.findById(emailId);

            if (emailOptional.isPresent()) {
                EmailEntity adminEntity = emailOptional.get();

                // Decrypt password in the retrieved admin entity
                String decryptedPassword = passwordEncoder.decryptPassword(adminEntity.getPassword());
                adminEntity.setPassword(decryptedPassword);

                log.info("The size of the admin is: {}", emailOptional.isPresent());
                // log.info("The retrieved admin is: {}", adminEntityOptional);
                emailMapper.entityToResponse(adminEntity);

                return ResponseEntity.ok(adminEntity);
            }
        } catch (Exception e) {
            // Handle any exceptions and log an error message
            log.error("Error occurred while retrieving email by ID: " + e.getMessage());
        }
        return null;
    }

    // Method to get all emails
    public List<EmailResponse> getAllEmails() {
        try {
            // Find all email entities
            List<EmailEntity> req = emailRepository.findAll();

            for (EmailEntity emailEntity : req) {
                String decryptedPassword = passwordEncoder.decryptPassword(emailEntity.getPassword());
                emailEntity.setPassword(decryptedPassword);
            }
            // Convert and return email entities to email responses using the mapper
            return emailMapper.responseToEntity(req);
        } catch (Exception e) {
            // Handle any exceptions and log an error message
            log.error("Error occurred while retrieving all emails: " + e.getMessage());
            return null;
        }
    }
    public ResultResponse updateEmail(String emailId, EmailUpdateRequest updatedEmail) {
        try {
            // Logic for updating an email
            EmailEntity existingEmail = emailRepository.findByEmailId(emailId);

            if (existingEmail != null) {
                EmailEntity updatedEmailEntity = emailMapper.updateEntityFromRequest(updatedEmail, existingEmail);
                updatedEmailEntity.setPassword(passwordEncoder.encryptPassword(existingEmail.getPassword()));
                emailRepository.save(updatedEmailEntity);

                ResultResponse result = new ResultResponse();
                log.info("Update successful for email {}. Updated email: {}", emailId, updatedEmail);
                result.setResult("Update successful");

                return result;
            }

            ResultResponse result = new ResultResponse();
            log.info("Email ID not found in the DB");
            result.setResult("Email not found");

            return result;
        } catch (Exception e) {
            // Handle any exceptions and log an error message
            log.error("Error occurred during email update: " + e.getMessage());
            ResultResponse errorResult = new ResultResponse();
            errorResult.setResult("Error occurred during email update");

            return errorResult;
        }
    }
    // Method to delete an email
    public ResultResponse deleteEmail(String emailId) {
        try {
            // Try to find the email by its ID
            Optional<EmailEntity> emailOptional = emailRepository.findById(emailId);

            if (emailOptional.isPresent()) {
                // If found, delete the email by its ID
                emailRepository.deleteById(emailId);

                // Create a success response
                ResultResponse result = new ResultResponse();

                // Log the successful deletion
                log.info("Deleted email with ID: {}", emailId);

                result.setResult("Delete successful");  // Set the result message

                return result;  // Return the result
            } else {
                // If not found, create a result indicating it
                ResultResponse result = new ResultResponse();
                result.setResult("Email not found");
                log.info("Id not found in the DB");

                return result;  // Return the result
            }
        } catch (Exception e) {
            // Handle any exceptions and log an error message
            log.error("Error occurred during email deletion: " + e.getMessage());

            // Create an error response
            ResultResponse errorResult = new ResultResponse();
            errorResult.setResult("Error occurred during email deletion");

            return errorResult;  // Return the error result
        }
    }
}


