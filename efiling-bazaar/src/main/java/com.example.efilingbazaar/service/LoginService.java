package com.example.efilingbazaar.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.example.efilingbazaar.entity.Admin;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.efilingbazaar.entity.LogEntity;
import com.example.efilingbazaar.entity.LoginEntity;
import com.example.efilingbazaar.entity.OtpEntity;
import com.example.efilingbazaar.exception.InvalidOtpException;
import com.example.efilingbazaar.repository.LogRepository;
import com.example.efilingbazaar.repository.LoginRepository;
import com.example.efilingbazaar.repository.OtpRepository;
import com.example.efilingbazaar.response.ResultResponse;

@Service
@Slf4j
public class LoginService {


    private static final Timestamp INVALID_TIMESTAMP = new Timestamp(0);
    private LoginRepository loginRepository;
    private OtpRepository otpRepository;
    private JavaMailSender javaMailSender;
    private Map<String, LocalDateTime> otpExpiryMap = new HashMap<>();
    private LogRepository logRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository,
                        LogRepository logRepository,
                        OtpRepository otpRepository,
                        JavaMailSender javaMailSender) {
        this.loginRepository = loginRepository;
        this.otpRepository = otpRepository;
        this.javaMailSender = javaMailSender;
        this.logRepository = logRepository;
    }

    public void sendOtp(String emailId, String password) {
        LoginEntity login = loginRepository.findByEmailIdAndPassword(emailId,password);

        if (login == null) {
            throw new IllegalArgumentException("Invalid email");
        }
        String otp = generateOTP();
        OtpEntity otpEntity = otpRepository.findByEmailId(emailId);

        if (otpEntity == null) {
            otpEntity = new OtpEntity();
        }

        otpEntity.setEmailId(emailId);
        otpEntity.setOtp(otp);
        otpRepository.save(otpEntity);

        sendOTPToEmail(emailId, otp);

        otpExpiryMap.put(emailId, LocalDateTime.now().plusMinutes(5));
        log.info("OTP sent for email: {}", emailId);
    }


    public void login(String emailId, String otp) {
        OtpEntity otpEntity = otpRepository.findByEmailIdAndOtp(emailId, otp);

        if (otpEntity == null) {
            ResultResponse result = new ResultResponse();
            result.setResult(" Invalid OTP");
            log.info("invalid otp for email: {}", emailId);
            throw new InvalidOtpException(result);
        }

        LocalDateTime otpExpiryTime = otpExpiryMap.get(emailId);

        if (otpExpiryTime == null || LocalDateTime.now().isAfter(otpExpiryTime)) {
            otpRepository.delete(otpEntity);
            otpExpiryMap.remove(emailId);

            ResultResponse result = new ResultResponse();
            result.setResult("OTP expired");
            log.info("otp is expired for email: {}", emailId);
            throw new InvalidOtpException(result);
        }

        otpEntity.setOtp(null);
        otpRepository.save(otpEntity);
        otpExpiryMap.remove(emailId);

        updateLastLoginTime(emailId);

        ResultResponse result = new ResultResponse();
        log.info("Login successful for email: {}", emailId);
        result.setResult("Login successful");
        return;
    }
    private void updateLastLoginTime(String emailId) {
        LoginEntity login = loginRepository.findByEmailId(emailId);
        if (login != null) {
            login.setLastLoginTime();
            loginRepository.save(login);

            // Create a log entry for the login and store its ID in the Login object
            LogEntity loginLogEntityEntry = createLogEntityEntry(emailId, login.getEmployeeId(), "Login");
            login.setLoginLogId(loginLogEntityEntry.getId());  // Store the log entry ID
            loginRepository.save(login);
        }
    }

    public void logout(String emailId) {
        LoginEntity login = loginRepository.findByEmailId(emailId);
        System.out.println("login "+login);
        if (login != null) {
            login.setLastLogoutTime();
            loginRepository.save(login);

            System.out.println("loginemailId "+login.getLoginEmployeeId());
            // Update the sessionLastLogoutTime in the corresponding login log entry
            if (login.getLoginEmployeeId() != null) {
                System.out.println("Calling updateSessionLastLogoutTimeInLogEntry with logEntryId: " + login.getLoginEmployeeId());
                updateSessionLastLogoutTimeInLogEntityEntry((String) login.getLoginEmployeeId(), (Timestamp) login.getLastLoginTime());
            }
        }
    }

    // When creating a log entry for login
    public LogEntity createLogEntityEntry(String emailId, String employeeId, String action) {
        LogEntity logEntry = new LogEntity();
        logEntry.setEmailId(emailId);
        logEntry.setEmployeeId(employeeId);
        logEntry.setAction(action);
        logEntry.setSessionLastLoginTime(Timestamp.from(OffsetDateTime.now().toInstant()));
        // Set other fields as needed
        return logRepository.save(logEntry);
    }

    // When updating sessionLastLogoutTime in the log entry
    public void updateSessionLastLogoutTimeInLogEntityEntry(String logEmployeeId, Timestamp lastlogintime) {
        System.out.println("Updating sessionLastLogoutTime for logEntryId: " + logEmployeeId+"  "+lastlogintime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newtime = 	format.format(lastlogintime);

        LogEntity logEntry = logRepository.findByEmployee(logEmployeeId, newtime);
        System.out.println(logEntry);

        if (logEntry != null) {

            // for(Log log: logEntry) {
            logEntry.setSessionLastLogoutTime(Timestamp.from(OffsetDateTime.now().toInstant()));
            logRepository.save(logEntry);
            //  }
        }
    }

    private String generateOTP() {
        // Generate a random 6-digit OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private void sendOTPToEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("OTP for login");
        message.setText("Your OTP for login is: " + otp);
        javaMailSender.send(message);
    }
    public void initiatePasswordReset(String emailId) {
        // Check if the email exists in the database
        LoginEntity login = loginRepository.findByEmailId(emailId);
        if (login == null) {
            throw new IllegalArgumentException("Invalid email");
        }

        // Generate OTP and save it in the database
        String otp = generateOTP();
        OtpEntity otpEntity = otpRepository.findByEmailId(emailId); // Check if an OTP entry already exists for the email ID
        if (otpEntity == null) {
            otpEntity = new OtpEntity();
        }
        otpEntity.setEmailId(emailId);
        otpEntity.setOtp(otp);
        otpRepository.save(otpEntity);

        // Send OTP to the email address
        sendOTPToEmail(emailId, otp);

        // Store the OTP and its expiry time
        otpExpiryMap.put(emailId, LocalDateTime.now().plusMinutes(5));
        log.info("Password reset OTP sent for email: {}", emailId);
    }
    @Transactional
    public void resetPassword(String emailId, String otp, String password) {
        // Check if OTP exists in the repository
        OtpEntity otpEntity = otpRepository.findByEmailIdAndOtp(emailId, otp);
        if (otpEntity == null) {
            ResultResponse result = new ResultResponse();
            result.setResult("Invalid OTP");
            log.info("Invalid OTP for email: {}", emailId);
            throw new InvalidOtpException(result);
        }

        // Check if the OTP is expired
        LocalDateTime otpExpiryTime = otpExpiryMap.get(emailId);
        if (otpExpiryTime == null || LocalDateTime.now().isAfter(otpExpiryTime)) {
            otpRepository.delete(otpEntity);
            otpExpiryMap.remove(emailId);
            ResultResponse result = new ResultResponse();
            result.setResult("OTP expired");
            log.info("OTP is expired for email: {}", emailId);
            throw new InvalidOtpException(result);
        }

        // Update the password in the database
        loginRepository.updatePassword(emailId, password);

        // Clear the OTP and its expiry time
        otpEntity.setOtp(null);
        otpRepository.save(otpEntity);
        //otpExpiryMap.remove(emailId);

        log.info("Password reset successful for email: {}", emailId);
    }


}