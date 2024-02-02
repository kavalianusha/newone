package com.example.efilingbazaar.service;

import com.example.efilingbazaar.entity.CustomerEntity;
import com.example.efilingbazaar.entity.MainServiceEntity;
import com.example.efilingbazaar.entity.SubServiceEntity;
import com.example.efilingbazaar.exception.CustomerNotFoundException;
import com.example.efilingbazaar.mapper.CustomerMapper;
import com.example.efilingbazaar.repository.CustomerRepository;
import com.example.efilingbazaar.request.CustomerRequest;
import com.example.efilingbazaar.response.CustomerResponse;
import com.example.efilingbazaar.response.ResultResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@Data
public class CustomerService {

    private CustomerRepository customerRepository;

    private JavaMailSender javaMailSender;

    private CustomerMapper customerMapper;

    // private MultipartFile file;

    //private final String PATH = "D:\\Efiling_WS\\updated-efiling-bazaar\\anushamamatha2\\efiling-bazaar\\src\\test\\resources\\MainServiceImages\\";
    private String PATH;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           JavaMailSender javaMailSender,
                           CustomerMapper customerMapper,
                           @Value("${file.upload.path}") String filePath){
        this.customerMapper=customerMapper;
        this.javaMailSender=javaMailSender;
        this.customerRepository=customerRepository;
        try {

            File newFile = new File(filePath+"CustomerImages/");
            if (!newFile.exists()){
                newFile.mkdirs();
            }
            System.out.println("the new file name is : " +newFile.exists());

            this.PATH=filePath+"CustomerImages/";
        } catch (Exception e) {
            // Handle the exception if needed
            e.printStackTrace();
        }

    }

    public ResponseEntity<?> registerUser(CustomerRequest request) {
        CustomerEntity existingUser = customerRepository.findByEmail(request.getEmail());
        if (existingUser != null) {
            // Email already exists in the database
            ResultResponse result = new ResultResponse();
            result.setResult("Email exists in the db");
            log.info("Email exists in the db: {}", request.getEmail());
            return ResponseEntity.badRequest().body(result);
        } else {
            // Email doesn't exist, save the data into the database
            CustomerEntity newUser = customerMapper.entityToRequest(request);
            String highestCustomerId = customerRepository.findHighestCustomerId();
            int numericPart = 1;

            if (highestCustomerId != null) {
                numericPart = Integer.parseInt(highestCustomerId.substring(4)) + 1;
            }
            String idFormat = "CUST%03d"; // The %03d means a 3-digit zero-padded number
            String customerId = String.format(idFormat, numericPart);
            newUser.setCustomerId(customerId);

            sendEmail(newUser.getEmail(), newUser.getPassword());
            newUser.setCreatedDate(LocalDate.now());
            customerRepository.save(newUser);

            ResultResponse result = new ResultResponse();
            result.setResult("Registration successful. Login with email and password.");
            log.info("Customer registration successful: {}", request.getEmail());
            return ResponseEntity.ok(result);
        }
    }
    public ResponseEntity<?> verifyOtp(String password, String email) {
        CustomerEntity customerEntity = customerRepository.findByEmail(email);

        if (customerEntity == null) {
            log.info("Incorrect email: {}", email);
            ResultResponse result = new ResultResponse();
            result.setResult("Incorrect email");
            return ResponseEntity.badRequest().body(result);
        }

        String storedPassword = customerEntity.getPassword();

        if (storedPassword == null) {
            log.info("password is null for email: {}", email);
            ResultResponse result = new ResultResponse();
            result.setResult("password is null");
            return ResponseEntity.badRequest().body(result);
        }

        if (!password.equals(storedPassword)) {
            ResultResponse result = new ResultResponse();
            result.setResult("Incorrect password");
            log.info("Incorrect password for email: {}", email);

            return ResponseEntity.badRequest().body(result);
        }
        customerEntity.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        customerRepository.save(customerEntity);
        //otpExpiryMap.remove(otp);
        ResultResponse result = new ResultResponse();
        result.setResult("Customer login successful!!");
        log.info("Customer login is successful for email: {}", email);
        return ResponseEntity.ok(result);
}

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public void sendEmail(String email, String password) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Registration is successfull for Customer");
        mailMessage.setText("Login with your email id  is : " + email + " \n  and password is : " + password);

        javaMailSender.send(mailMessage);
        System.out.println("password send successfully to: " + email);
    }
    public void sendOtpByEmail(String email, String otp) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Customer forgot password mail");
        mailMessage.setText("Your otp for forgot password is : " + otp);

        javaMailSender.send(mailMessage);
        System.out.println("otp send successfully to: " + email);
    }


    public List<CustomerResponse> getAllCustomers() {
        List<CustomerEntity> customers = customerRepository.findAll();
        log.info("the size of the customers is {} : ", customers.size());
        log.info("the retrieved customer are {} : ", customers);
        return customerMapper.responseToEntityList(customers);
    }

    public void deleteCustomerById(String customerId) {
        CustomerEntity customer = customerRepository.findByCustomerId(customerId);
        if (customer == null) {
            log.info("The customer id is not found in the db {} ", customer.getCustomerId());
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
        customerRepository.deleteById(customerId);
    }

    public CustomerRequest findBycustomerId(String customerId) {
        // TODO Auto-generated method stub
        return customerRepository.findBycustomerId(customerId);
    }
    public CustomerEntity findByCustomerId(String customerId) {
        // TODO Auto-generated method stub
        return customerRepository.findByCustomerId(customerId);
    }

    public CustomerResponse getCustomerById(String customerId) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            log.info("the size of the customers is {} : " , customerOptional.isPresent());
            log.info("the retrieved customer is {} : ", customerOptional);
            return customerMapper.responseToEntity(customerOptional.get()); // Return the mapped result
        } else {
            return null; // Return null if the customer is not found
        }
    }

    public boolean logoutCustomerByEmail(String email) {
        CustomerEntity customer = customerRepository.findByEmail(email);

        if (customer != null) {
            customer.setLastLogoutTime(new Timestamp(System.currentTimeMillis()));
            customerRepository.save(customer);
            return true; // Logout successful
        }
        return false; // Customer not found or could not be logged out
    }

    public void updateCustomer(String customerId, CustomerRequest customerRequest) {
        // Retrieve the existing customer by customerId
        CustomerEntity existingCustomer = customerRepository.findByCustomerId(customerId);

        if (existingCustomer == null) {
            log.info("The customer id is not found in the db {} ",existingCustomer.getCustomerId());
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }

        existingCustomer.setFirst_name(customerRequest.getFirst_name());
        existingCustomer.setLast_name(customerRequest.getLast_name());
        existingCustomer.setMobile_no(customerRequest.getMobile_no());
        existingCustomer.setPan_no(customerRequest.getPan_no());
        existingCustomer.setEmail(customerRequest.getEmail());
        existingCustomer.setPassword(customerRequest.getPassword());

        // Save the updated customer entity back to the repository
        customerRepository.save(existingCustomer);
    }


    public void forgotPasswordOtp(String email) {
        // Retrieve the existing customer by email
        CustomerEntity existingCustomer = customerRepository.findByEmail(email);

        if (existingCustomer == null) {
            throw new CustomerNotFoundException("Customer with email " + email + " not found.");
        }

        String otp = generateOtp();
        // Send the OTP via email (you need to implement this)
        sendOtpByEmail(existingCustomer.getEmail(), otp);

        // Update the customer's OTP in the database
        existingCustomer.setOtp(otp);
        customerRepository.save(existingCustomer);
    }

    public boolean verifyOtpAndResetPassword(String otp, String password, String email) {
        System.out.println("Received OTP: " + otp);
        System.out.println("Received newPassword: " + password);
        System.out.println("Received email: " + email);

        CustomerEntity entity = customerRepository.findByEmail(email);

        if (entity == null) {
            System.out.println("Customer not found for email: " + email);
            return false; // Customer not found
        }

        if (!otp.equals(entity.getOtp())) {
            System.out.println("Invalid OTP. Expected OTP: " + entity.getOtp());
            return false; // Invalid OTP
        }

        // OTP is valid, reset the password
        entity.setPassword(password);
        entity.setOtp(null); // Clear the stored OTP
        customerRepository.save(entity);

        return true;
    }
    public boolean existsById(String customerId) {
        return customerRepository.existsById(customerId);
    }

    public void uploadFile(String customerId,List<MultipartFile> files) throws IOException {
        CustomerEntity customer = findByCustomerId(customerId);

        if (customer == null) {
            // Handle the case where the customer is not found, you can throw an exception or handle it as needed
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
        }
        if (!files.isEmpty()) {
            List<String> fileNames = new ArrayList<>();
            for (MultipartFile file : files) {
                String fileName = PATH + file.getOriginalFilename();
                byte[] document = file.getBytes();
                String fileType = file.getContentType();
                file.transferTo(new File(fileName));
                fileNames.add(fileName);
                // Process document data as needed...
            }
            customer.setCustomerFilePaths(fileNames);
        }
        customerRepository.save(customer);
    }

    private String previousCount = "";
    private LocalDate lastUpdatedDate = LocalDate.now();
    public int countCustomers() {
        try {
            return (int) customerRepository.count(); // Count all employees in the database
        } catch (Exception e) {
            log.error("Failed to count employees: {}", e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to count employees");
        }
    }

}

