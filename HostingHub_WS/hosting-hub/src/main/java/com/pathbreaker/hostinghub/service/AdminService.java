package com.pathbreaker.hostinghub.service;

import com.pathbreaker.hostinghub.entity.AdminEntity;
import com.pathbreaker.hostinghub.exception.AdminException;
import com.pathbreaker.hostinghub.mappers.AdminMapper;
import com.pathbreaker.hostinghub.passwordencrypt.PasswordEncoder;
import com.pathbreaker.hostinghub.repository.AdminRepository;
import com.pathbreaker.hostinghub.request.AdminRequest;
import com.pathbreaker.hostinghub.request.AdminUpdateRequest;
import com.pathbreaker.hostinghub.response.AdminResponse;
import com.pathbreaker.hostinghub.response.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
/*import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class AdminService {

    @Autowired
    public AdminService(AdminRepository adminRepository,
                        AdminMapper adminMapper,
                        JavaMailSender javaMailSender,
                        PasswordEncoder passwordEncoder) {

        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
        this.javaMailSender = javaMailSender;
        otpExpiryMap = new HashMap<>();
        this.passwordEncoder = passwordEncoder;
    }

    private AdminRepository adminRepository;
    private AdminMapper adminMapper;
    private JavaMailSender javaMailSender;
    private final Map<String, LocalDateTime> otpExpiryMap;

    private PasswordEncoder passwordEncoder;



    // Method for registering a new admin
    public ResponseEntity<?> registerAdmin(AdminRequest request) {
        AdminEntity existingAdmin = adminRepository.findByEmailId(request.getEmailId());

        if (existingAdmin != null) {
            // Email already exists in the database
            ResultResponse result = new ResultResponse();
            result.setResult("Email already exists in the db");
            log.info("Email already exists in the db: {}", request.getEmailId());
            return ResponseEntity.badRequest().body(result);
        } else {
            // Email doesn't exist, save the data into the database
            String adminId = generateAdminId();
            AdminEntity newAdmin = adminMapper.entityToRequest(request);
            newAdmin.setAdminId(adminId);
            newAdmin.setPassword(passwordEncoder.encryptPassword(request.getPassword()));
            System.out.println(newAdmin.getPassword());

            sendEmail(request.getEmailId(), request.getPassword());
            /*String encodedPassword = passwordEncoder.encode(request.getPassword());
            newAdmin.setPassword(encodedPassword);
            System.out.println("the encoded password is :" + encodedPassword);*/
            adminRepository.save(newAdmin);

            ResultResponse result = new ResultResponse();
            result.setResult("Registration successful for new Admin please login with your credentials");
            log.info("Admin registration successful: {}", request.getEmailId());
            return ResponseEntity.ok(result);
        }
    }

    private String generateAdminId() {
            String adminId = adminRepository.findHighestAdminId();

            int numericPart = 1;

            if (adminId != null) {
                numericPart = Integer.parseInt(adminId.substring(3)) + 1;
            }

            String idFormat = "AD%03d";
            System.out.println("the admin id is :" + String.format(idFormat,numericPart));
            return String.format(idFormat, numericPart);

        }


    // Method for sending an email to a specified address
    public void sendEmail(String email, String password) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("WELCOME to Hosting Hub App");
        mailMessage.setText("Login with your "+"\n\n"+ "email Id is: " + email + "\n\n"+" password is: " +password+
        "\n\n"+"Sincerely, "+"\n\n"+"HostDomain Team"+"\n"+"Mobile: +1234567890"+"\n"+"Website: hosting.com");

        javaMailSender.send(mailMessage);
        log.info("Password sent successfully to: {}", email);
    }

    // Method for sending an OTP to a specified admin
    public ResponseEntity<?> sendOtp(AdminRequest adminRequest) {
        AdminEntity adminEntity = adminRepository.findByEmailIdOrUserName(adminRequest.getEmailId(), adminRequest.getUserName());
        if (adminEntity == null) {
            log.info("Incorrect email or username: {}, {}", adminRequest.getEmailId(), adminRequest.getUserName());
            ResultResponse result = new ResultResponse();
            result.setResult("Incorrect email or username");
            return ResponseEntity.badRequest().body(result);
        }

        String storedPassword = passwordEncoder.decryptPassword(adminEntity.getPassword());
        System.out.println(storedPassword);

        if (storedPassword == null) {
            log.info("Password is null for email: {}", adminRequest.getEmailId());
            ResultResponse result = new ResultResponse();
            result.setResult("Password is null");
            return ResponseEntity.badRequest().body(result);
        }

        if (!adminRequest.getPassword().equals(storedPassword)) {
            ResultResponse result = new ResultResponse();
            result.setResult("Incorrect password");
            log.info("Incorrect password for email: {}", adminRequest.getEmailId());

            return ResponseEntity.badRequest().body(result);
        }

        String otp = generateOtp();
        adminEntity.setOtp(otp);

        sendOtpByEmail(adminEntity.getEmailId(), otp);
        adminRepository.save(adminEntity);
        otpExpiryMap.put(adminRequest.getEmailId(), LocalDateTime.now().plusMinutes(5));

        log.info(" OTP for sent to the emailId or Username is : {} {}", adminRequest.getEmailId(), adminRequest.getUserName());
        ResultResponse result = new ResultResponse();
        result.setResult("OTP sent successfully for  login");
        return ResponseEntity.ok(result);
    }

    // Method for verifying the provided OTP
    public ResponseEntity<?> verifyOtp(AdminRequest request) {
        AdminEntity admin = adminRepository.findByOtp(request.getOtp());

        if (admin == null) {
            ResultResponse result = new ResultResponse();
            result.setResult("Invalid OTP for Admin");
            log.info("Invalid OTP for Admin");
            return ResponseEntity.badRequest().body(result);
        }
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);

        otpExpiryMap.put(request.getOtp(), expiryTime);

        if (expiryTime == null || LocalDateTime.now().isAfter(expiryTime)) {
            otpExpiryMap.remove(admin.getOtp());
            ResultResponse result = new ResultResponse();
            result.setResult("OTP expired for Admin");
            log.info("OTP expired for Admin");
            return ResponseEntity.badRequest().body(result);
        }

        admin.setOtp(null);
        adminRepository.save(admin);
        otpExpiryMap.remove(request.getOtp());

        ResultResponse result = new ResultResponse();
        if(admin.getRole().equalsIgnoreCase("admin")){
            System.out.println("this is the login for admin");
            log.info("Login successful for admin");
            result.setResult("Login successful for admin");
            result.setRole(admin.getRole());
        } else if (admin.getRole().equalsIgnoreCase("hosting")) {
            System.out.println("this is the login for hosting");
            log.info("Login successful for hosting");
            result.setResult("Login successful for hosting");
            result.setRole(admin.getRole());
        }
        else if(admin.getRole().equalsIgnoreCase("email")){
            System.out.println("this is the login for email");
            log.info("Login successful for email");
            result.setResult("Login successful for email");
            result.setRole(admin.getRole());
        }
        else if(admin.getRole().equalsIgnoreCase("it returns")){
            System.out.println("this is the login for It returns");
            log.info("Login successful for It returns");
            result.setResult("Login successful for It returns");
            result.setRole(admin.getRole());
        }
        else {
            result.setResult("login failed for all");
        }
       /* log.info("Login successful for  emailId: {}" , admin.getEmailId());
        result.setResult(" Login successful for  with emailId: " + admin.getEmailId());*/
        return ResponseEntity.ok(result);
    }

    // Method for sending an OTP via email
    public void sendOtpByEmail(String emailId, String otp) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("One Time Password for login on Hosting Hub Application.");
        mailMessage.setText("Dear "+emailId+" ,"+"\n\n"+"Your OTP for login is : " + otp+"\n\n"+
                " Above OTP is valid for 5 minutes, pls do not share OTP with anyone."+"\n\n"+
                "Sincerely,"+"\n\n"+"HostDomain Team"+"\n"+"Mobile: +1234567890"+"\n"+"Website: hosting.com");

        javaMailSender.send(mailMessage);
        log.info("OTP sent successfully to: {}", emailId);
    }

    // Method for generating a random OTP
    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Method for retrieving all admins
    public List<AdminResponse> getAllAdmins() {
        List<AdminEntity> admins = adminRepository.findAll();

        for (AdminEntity admin : admins) {
            String decryptedPassword = passwordEncoder.decryptPassword(admin.getPassword());
            admin.setPassword(decryptedPassword);
        }
        log.info("The size of the admin list is: {}", admins.size());


        return adminMapper.responseToEntityList(admins);
    }

    // Method for retrieving an admin by their ID
    public AdminResponse getAdminById(String adminId) {
        Optional<AdminEntity> adminEntityOptional = adminRepository.findById(adminId);

        if (adminEntityOptional.isPresent()) {
            AdminEntity adminEntity = adminEntityOptional.get();

            // Decrypt password in the retrieved admin entity
            String decryptedPassword = passwordEncoder.decryptPassword(adminEntity.getPassword());
            adminEntity.setPassword(decryptedPassword);

            log.info("The size of the admin is: {}", adminEntityOptional.isPresent());
            // log.info("The retrieved admin is: {}", adminEntityOptional);
            return adminMapper.responseToEntity(adminEntity);
        } else {
            return null;
        }
    }

    // Method for checking if an admin with a specified ID exists
    public boolean existsById(String adminId) {
        return adminRepository.existsById(adminId);
    }

    // Method for updating an existing admin
    public AdminEntity updateAdmin(String adminId, AdminUpdateRequest adminRequest) {
        AdminEntity existingAdmin = adminRepository.findByAdminId(adminId);

        if (existingAdmin == null) {
            log.info("The admin id is not found in the db: {}", adminId);
            ResultResponse response = new ResultResponse();
            response.setResult("Admin with ID " + adminId + " not found.");
            throw new AdminException(response);
        }
        AdminEntity newAdmin = adminMapper.updateEntityFromRequest(adminRequest, existingAdmin);
        newAdmin.setPassword(passwordEncoder.encryptPassword(adminRequest.getPassword()));
        System.out.println(newAdmin.getPassword());
        adminRepository.save(newAdmin);
        log.info("admin updated successfully going to controller class");
        return newAdmin;
    }

    // Method for deleting an admin by their ID
    public void deleteAdminById(String adminId) {
        AdminEntity admin = adminRepository.findByAdminId(adminId);
        if (admin == null) {
            log.info("The admin id is not found in the db: {}", adminId);
            throw new AdminException("Admin with ID " + adminId + " not found.");
        }
        adminRepository.deleteById(adminId);
        log.info("admin is deleted successfully going to controller class....");
    }
}