package com.example.hostinghub.response;

import lombok.Data;

@Data
public class PasswordsResponseView {

    public String passwordId;
    public String userName;
    public String password;
    public String registrationDate;
    public String expiryDate;
    public Long daysLeft;
    private String updateDate;

    public DomainResponseView domainResponseView;
    public HostingResponseView hostingResponseView;
    public ItReturnsResponseView itReturnsResponseView;
}
