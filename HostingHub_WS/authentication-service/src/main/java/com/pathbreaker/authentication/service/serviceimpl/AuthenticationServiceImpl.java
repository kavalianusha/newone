package com.pathbreaker.authentication.service.serviceimpl;

import com.pathbreaker.authentication.service.config.AppConfig;
import com.pathbreaker.authentication.service.entity.AdminEntity;
import com.pathbreaker.authentication.service.exception.AdminException;
import com.pathbreaker.authentication.service.exception.InvalidInputException;
import com.pathbreaker.authentication.service.helper.AccessTokenUtil;
import com.pathbreaker.authentication.service.helper.RefreshTokenUtil;
import com.pathbreaker.authentication.service.mappers.AuthenticationMapper;
import com.pathbreaker.authentication.service.repository.AdminRepository;
import com.pathbreaker.authentication.service.repository.AuthenticationDetailsRepository;
import com.pathbreaker.authentication.service.request.AuthenticationDetailsPayload;
import com.pathbreaker.authentication.service.response.AdminEntityPayLoad;
import com.pathbreaker.authentication.service.response.ResultResponse;
import com.pathbreaker.authentication.service.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    Logger log = LoggerFactory.getLogger(CustomFilter.class);
    @Autowired
    public AuthenticationServiceImpl(AuthenticationDetailsRepository authenticationDetailsRepository,
                                     AuthenticationMapper authenticationMapper,
                                     AdminRepository adminRepository,
                                     AppConfig appConfig,
                                     AccessTokenUtil accessUtil,
                                     RefreshTokenUtil refreshTokenUtil) {
        this.authenticationDetailsRepository = authenticationDetailsRepository;
        this.authenticationMapper = authenticationMapper;
        this.adminRepository = adminRepository;
        this.accessUtil = accessUtil;
        this.appConfig = appConfig;
        otpExpiryMap = new HashMap<>();
        this.refreshTokenUtil = refreshTokenUtil;

    }

    private final AuthenticationDetailsRepository authenticationDetailsRepository;
    private final AuthenticationMapper authenticationMapper;
    private final AdminRepository adminRepository;
    private final AppConfig appConfig;
    private final AccessTokenUtil accessUtil;
    private final RefreshTokenUtil refreshTokenUtil;
    private final Map<String, LocalDateTime> otpExpiryMap;



    @Override
    public ResponseEntity<?> login(AuthenticationDetailsPayload request) throws Exception {

        try {
            validateRequest(request);
            if (!StringUtils.hasLength(request.getClientId())) {
                System.out.println("the default method is : " + request.getClientId());
                request.setClientId("default");
            }
            AdminEntityPayLoad payLoad = getAdminEntityByUserName(request.getUserName());
            System.out.println("the admin entity pay load is : " + payLoad);

            if (Objects.isNull(payLoad)) {
                throw new InvalidInputException(HttpStatus.UNAUTHORIZED, "Invalid UserName");
            }
            if (StringUtils.hasLength(request.getOtp()) &&
                    !request.getOtp().equalsIgnoreCase(payLoad.getOtp())) {
                throw new InvalidInputException(HttpStatus.UNAUTHORIZED, "Invalid Otp");

            } else if (StringUtils.hasLength(request.getPassword()) &&
                    !request.getPassword().equalsIgnoreCase(payLoad.getPassword())) {
                throw new InvalidInputException(HttpStatus.UNAUTHORIZED, "Invalid password");
            }
            ResponseEntity<?> verify = verifyOtp(request);
            System.out.println("The verify otp method is: " + verify.getStatusCode().is2xxSuccessful());

            if (verify.getStatusCode().is2xxSuccessful()) {
                String responseMessage = verify.getBody().toString();

                if (responseMessage != null) {
                    System.out.println("The verify otp is: " + verify.getStatusCode().is2xxSuccessful());
                    return generateTokens(request);
                }
            }
            else {
                throw new AdminException(HttpStatus.UNAUTHORIZED, this.toString());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        catch (AdminException e){
            throw new AdminException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

        @Override
     public void validateRequest(AuthenticationDetailsPayload request) {
        // Check if clientId, accessExpirationInMin, and refreshExpirationInMin are present
        if (!StringUtils.hasLength(request.getUserName())) {
            throw new InvalidInputException(HttpStatus.BAD_REQUEST, "UserName is required");
        }
        if (!StringUtils.hasLength(request.getPassword()) && !StringUtils.hasLength(request.getOtp())) {
            throw new InvalidInputException(HttpStatus.BAD_REQUEST, "password or otp is required");
        }
    }

    @Override
    public ResponseEntity<?> generateTokens(AuthenticationDetailsPayload request) throws Exception {

        try {
            String accessToken = accessUtil.generateAccessToken(request.getUserName());
            // Generate Refresh token
            String refreshToken = refreshTokenUtil.generateRefreshToken(request.getUserName());

            System.out.println("accessToken is: " + accessToken);
            System.out.println("The user details are: " + request);
            System.out.println("Refresh token is: " + refreshToken);

            AdminEntityPayLoad payLoad = getAdminEntityByUserName(request.getUserName());

            ResultResponse response = new ResultResponse();
            response.setAccessToken(accessToken);
            response.setRefreshToken(refreshToken);
            response.setRole(payLoad.getRole());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            throw new AdminException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> verifyOtp(AuthenticationDetailsPayload request) {
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
            return ResponseEntity.ok(result);
        } else if (admin.getRole().equalsIgnoreCase("hosting")) {
            System.out.println("this is the login for hosting");
            log.info("Login successful for hosting");
            result.setResult("Login successful for hosting");
            result.setRole(admin.getRole());
            return ResponseEntity.ok(result);
        }
        else if(admin.getRole().equalsIgnoreCase("email")){
            System.out.println("this is the login for email");
            log.info("Login successful for email");
            result.setResult("Login successful for email");
            result.setRole(admin.getRole());
            return ResponseEntity.ok(result);
        }
        else if(admin.getRole().equalsIgnoreCase("it returns")){
            System.out.println("this is the login for It returns");
            log.info("Login successful for It returns");
            result.setResult("Login successful for It returns");
            result.setRole(admin.getRole());
            return ResponseEntity.ok(result);
        }
        else {
            result.setResult("login failed for all");
        }
        return ResponseEntity.ok(result);
    }

    private AdminEntityPayLoad getAdminEntityByUserName(String userName) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            AdminEntityPayLoad result = restTemplate.getForObject(appConfig.getHostingHubUserNameUri() + userName, AdminEntityPayLoad.class);

            System.out.println(result);
            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
