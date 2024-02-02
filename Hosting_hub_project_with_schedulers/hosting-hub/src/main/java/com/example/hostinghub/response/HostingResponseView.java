package com.example.hostinghub.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class HostingResponseView {

    private String hostingId;
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

}
