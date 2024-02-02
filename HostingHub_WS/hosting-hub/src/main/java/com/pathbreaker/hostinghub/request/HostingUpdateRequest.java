package com.pathbreaker.hostinghub.request;

import com.pathbreaker.hostinghub.entity.PasswordsEntity;
import lombok.Data;

@Data
public class HostingUpdateRequest {
    private String hostingProvider;
    private String url1;
    private String url2;
    private String url3;
    private String duration;
    private String password;
    private String emailId;
    private String registrationPhoneNumber;
    private String registrationDomain;
    private String registrationDate; // Holds the date of domain registration
    private String expiryDate;
    private String userName;
    private String hostingDnsName;
    private String ns1;
    private String ns2;
    private Long daysLeft;
    private PasswordsEntity passwordsEntity;
}
