package com.example.efilingbazaar.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.efilingbazaar.exception.EmployeeException;
import com.example.efilingbazaar.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.efilingbazaar.entity.OtpEntity;
import com.example.efilingbazaar.entity.EmployeeRegistrationEntity;
import com.example.efilingbazaar.entity.LoginEntity;
import com.example.efilingbazaar.entity.RoleEntity;
import com.example.efilingbazaar.mapper.EmployeeRegistrationMapper;
import com.example.efilingbazaar.repository.EmployeeRegistrationRepository;
import com.example.efilingbazaar.repository.LoginRepository;
import com.example.efilingbazaar.repository.OtpRepository;
import com.example.efilingbazaar.repository.RoleRepository;
import com.example.efilingbazaar.request.EmployeeRegistrationRequest;
import com.example.efilingbazaar.response.EmployeeRegistrationResponse;

@Service
@Component
@Slf4j
public class EmployeeRegistrationService {
    private final EmployeeRegistrationRepository registrationRepository;
    private final LoginRepository loginRepository;
    private final RoleRepository roleRepository;
    private final OtpRepository otpRepository;
    private final JavaMailSender javaMailSender;
    private final EmployeeRegistrationMapper employeeMapper;
    //private Object existingEmployee;

    @Autowired(required = true)
    public EmployeeRegistrationService(EmployeeRegistrationRepository registrationRepository,
                                       LoginRepository loginRepository, RoleRepository roleRepository, OtpRepository otpRepository,
                                       EmployeeRegistrationMapper employeeMapper, JavaMailSender javaMailSender)

    {
        this.registrationRepository = registrationRepository;
        this.loginRepository = loginRepository;
        this.roleRepository = roleRepository;
        this.otpRepository = otpRepository;
        this.employeeMapper = employeeMapper;
        this.javaMailSender = javaMailSender;

    }

    public EmployeeRegistrationEntity registerEmployeeFromRequest(EmployeeRegistrationRequest request) {
        try {
            EmployeeRegistrationEntity registration = employeeMapper.requestToEntity(request);

            // EmployeeRegistrationEntity registration = new EmployeeRegistrationEntity();
            /*
             * registration.setFirstName(request.getFirstName());
             * registration.setMiddleName(request.getMiddleName());
             * registration.setLastName(request.getLastName());
             * registration.setAddress(request.getAddress());
             * registration.setIdProof(request.getIdProof());
             * registration.setContactNumber(request.getContactNumber());
             * registration.setEmailId(request.getEmailId());
             * registration.setPassword(request.getPassword());
             * registration.setQualification(request.getQualification());
             * registration.setSkillset(request.getSkillset());
             * registration.setRoleName(request.getRoleName());
             * registration.setStatus(request.isStatus());
             */

            // return registrationRepository.save(employeeMapper.requestToEntity(request));
            // EmployeeRegistrationEntity registration =
            // employeeMapper.requestToEntity(request);
            // Check if the employee already exists based on ID proof
            if (registrationRepository.existsByIdProof(registration.getIdProof())) {
                throw new IllegalArgumentException("Employee already registered based on ID proof");
            }

            if (registrationRepository.existsByEmailId(registration.getEmailId())) {
                throw new IllegalArgumentException("Employee already registered with this email ID");
            }

            String highestemployeeId = registrationRepository.findHighestemployeeId();
            int numericPart = 1;
            if (highestemployeeId != null) {
                numericPart = Integer.parseInt(highestemployeeId.substring(3)) + 1;
            }
            String idFormat = "EMP%03d"; // The %03d means a 3-digit zero-padded number
            String employeeId = String.format(idFormat, numericPart);
            registration.setEmployeeId(employeeId);
            // Save the employee to the database
            EmployeeRegistrationEntity savedEmployee = registrationRepository.save(registration);

            // Create and save login data
            // LoginEntity login = employeeMapper.requestToEntity(request);
            /*
             * LoginEntity login = new LoginEntity();
             * login.setEmployeeId(registration.getEmployeeId());
             * login.setEmailId(registration.getEmailId());
             * login.setPassword(registration.getPassword());
             * login.setRoleName(registration.getRoleName());
             * login.setStatus(registration.isStatus()); // Set other login-related data if
             * needed loginRepository.save(login);
             */

            LoginEntity login = employeeMapper.entityToLoginEntity(registration);
            loginRepository.save(login);

            // Check if the role exists in the role table
            /*
             * RoleEntity role = roleRepository.findByRoleName(registration.getRoleName());
             * if (role == null) { // If the role doesn't exist, create and save it role =
             * new RoleEntity(); role.setRoleName(registration.getRoleName());
             * roleRepository.save(role); }
             */
            RoleEntity role = employeeMapper.entityToRoleEntity(registration);
            if (roleRepository.findByRoleName(role.getRoleName()) == null) {
                roleRepository.save(role);
            }

            // Save the employee's email in the OTP table for later use during login
            /*
             * OtpEntity otpEntity = new OtpEntity();
             *
             * otpEntity.setEmailId(registration.getEmailId());
             * otpRepository.save(otpEntity);
             */

            OtpEntity otpEntity = employeeMapper.entityToOtpEntity(registration);
            otpRepository.save(otpEntity);

            // Send email with login credentials to the registered employee
            sendRegistrationEmail(registration.getEmailId(), registration.getPassword());
            log.info("Employee registered successfully with ID: {} and Email: {}", registration.getEmployeeId(), registration.getEmailId()); // Log successful registration

            return savedEmployee;
        } catch (Exception e) {
            /*log.info("firstName {} is invalid , middleName {} is invalid , lastname {} is invalid " +
                    "address {} is ivalid , idProof {} is invalid , contactNumber {} is invalid , " +
                    "emailId {} is invalid , password {} is invalid , qualification {} is invalid , " +
                    "skillset {} is invalid , roleName {} is invalid , status {} is invalid",  );
           */

            //log.info("firstName {} is invalid ", fir );
            //e.printStackTrace();
            log.warn("exception in employee registration . {} " , e.getMessage(), e);
            throw new RuntimeException("Failed to register employee");
        }
    }

