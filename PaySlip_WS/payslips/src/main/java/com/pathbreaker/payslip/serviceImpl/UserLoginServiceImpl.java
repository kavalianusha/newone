package com.pathbreaker.payslip.serviceImpl;

import com.pathbreaker.payslip.entity.UserLogin;
import com.pathbreaker.payslip.exception.Exceptions;
import com.pathbreaker.payslip.exception.NotFoundException;
import com.pathbreaker.payslip.mappers.UserLoginMapper;
import com.pathbreaker.payslip.repository.UserLoginRepository;
import com.pathbreaker.payslip.request.UserLoginRequest;
import com.pathbreaker.payslip.response.ResultResponse;
import com.pathbreaker.payslip.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    public UserLoginServiceImpl(UserLoginMapper userLoginMapper,
                                UserLoginRepository userLoginRepository,
                                JavaMailSenderImpl javaMailSender){
        this.userLoginMapper = userLoginMapper;
        this.userLoginRepository = userLoginRepository;
        otpExpiryMap = new HashMap<>();
        this.javaMailSender = javaMailSender;
    }
    private final Map<String, LocalDateTime> otpExpiryMap;
    private final JavaMailSenderImpl javaMailSender;
    private final UserLoginMapper userLoginMapper;
    private final UserLoginRepository userLoginRepository;

    @Override
    public ResponseEntity<?> sendOtpToEmail(UserLoginRequest userLoginRequest) {
        try {
            UserLogin userLoginEntity = userLoginRepository.findByEmailIdOrUserName(userLoginRequest.getEmailId(), userLoginRequest.getUserName());
            if (userLoginEntity == null) {
                log.info("EmailId or UserName is null : {}, {}", userLoginEntity.getEmailId());
                ResultResponse result = new ResultResponse();
                result.setResult("EmailId or UserName is Null");
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The EmailId or UserName is not found it is null");
            }

            String storedPassword = userLoginEntity.getPassword();
            System.out.println(storedPassword);

            if (storedPassword == null) {
                log.info("Password is null for emailId or userName : {} {}", userLoginEntity.getEmailId());
                ResultResponse result = new ResultResponse();
                result.setResult("Password is null for emailID or userName : " + userLoginEntity.getEmailId());
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The Password is not found it is null");
            }

            if (!userLoginRequest.getPassword().equals(storedPassword)) {
                ResultResponse result = new ResultResponse();
                result.setResult("Incorrect password");
                log.info("Incorrect password for emailId: {}", userLoginEntity.getEmailId());

                throw new NotFoundException(HttpStatus.NOT_ACCEPTABLE, "The Password is Incorrect for EmailId : " + userLoginEntity.getEmailId());
            }
            Long otp = generateOtp();
            userLoginEntity.setOtp(otp);
            LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);
            userLoginEntity.setExpiryTime(expiryTime);
            System.out.println("The otp will expiry in :" + expiryTime);

            sendOtpByEmail(userLoginEntity.getEmailId(), otp);
            userLoginRepository.save(userLoginEntity);

            otpExpiryMap.put(userLoginRequest.getOtp(), expiryTime);

            log.info(" OTP sent to the emailId : {}", userLoginEntity.getEmailId(), userLoginRequest.getPassword());
            ResultResponse result = new ResultResponse();
            result.setResult("OTP sent successfully to " + userLoginEntity.getEmailId() + " please login with that otp ");
            return ResponseEntity.ok(result);
        }
        catch (Exceptions ex){
            log.warn("An error during the otp sending to the emailId {} : ",userLoginRequest.getEmailId());
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "An error during the otp sending to the emailId : "+userLoginRequest.getEmailId());
        }
    }

    @Override
    public ResponseEntity<?> loginUser(UserLoginRequest userLoginRequest) {
        try {
            UserLogin userLoginEntity = userLoginRepository.findByOtp(userLoginRequest.getOtp());
            if (userLoginEntity == null) {
                ResultResponse result = new ResultResponse();
                result.setResult("The OTP is null or InValid ");
                log.info("The OTP is null or Invalid");
                throw new NotFoundException(HttpStatus.NOT_ACCEPTABLE, " OTP is null or Invalid " );
            }
            LocalDateTime expiryTime = userLoginEntity.getExpiryTime();
            System.out.println("The login otp will expire in: " + expiryTime);

            if (expiryTime == null || LocalDateTime.now().isAfter(expiryTime)) {
                // OTP has expired
                otpExpiryMap.remove(userLoginEntity.getOtp());
                ResultResponse result = new ResultResponse();
                result.setResult("OTP has expired");
                log.info("OTP has expired");
                throw new NotFoundException(HttpStatus.NOT_ACCEPTABLE, "OTP has expired");
            }
            // OTP is valid within the 5-minute window
            userLoginEntity.setLastLoginTime(new Date());
            userLoginRepository.save(userLoginEntity);
            otpExpiryMap.remove(userLoginRequest.getOtp());

            ResultResponse result = new ResultResponse();
            log.info(" Login successfull to the emailId : {}" ,userLoginEntity.getEmailId());
            result.setResult("Login successfull to the emailId : " + userLoginEntity.getEmailId());
            result.setUserId(userLoginEntity.getUserEntity().getUserId());
            result.setUserName(userLoginEntity.getUserName());
            result.setRole(userLoginEntity.getRole());
            result.setId(userLoginEntity.getId());
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
