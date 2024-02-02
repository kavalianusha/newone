package com.example.efilingbazaar.response;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
public class CustomerResponse {

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
    private List<String> customerFilePaths;
    private String fileType;
    private Timestamp lastLogoutTime;
    private Timestamp lastLoginTime;
}
