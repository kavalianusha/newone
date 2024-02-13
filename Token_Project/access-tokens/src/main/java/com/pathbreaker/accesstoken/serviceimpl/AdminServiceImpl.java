package com.pathbreaker.accesstoken.serviceimpl;

import com.pathbreaker.accesstoken.entity.Admin;
import com.pathbreaker.accesstoken.exception.Exceptions;
import com.pathbreaker.accesstoken.exception.NotFoundException;
import com.pathbreaker.accesstoken.helper.TokensUtil;
import com.pathbreaker.accesstoken.repository.AdminRepository;
import com.pathbreaker.accesstoken.request.AdminRequest;
import com.pathbreaker.accesstoken.response.ResultResponse;
import com.pathbreaker.accesstoken.service.AdminService;
import com.pathbreaker.accesstoken.config.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository,
                            JavaMailSender javaMailSender,
                            TokensUtil tokensUtil,
                            Twilio twilioService) {
        this.adminRepository = adminRepository;
        this.twilioService = twilioService;
        otpExpiryMap = new HashMap<>();
        this.javaMailSender = javaMailSender;
        this.tokensUtil = tokensUtil;
    }
    private final AdminRepository adminRepository;
    private final JavaMailSender javaMailSender;
    private final Map<Long, LocalDateTime> otpExpiryMap;
    private final TokensUtil tokensUtil;
    private final Twilio twilioService;

    @Override
    public ResponseEntity<?> sendOtpToPhone(AdminRequest request) {
        try {
            Long otp = generateOtp();
            sendOtpToPhoneNo(request.getCountryCode(),request.getPhoneNo(),request.getUserName(),otp);
            System.out.println("The request method : "+request.getCountryCode()+request.getPhoneNo());
            System.out.println("-----------");
        }catch (Exceptions ex) {
            throw new Exceptions(HttpStatus.UNAUTHORIZED,"There is a error send otp method"+ex);
        }
        return null;
    }

    public void sendOtpToPhoneNo(String countryCode,String phoneNo,String userName, Long otp) {
            String messageBody = " \n Dear " + userName + ",\n\nYour OTP for login is: " + otp + "\n\n"
                    + "Above OTP is valid for 5 minutes, please do not share OTP with anyone.\n\n"
                    + "Sincerely,\n\nPaySlip Team\nMobile: +1234567890\nWebsite: payslips.com";

            try {
                com.twilio.Twilio.init(twilioService.getAccountSid(), twilioService.getAuthToken());
                System.out.println("THe application from no : "+twilioService.getPhoneNumber());
                System.out.println("THe to number : "+countryCode + phoneNo);

                Message message = Message.creator(
                                new PhoneNumber(countryCode + phoneNo),
                                new PhoneNumber(twilioService.getPhoneNumber()),
                                messageBody).create();


                System.out.println("OTP sent successfully to: " + userName);
            } catch (Exceptions ex) {
               throw new Exceptions(HttpStatus.UNAUTHORIZED,"There is a error "+ex);
            }
        }

    public void sendOtpCall(String countryCode, String phoneNo, String userName, Long otp) {
        String twilioPhoneNumber = twilioService.getPhoneNumber();

        try {
            com.twilio.Twilio.init(twilioService.getAccountSid(), twilioService.getAuthToken());

            // Construct the message to be spoken in the call
            String callMessage = "Hello " + userName + ". Your OTP for login is " + otp +
                    ". This OTP is valid for 5 minutes. Do not share it with anyone. " +
                    "Thank you for using PaySlip services.";

            // Create the call
            Call call = Call.creator(
                            new PhoneNumber(countryCode + phoneNo),
                            new PhoneNumber(twilioPhoneNumber),
                            new URI("http://demo.twilio.com/docs/voice.xml"))  // Replace with your TwiML URL or TwiML content
                    .create();

            System.out.println("Call initiated successfully to: " + userName);
        } catch (Exception ex) {
            throw new RuntimeException("There was an error initiating the call: " + ex.getMessage(), ex);
        }
    }


    private Long generateOtp() {
        Random random = new Random();
        Long otp = 100000 + random.nextLong(900000);
        return otp;
    }
    @Override
    public ResponseEntity<?> sendOtpToEmail(AdminRequest request){
        try {
            Admin admin = adminRepository.findByEmailIdOrUserName(request.getEmailId(),request.getUserName());
            if (admin == null) {
                log.info("EmailId or UserName is null : {}, {}", request.getEmailId(),request.getUserName());
                ResultResponse result = new ResultResponse();
                result.setResult("EmailId is Null");
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The EmailId or Username is not found it is null");
            }

            String storedPassword = admin.getPassword();
            System.out.println(storedPassword);

            if (storedPassword == null) {
                log.info("Password is null for emailId: {}", request.getEmailId());
                ResultResponse result = new ResultResponse();
                result.setResult("Password is null for emailID : " +request.getEmailId());
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The Password is not found it is null");
            }

            if (!request.getPassword().equals(storedPassword)) {
                ResultResponse result = new ResultResponse();
                result.setResult("Incorrect password");
                log.info("Incorrect password for emailId: {}", request.getEmailId());

                throw new NotFoundException(HttpStatus.NOT_ACCEPTABLE, "The Password is Incorrect for EmailId : " + request.getEmailId());
            }
            Long otp = generateOtp();
            admin.setOtp(otp);

            sendOtpByEmail(admin.getEmailId(), otp);
            LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(5);
            admin.setExpiryTime(expiryTime);
            System.out.println("The otp will expiry in :" + expiryTime);

            adminRepository.save(admin);
            otpExpiryMap.put(request.getOtp(), expiryTime);
            String accessToken = tokensUtil.generateAccessToken(admin.getUserName());
            String refreshToken = tokensUtil.generateRefreshToken(admin.getUserName());
            if (accessToken.equals(refreshToken))
                System.out.println("the tokens are same");
            else
                System.out.println("The tokens are different");
            log.info(" OTP sent to the emailId : {}", request.getEmailId(), request.getPassword());
            ResultResponse result = new ResultResponse();
            result.setResult("OTP sent successfully to " + request.getEmailId() + " please login with that otp ");
            result.setAccessToken(accessToken);
            result.setRefreshToken(refreshToken);
            return ResponseEntity.ok(result);
        }
        catch (Exception ex) {
            log.warn("An error during the otp sending to the emailId {} : \n{}", request.getEmailId(), ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error during the otp sending to the emailId : " + request.getEmailId() + "             " + ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> loginAdmin(AdminRequest loginRequest) {
        return null;
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


}
