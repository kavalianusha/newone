package com.example.efilingbazaar.controller;

import java.time.LocalDate;
import java.util.List;

import com.example.efilingbazaar.exception.EmployeeException;
import com.example.efilingbazaar.response.ResultCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import com.example.efilingbazaar.entity.EmployeeRegistrationEntity;
import com.example.efilingbazaar.repository.EmployeeRegistrationRepository;
import com.example.efilingbazaar.request.EmployeeRegistrationRequest;
import com.example.efilingbazaar.response.EmployeeRegistrationResponse;
import com.example.efilingbazaar.response.ResultResponse;
import com.example.efilingbazaar.service.EmployeeRegistrationService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class EmployeeRegistrationController {

    @Autowired
    private EmployeeRegistrationService registrationService;

    @Autowired
    private EmployeeRegistrationRepository registrationRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    // Your findHighestemployeeId method here (make sure it's inside the class and has the proper access modifier)
    public String findHighestemployeeId() {
        String highestemployeeId = registrationRepository.findHighestemployeeId();
        if (highestemployeeId == null) {
            highestemployeeId = "EMP000";
        }
        return highestemployeeId;
    }

    // Endpoint to register a new employee
    @PostMapping("/register")
    public ResponseEntity<ResultResponse> registerEmployeeFromRequest(@RequestBody EmployeeRegistrationRequest request) {
        try {
            registrationService.registerEmployeeFromRequest(request);
            /*log.info("request firstName : {} middleName : {} lastName : {} address : {} idProof : {}" +
                    " contactNumber : {} emailId :" + " {} password : {} qualification : {} skillset : {}" +
                    " roleName : {} status : {} ", request.getFirstName(), request.getMiddleName(),
                    request.getLastName(), request.getAddress(), request.getIdProof(),
                    request.getContactNumber(), request.getEmailId(), request.getPassword(), request.getQualification(),
                    request.getSkillset(), request.getRoleName(), request.isStatus());*/
            ResultResponse result = new ResultResponse();
            result.setResult("Registration successful. Login credentials sent to your email.");
            //log.info("Employee registered successfully with ID: {}", request.getEmployeeId()); // Log successful registration
            //log.trace("Employee registered successfully with ID: {} ", request.setEmployeeId(request.getEmployeeId())); // Log successful registration

            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            ResultResponse result = new ResultResponse();
            result.setResult("Failed to register employee from request: ");
            log.info("Failed to register employee from request: {}", e.getMessage()); // Log error

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    // Other endpoints remain unchanged
    // Endpoint to get all employees
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeRegistrationResponse>> getAllEmployees() {
        try {
            List<EmployeeRegistrationResponse> employees = registrationService.getAllEmployees();
            log.info("Employee with ID {} get successfully", employees);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            log.info("Failed to get all the employee from request: {}", e.getMessage()); //
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint to get an employee by ID
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<EmployeeRegistrationResponse> getEmployeeById(@PathVariable String employeeId) {
        try {
            EmployeeRegistrationResponse employee = registrationService.getEmployeeById(employeeId);
            if (employee == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            log.info("Failed to get the employee from request: {}", e.getMessage()); //
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoint to update contactNumber, address, status, and skillSet of an employee
    @PutMapping("employee/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable String employeeId, @RequestBody EmployeeRegistrationEntity updatedRegistration) {
        try {
            ResponseEntity<?> response = registrationService.updateEmployee(employeeId, updatedRegistration);
            return response;
        } catch (Exception e) {
            ResultResponse result = new ResultResponse();
            result.setResult("Failed to update employee details");
            log.info("failed to update the employee details");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    // Endpoint to delete an employee by ID
    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String employeeId) {
        try {
            ResponseEntity<Void> response = registrationService.deleteEmployee(employeeId);
            //log.info("Employee with ID {} deleted successfully", employeeId);
            return response;
        } catch (Exception e) {
            log.info("Failed to delete the employee from request: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/employees/count")
    public ResponseEntity<ResultCount> countEmployees() {
        String currentCount = String.valueOf(registrationService.countEmployees());
        String count = currentCount;
        LocalDate lastUpdatedDate = registrationService.getLastUpdatedDate();

        if (!count.equals(registrationService.getPreviousCount())) {
            lastUpdatedDate = LocalDate.now();
            registrationService.setPreviousCount(count);
            registrationService.setLastUpdatedDate(lastUpdatedDate);
        }

        String message = count;

        ResultCount response = new ResultCount();
        response.setResult(message);
        response.setDate(lastUpdatedDate);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
