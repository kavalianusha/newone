package com.pathbreaker.payslip.serviceImpl;

import com.pathbreaker.payslip.entity.EmployeeLogin;
import com.pathbreaker.payslip.exception.Exceptions;
import com.pathbreaker.payslip.exception.NotFoundException;
import com.pathbreaker.payslip.mappers.EmployeeLoginMappers;
import com.pathbreaker.payslip.repository.EmployeeLoginRepository;
import com.pathbreaker.payslip.request.EmployeeLoginRequest;
import com.pathbreaker.payslip.response.ResultResponse;
import com.pathbreaker.payslip.service.EmployeeLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class EmployeeLoginServiceImpl implements EmployeeLoginService {

    @Autowired
    public EmployeeLoginServiceImpl(EmployeeLoginMappers loginMappers,
                                    EmployeeLoginRepository loginRepository,
                                    JavaMailSenderImpl javaMailSender){
        this.loginMappers = loginMappers;
        this.loginRepository = loginRepository;
        otpExpiryMap = new HashMap<>();
        this.javaMailSender = javaMailSender;
    }
    private final Map<Long, LocalDateTime> otpExpiryMap;
    private final JavaMailSenderImpl javaMailSender;
    private final EmployeeLoginMappers loginMappers;
    private final EmployeeLoginRepository loginRepository;

    @Override
    public ResponseEntity<?> sendOtpToEmail(EmployeeLoginRequest loginRequest) {
        try {
        EmployeeLogin loginEntity = loginRepository.findByEmailId(loginRequest.getEmailId());
        if (loginEntity == null) {
            log.info("EmailId is null : {}, {}", loginRequest.getEmailId());
            ResultResponse result = new ResultResponse();
            result.setResult("EmailId is Null");
            throw new NotFoundException(HttpStatus.NOT_FOUND, "The EmailId is not found it is null");
        }

        String storedPassword = loginEntity.getPassword();
        System.out.println(storedPassword);

        if (storedPassword == null) {
            log.info("Password is null for emailId: {}", loginRequest.getEmailId());
            ResultResponse result = new ResultResponse();
            result.setResult("Password is null for emailID : " + loginRequest.getEmailId());
            throw new NotFoundException(HttpStatus.NOT_FOUND, "The Password is not found it is null");
        }

        if (!loginRequest.getPassword().equals(storedPassword)) {
            ResultResponse result = new ResultResponse();
            result.setResult("Incorrect password");
            log.info("Incorrect password for emailId: {}", loginRequest.getEmailId());

            throw new NotFoundException(HttpStatus.NOT_ACCEPTABLE, "The Password is Incorrect for EmailId : " + loginRequest.getEmailId());
        }
        Long otp = generateOtp();
        loginEntity.setOtp(otp);

        sendOtpByEmail(loginEntity.getEmailId(), otp);
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);
        loginEntity.setExpiryTime(expiryTime);
        System.out.println("The otp will expiry in :" + expiryTime);

        loginRepository.save(loginEntity);
        otpExpiryMap.put(loginRequest.getOtp(), expiryTime);

        log.info(" OTP sent to the emailId : {}", loginRequest.getEmailId(), loginRequest.getPassword());
        ResultResponse result = new ResultResponse();
        result.setResult("OTP sent successfully to " + loginRequest.getEmailId() + " please login with that otp ");
        return ResponseEntity.ok(result);
    }
     catch (Exceptions ex){
        log.warn("An error during the otp sending to the emailId{} : ",loginRequest.getEmailId());
        throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "An error during the otp sending to the emailId : "+loginRequest.getEmailId());
    }
 }

    @Override
    public ResponseEntity<?> loginAdmin(EmployeeLoginRequest loginRequest) {
       try {
           EmployeeLogin loginEntity = loginRepository.findByOtp(loginRequest.getOtp());
           if (loginEntity == null) {
               ResultResponse result = new ResultResponse();
               result.setResult("The OTP is null or InValid ");
               log.info("The OTP is null or Invalid");
               throw new NotFoundException(HttpStatus.NOT_ACCEPTABLE, " OTP is null or Invalid " );
           }
           LocalDateTime expiryTime = loginEntity.getExpiryTime();
           System.out.println("The login otp will expire in: " + expiryTime);

           if (expiryTime == null || LocalDateTime.now().isAfter(expiryTime)) {
               // OTP has expired
               otpExpiryMap.remove(loginEntity.getOtp());
               ResultResponse result = new ResultResponse();
               result.setResult("OTP has expired");
               log.info("OTP has expired");
               throw new NotFoundException(HttpStatus.NOT_ACCEPTABLE, "OTP has expired");
           }
           // OTP is valid within the 5-minute window
           loginEntity.setLastLoginTime(new Date());
           loginRepository.save(loginEntity);
           otpExpiryMap.remove(loginRequest.getOtp());

           ResultResponse result = new ResultResponse();
           log.info(" Login successfull to the emailId : {}" ,loginEntity.getEmailId());
           result.setResult("Login successfull to the emailId : " + loginEntity.getEmailId());
           result.setUserId(loginEntity.getEmployeeEntity().getEmployeeId());
           result.setRole(loginEntity.getRole());
           result.setUserName(loginEntity.getEmployeeEntity().getFirstName()+" "+loginEntity.getEmployeeEntity().getLastName());
           result.setId(loginEntity.getId());
           return ResponseEntity.ok(result);
       }
       catch (Exceptions ex){
        log.warn("An error occured during the login {} : ",ex);
        throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured during the login : "+ex);
       }
}

    public void sendOtpByEmail(String emailId, Long otp) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("One Time Password for login on PaySlips Application.");
        mailMessage.setText("Dear "+emailId+" ,"+"\n\n"+"Your OTP for login is : " + otp+"\n\n"+
                " Above OTP is valid for 5 minutes, pls do not share OTP with anyone."+"\n\n"+
                "Sincerely,"+"\n\n"+"PaySlip Team"+"\n"+"Mobile: +1234567890"+"\n"+"Website: payslips.com");

        javaMailSender.send(mailMessage);
        log.info("OTP sent successfully to: {}", emailId);
    }

    private Long generateOtp() {
        Random random = new Random();
        Long otp = 100000 + random.nextLong(900000);
        return otp;
    }


}
