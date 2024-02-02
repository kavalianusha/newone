package com.example.efilingbazaar.controller;

import com.example.efilingbazaar.entity.CustomerEntity;
import com.example.efilingbazaar.entity.MainServiceEntity;
import com.example.efilingbazaar.exception.CustomerNotFoundException;
import com.example.efilingbazaar.repository.CustomerRepository;
import com.example.efilingbazaar.request.CustomerRequest;
import com.example.efilingbazaar.request.MainServiceRequest;
import com.example.efilingbazaar.response.CustomerResponse;
import com.example.efilingbazaar.response.ResultCount;
import com.example.efilingbazaar.response.ResultResponse;
import com.example.efilingbazaar.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins="*")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/customers/register")
    public ResponseEntity<?> registerUser(@RequestBody CustomerRequest customer) {
        return customerService.registerUser(customer);
    }
    @PostMapping("/customers/login")
    public ResponseEntity<?> verifyOtp(@RequestBody CustomerEntity customer) {
        try {
            ResponseEntity<?> response = customerService.verifyOtp(customer.getPassword(), customer.getEmail());
            return response;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }
    @PostMapping("/customers/logout")
    public ResponseEntity<?> logoutCustomerByEmail(@RequestBody CustomerRequest request) {
        boolean logoutSuccessful = customerService.logoutCustomerByEmail(request.getEmail());

        if (logoutSuccessful) {
            ResultResponse result = new ResultResponse();
            result.setResult("Customer logged out successfully.");
            log.info("customer logout successfully!!");
            return ResponseEntity.ok(result);
        } else {
            ResultResponse result = new ResultResponse();
            result.setResult("Customer not found or could not be logged out.");
            log.info("The Customer emailId not found in" +
                    " db to logout {}",request.getEmail());
            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/customers/all")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customerResponses = customerService.getAllCustomers();
        return ResponseEntity.ok(customerResponses);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String customerId) {
        CustomerResponse customer = customerService.getCustomerById(customerId);
        try {
            if (customer == null) {
                log.info("give me the correct customerId to get the details {} : ",customerId);
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("give me the correct customerId to get the details {} : ",customerId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/customers/{customerId}")
    public ResponseEntity<?> updateCustomer(
            @PathVariable String customerId,
            @RequestBody CustomerRequest customerUpdateRequest) {
        try {
            // Check if the main service with the given ID exists in the database
            if (!customerService.existsById(customerId)) {
                ResultResponse result = new ResultResponse();
                result.setResult("Customer with ID " + customerId + " not found.");
                log.info("The customer with the given id is not found {} : ",customerUpdateRequest.getCustomerId());
                return ResponseEntity.ok(result);
            }
            // Perform the update operation using the service
            customerService.updateCustomer(customerId, customerUpdateRequest);
            ResultResponse result = new ResultResponse();
            result.setResult("Customer with ID " + customerId + " has been updated.");
            log.info("The customer details are updated successfully!!");
            return ResponseEntity.ok(result);

        } catch (Exception ex) {
            // Log the exception
            ex.printStackTrace();

            ResultResponse result = new ResultResponse();
            result.setResult("An error occurred while updating the customer: " + ex.getMessage());
            log.info("An error occurred while updating the customer {}: ", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable String customerId) {
        try {
            customerService.deleteCustomerById(customerId);
            ResultResponse result = new ResultResponse();
            result.setResult("Customer with ID " + customerId + " has been deleted.");
            log.info("The customer is deleted successfully!!");
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            ResultResponse result = new ResultResponse();
            result.setResult("Customer with ID: " + customerId + " not found");
            log.info("The customer id is not found in the db {} ", customerId);
            return ResponseEntity.ok(result);
        }
    }
    @PostMapping("/customers/forgot-otp")
    public ResponseEntity<?> forgotPasswordOtp(@RequestBody CustomerRequest request) {
        CustomerEntity customer = customerRepository.findByEmail(request.getEmail());
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        }
        try {
            customerService.forgotPasswordOtp(customer.getEmail());
            ResultResponse result = new ResultResponse();
            result.setResult("OTP for forgot password sent to the email address.");
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            // Invalid email or password
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            ResultResponse result = new ResultResponse();
            result.setResult("Failed to send OTP for forgot password");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @PostMapping("/customers/forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestBody CustomerRequest request) {
        try {
            boolean passwordResetSuccessful = customerService.verifyOtpAndResetPassword(request.getOtp(), request.getPassword(), request.getEmail());
            if (passwordResetSuccessful) {
                ResultResponse result = new ResultResponse();
                result.setResult("Password reset successful. please login with the email id and password");
                return ResponseEntity.ok(result);
            } else {
                ResultResponse result = new ResultResponse();
                result.setResult("Invalid OTP or customer not found.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }
        } catch (Exception e) {
            ResultResponse result = new ResultResponse();
            result.setResult("Failed to reset password.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
    @GetMapping("customers/count")
    public ResponseEntity<ResultCount> countCustomers() {
            String currentCount = String.valueOf(customerService.countCustomers());
            LocalDate lastUpdatedDate = customerService.getLastUpdatedDate();

            if (!currentCount.equals(customerService.getPreviousCount())) {
                lastUpdatedDate = LocalDate.now();
                customerService.setPreviousCount(currentCount);
                customerService.setLastUpdatedDate(lastUpdatedDate);
            }
            String message =  currentCount;
            ResultCount response = new ResultCount();
            response.setResult(message);
            response.setDate(lastUpdatedDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    @RequestMapping(value = "/customers/upload-document/{customerId}", method=RequestMethod.POST,
            consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadDocument(
            @PathVariable String customerId,
            @RequestParam("files") List<MultipartFile> files) {
        try {
            if (files.isEmpty()) {
                ResultResponse result = new ResultResponse();
                result.setResult("File cannot be empty.");
                log.info("File cannot be empty.");
                return ResponseEntity.badRequest().body(result);
            }
            customerService.uploadFile(customerId,files);

            ResultResponse result = new ResultResponse();
            result.setResult("Document uploaded successfully");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            ResultResponse result = new ResultResponse();
            result.setResult(e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception stack trace for debugging
            ResultResponse result = new ResultResponse();
            result.setResult("Failed to upload document");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/customer/{customerId}/images/{imageName}")
    public ResponseEntity<?> getCustomerImage(@PathVariable String customerId, @PathVariable String imageName) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            List<String> filePaths = customerOptional.get().getCustomerFilePaths();
            for (String filePath : filePaths) {
                if (Paths.get(filePath).getFileName().toString().equals(imageName)) {
                    try {
                        Path documentPath = Paths.get(filePath);
                        byte[] documentBytes = Files.readAllBytes(documentPath);

                        HttpHeaders headers = new HttpHeaders();

                        // Determine content type based on file extension
                        String contentType = Files.probeContentType(documentPath);
                        if (contentType != null) {
                            headers.setContentType(MediaType.parseMediaType(contentType));
                        }

                        return new ResponseEntity<>(documentBytes, headers, HttpStatus.OK);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }
            }
            // If we reach here, the image was not found
            ResultResponse result = new ResultResponse();
            result.setResult("Image/document not found for customer with ID: " + customerId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(result);
        }
        // If we reach here, the customer was not found
        ResultResponse result = new ResultResponse();
        result.setResult("Customer with ID " + customerId + " not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(result);
    }



}
