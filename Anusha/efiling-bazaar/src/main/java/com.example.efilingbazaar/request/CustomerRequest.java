package com.example.efilingbazaar.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
public class CustomerRequest {

    private String customerId;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile_no;
    private String pan_no;
    private String password;
    private String otp;
    private long otpTime;
    private LocalDate createdDate;
    private Timestamp lastLoginTime;
    private Timestamp lastLogoutTime;
    private List<String> customerFilePaths;
}