    // Method to send login credentials to the registered employee
    private void sendRegistrationEmail(String email, String password) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Registration Successful");
            message.setText("Dear Employee,\n\nYour registration is successful.\n\nEmail: " + email + "\nPassword: "
                    + password);

            javaMailSender.send(message);
            log.info("Registration email sent to: {}", email); // Log email sent
        } catch (Exception e) {
            log.error("Failed to send registration email: {}", e.getMessage()); // Log error
            e.printStackTrace();
        }
    }

    /*public List<EmployeeRegistrationResponse> getAllEmployees() {
        List<EmployeeRegistrationEntity> employees = registrationRepository.findAll();
        log.info("Retrieved {} employees.", employees.size()); // Log the number of retrieved employees
        System.out.println("result:"+employees);
        return employees.stream()
                .map(employeeMapper::convertToResponseEmployeeDTO).collect(Collectors.toList());
    }

    public EmployeeRegistrationResponse getEmployeeById(String employeeId) {
        EmployeeRegistrationEntity employee = registrationRepository.findByEmployeeId(employeeId);

        if (employee != null) {  // Use null check instead of Optional.isPresent()
            EmployeeRegistrationResponse res = employeeMapper.mapEntityToResponse(employee);
            return res;
        } else {
            return null;
        }
    }

*/
    public List<EmployeeRegistrationResponse> getAllEmployees() {
        try {
            List<EmployeeRegistrationEntity> employees = registrationRepository.findAll();
            log.info("Retrieved {} employees.", employees.size()); // Log the number of retrieved employees
            log.info("Employee details: {}", employees); // Log the details of retrieved employees (optional, for debugging)

            return employees.stream()
                    .map(employeeMapper::convertToResponseEmployeeDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Failed to retrieve employees: {}", e.getMessage()); // Log error
            // Handle the exception, such as logging the error or taking alternative actions
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve employees");
        }
    }

    public EmployeeRegistrationResponse getEmployeeById(String employeeId) {
        try {
            EmployeeRegistrationEntity employee = registrationRepository.findByEmployeeId(employeeId);

            if (employee != null) {
                EmployeeRegistrationResponse res = employeeMapper.mapEntityToResponse(employee);
                log.info("Retrieved employee by ID: {}", employeeId); // Log successful retrieval by ID
                log.info("Employee details: {}", res); // Log the details of retrieved employee (optional, for debugging)
                return res;
            } else {
                log.warn("No employee found for ID: {}", employeeId); // Log a warning when no employee is found
                return null;
            }
        } catch (Exception e) {
            log.error("Failed to retrieve employee by ID {}: {}", employeeId, e.getMessage()); // Log error
            // Handle the exception, such as logging the error or taking alternative actions
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve employee by ID");
        }
    }

    //return convertToResponseEmployeeDTO(employee);


    //EmployeeRegistrationResponse convertToResponseEmployeeDTO(EmployeeRegistrationEntity entity) {
    // EmployeeRegistrationResponse employeeDto = new
    // EmployeeRegistrationResponse();
    //EmployeeRegistrationResponse employeeDto = employeeMapper.mapEntityToResponse(entity);

    /*
     * employeeDto.setFirstName(entity.getFirstName());
     * employeeDto.setMiddleName(entity.getMiddleName());
     * employeeDto.setLastName(entity.getLastName());
     * employeeDto.setAddress(entity.getAddress());
     * employeeDto.setIdProof(entity.getIdProof());
     * employeeDto.setContactNumber(entity.getContactNumber());
     * employeeDto.setEmployeeId(entity.getEmployeeId());
     * employeeDto.setEmailId(entity.getEmailId());
     * employeeDto.setRoleName(entity.getRoleName());
     * employeeDto.setPassword(entity.getPassword());
     * employeeDto.setStatus(entity.isStatus());
     * employeeDto.setQualification(entity.getQualification());
     * employeeDto.setSkillset(entity.getSkillset());
     */

    //	return employeeDto;
    //}


    public ResponseEntity<?> updateEmployee(String employeeId, EmployeeRegistrationEntity updatedRegistration) {
        try {
            EmployeeRegistrationEntity existingEmployee = registrationRepository.findByEmployeeId(employeeId);
            // EmployeeRegistrationEntity existingEmployee = employeeMapper.updateEntityToRequest(updatedRegistration);
            // registrationRepository.save(existingEmployee);

            if (existingEmployee != null) {
                //EmployeeRegistrationEntity existingEmployee = employeeMapper.updateEntityToRequest(updatedRegistration);


                // Update the employee's details with the new information
                existingEmployee.setAddress(updatedRegistration.getAddress());
                existingEmployee.setContactNumber(updatedRegistration.getContactNumber());
                existingEmployee.setSkillset(updatedRegistration.getSkillset());
                existingEmployee.setStatus(updatedRegistration.isStatus());
                registrationRepository.save(existingEmployee);
                ResultResponse result = new ResultResponse();
                result.setResult("Employee details updated successfully");
                log.info("Employee with ID {} updated successfully", employeeId); // Log successful update
                return ResponseEntity.ok(result);
            } else {
                log.warn("No employee found for ID: {}", employeeId); // Log a warning when no employee is found
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Failed to update employee: {}", e.getMessage()); // Log error
            // Handle the exception, such as logging the error or taking alternative actions
            e.printStackTrace();
            throw new RuntimeException("Failed to update employee details");
        }
    }


    public ResponseEntity<Void> deleteEmployee(String employeeId) {
        try {
            EmployeeRegistrationEntity employee = registrationRepository.findByEmployeeId(employeeId);
            if (employee != null) {
                registrationRepository.delete(employee);
                log.info("Employee with ID {} deleted successfully", employeeId); // Log successful update
                return ResponseEntity.ok().build();
            } else {
                //log.info("Employee with ID {} deleted successfully", employeeId); // Log successful deletion
                log.warn("No employee found for ID: {}", employeeId); // Log a warning when no employee is found
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            //log.error("Failed to delete employee: {}", e.getMessage()); // Log error
            // Handle the exception, such as logging the error or taking alternative actions
            //log.warn("No employee found for ID: {}", employeeId); // Log a warning when no employee is found
            e.printStackTrace();
            throw new RuntimeException("Failed to delete employee");
        }
    }
    private String previousCount = "";
    private LocalDate lastUpdatedDate = LocalDate.now();

    public String getPreviousCount() {
        return previousCount;
    }

    public void setPreviousCount(String previousCount) {
        this.previousCount = previousCount;
    }

    public LocalDate getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int countEmployees() {
        try {
            return (int) registrationRepository.count(); // Count all employees in the database
        } catch (Exception e) {
            log.error("Failed to count employees: {}", e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to count employees");
        }
    }
    private EmployeeRegistrationEntity findByEmployeeId(String employeeId) {

        return registrationRepository.findByEmployeeId(employeeId);
    }


}