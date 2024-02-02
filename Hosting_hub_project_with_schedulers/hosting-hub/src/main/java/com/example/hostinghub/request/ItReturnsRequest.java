package com.example.hostinghub.request;

import java.time.LocalDate;

import com.example.hostinghub.entity.PasswordsEntity;
import lombok.Data;

@Data
public class ItReturnsRequest {
    private String customerId;
    private String serviceType;
    private String emailId;
    private String registeredMobileNo;
    private String registrationDate; // Holds the date of domain registration
    private String expiryDate;
    private String loginUrl;
    private String userName;
    private String password;
    private String createdBy;
    private String createdDate;
    private Long daysLeft;
    private PasswordsEntity passwordsEntity;
}