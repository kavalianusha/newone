package com.pathbreaker.hostinghub.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ItReturnsResponseView {
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
    private LocalDate createdDate;
    private Long daysLeft;
}
