package com.example.efilingbazaar.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import com.example.efilingbazaar.exception.InvalidOtpException;
import com.example.efilingbazaar.repository.AdminRepository;
import com.example.efilingbazaar.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.efilingbazaar.entity.Admin;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService {

    private final AdminRepository adminRepository;
    private final Map<String, LocalDateTime> otpExpiryMap = new HashMap<>();
    private Admin superAdmin;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;

        String superAdminEmailId = "pbtjava@gmail.com";
        String superAdminPassword = "pbtjava@123";

        superAdmin = adminRepository.findByEmailId(superAdminEmailId);

        if (superAdmin == null) {
            superAdmin = new Admin();
            superAdmin.setEmailId(superAdminEmailId);
            superAdmin.setPassword(superAdminPassword);
            adminRepository.save(superAdmin);
        }
    }


    public void sendOtp(String emailId, String password) {
        // Verify if the email exists in the database and the password is correct
        Admin admin = adminRepository.findByEmailId(emailId);
        if (admin == null) {
            throw new IllegalArgumentException("Invalid email");
        }

        // Verify if the provided password matches the hashed password in the database
        if (!admin.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password");
        }

        // Generate OTP and save it in the database
        String otp = generateOtp();
        Admin admin1 = adminRepository.findByEmailId(emailId); // Check if an OTP entry already exists for the email ID
        if (admin1 == null) {
            admin1 = new Admin();
        }
        admin1.setEmailId(emailId);
        admin1.setOtp(otp);
        adminRepository.save(admin1);

        // Send OTP to the email address
        sendEmail(emailId, otp);

        // Store the OTP and its expiry time
        otpExpiryMap.put(emailId, LocalDateTime.now().plusMinutes(5));
        log.info("OTP sent for email: {}", emailId);

    }

    public void login(String emailId, String otp, String password) {
        // Check if OTP exists in the repository
        Admin admin = adminRepository.findByEmailIdAndOtp(emailId, otp);
        if (admin == null) {
            ResultResponse result = new ResultResponse();
            result.setResult(" Invalid OTP");
            log.info("invalid otp for email: {}", emailId);
            throw new InvalidOtpException(result);
        }

        // Check if the OTP is expired
        LocalDateTime otpExpiryTime = otpExpiryMap.get(emailId);
        if (otpExpiryTime == null || LocalDateTime.now().isAfter(otpExpiryTime)) {
            adminRepository.delete(admin);
            otpExpiryMap.remove(emailId);
            // Remove the OTP expiry time from the map
            ResultResponse result = new ResultResponse();
            result.setResult("OTP expired");
            log.info("otp is expired for email: {}", emailId);
            throw new InvalidOtpException(result);
        }

        // If OTP is valid and not expired, clear the OTP and its expiry time
        admin.setOtp(null); // Set the OTP to null in the OTP entity
        adminRepository.save(admin); // Save the OTP entity with the updated value
        otpExpiryMap.remove(emailId); // Remove the OTP expiry time from the map

        // Update the last login time for the user in the login table
        updateLastLoginTime(emailId);
        ResultResponse result = new ResultResponse();
        log.info("Login successful for email: {}", emailId);
        result.setResult("Login successful");
        return;
    }

    private void updateLastLoginTime(String emailId) {
        Admin admin = adminRepository.findByEmailId(emailId);
        if (admin != null) {
            admin.setLoginTime();
            adminRepository.save(admin);

        }
    }


   /* public void login(String email, String otp, String password) {
        try {
            // Check if OTP exists in the repository
            Admin admin = adminRepository.findByEmailAndOtp(email, otp);
            if (admin == null) {
                throw new InvalidOtpException("Invalid OTP");
            }

            // Check if the OTP is expired
            LocalDateTime otpExpiryTime = otpExpiryMap.get(email);
            if (otpExpiryTime == null || LocalDateTime.now().isAfter(otpExpiryTime)) {
                throw new InvalidOtpException("OTP expired");
            }

            // If OTP is valid and not expired, clear the OTP and its expiry time
            admin.setOtp(null);
            adminRepository.save(admin);
            otpExpiryMap.remove(email);

            // Update the last login time for the user in the login table
            updateLastLoginTime(email);
            ResultResponse result = new ResultResponse();
            log.info("Login successful for email: {}", email);
            result.setResult("Login successful");
            return;
        } catch (InvalidOtpException e) {
            log.warn("Error on login: {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult("Invalid OTP");
            return;
        } catch (Exception e) {
            log.warn("Error on login: {}", e.getMessage(), e);
            ResultResponse result = new ResultResponse();
            result.setResult("Failed to login");
            return;
        }
    }

    private void updateLastLoginTime(String email) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null) {
            admin.setLoginTime();
            adminRepository.save(admin);
        }
    }
*/

    public ResponseEntity<?> logout(String emailId) {
        // Check if the admin is currently logged in (you can use a session or token-based approach for this)
        // For simplicity, I'll assume you have a logged-in status flag in your Admin entity
        Admin admin = adminRepository.findByEmailId(emailId);

        if (admin == null) {
            ResultResponse result = new ResultResponse();
            result.setResult("Admin not found");
            log.info("Admin not found for email: {}", emailId);
            return ResponseEntity.badRequest().body(result);
        }

        // Create a Timestamp for the logout time
        Timestamp logoutTimestamp = new Timestamp(System.currentTimeMillis());

        // Set the logout time using Timestamp
        admin.setLogoutTime(logoutTimestamp);
        adminRepository.save(admin);

        // Clear the logged-in status (you can also invalidate a session or token here)
        admin.setLoggedIn(false);
        adminRepository.save(admin);

        ResultResponse result = new ResultResponse();
        result.setResult("Logout successful");
        log.info("Logout successful for email: {}", emailId);
        return ResponseEntity.ok(result);
    }


    public void initiatePasswordReset(String emailId) {
        // Check if the email exists in the database
        Admin admin = adminRepository.findByEmailId(emailId);
        if (admin == null) {
            throw new IllegalArgumentException("Invalid email");
        }

        // Generate OTP and save it in the database
        String otp = generateOtp();
        admin.setOtp(otp);
        adminRepository.save(admin);

        // Send OTP to the email address
        sendEmail(emailId, otp);

        // Store the OTP and its expiry time
        otpExpiryMap.put(emailId, LocalDateTime.now().plusMinutes(5));
        log.info("Password reset OTP sent for email: {}", emailId);
    }

    public void resetPassword(String emailId, String otp, String password) {
        // Check if OTP exists in the repository
        Admin admin = adminRepository.findByEmailIdAndOtp(emailId, otp);
        if (admin == null) {
            throw new InvalidOtpException("Invalid OTP");
        }

        // Check if the OTP is expired
        LocalDateTime otpExpiryTime = otpExpiryMap.get(emailId);
        if (otpExpiryTime == null || LocalDateTime.now().isAfter(otpExpiryTime)) {
            throw new InvalidOtpException("OTP expired");
        }

        // Update the password in the database
        admin.setPassword(password);
        admin.setOtp(null);
        adminRepository.save(admin);

        // Clear the OTP and its expiry time
        otpExpiryMap.remove(emailId);
        log.info("Password reset successful for email: {}", emailId);
    }

    private String generateOtp() {
        // Generate a random 6-digit OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private void sendEmail(String recipientEmailId, String otp) {
        // SMTP server properties (You can replace this with your actual email sending logic)
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Sender's credentials
        final String senderEmailId = "pbtjava@gmail.com"; // Replace with your email
        final String senderPassword = "jcnsrxqjifkrvqxl"; // Replace with your password

        // Create a session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmailId, senderPassword);
            }
        });

        try {
            // Create a MimeMessage object
            MimeMessage message = new MimeMessage(session);
            // Set From: header
            message.setFrom(new InternetAddress(senderEmailId));
            // Set To: header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmailId));
            // Set Subject: header
            message.setSubject("OTP Verification");
            // Set Content: text/plain
            message.setText("Your OTP: " + otp);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}