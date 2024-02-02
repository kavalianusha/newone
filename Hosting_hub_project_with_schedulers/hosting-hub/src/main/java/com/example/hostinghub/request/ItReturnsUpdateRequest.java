package com.example.hostinghub.request;

import com.example.hostinghub.entity.PasswordsEntity;
import lombok.Data;

@Data
public class ItReturnsUpdateRequest {


    private String serviceType;
    private String emailId;
    private String registeredMobileNo;
    private String registrationDate; // Holds the date of domain registration
    private String expiryDate;
    private String loginUrl;
    private String userName;
    private String password;
    private Long daysLeft;
    private String createdBy;
    private PasswordUpdateRequest passwordUpdateRequest;

}