package com.example.hostinghub.request;

import com.example.hostinghub.entity.PasswordsEntity;
import lombok.Data;

@Data
public class HostingUpdateRequest {
    private String hostingProvider;
    private String url;
    private String login;
    private String password;
    private String emailId;
    private String registrationPhoneNumber;
    private String registrationDomain;
    private String registrationDate; // Holds the date of domain registration
    private String expiryDate;
    private String hostingCpannelUrl;
    private String userName;
    private String hostingDnsName;
    private Long daysLeft;
    private PasswordsEntity passwordsEntity;
}
