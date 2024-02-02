package com.pathbreaker.hostinghub.request;

import lombok.Data;

@Data
public class PasswordRequest {

    public String passwordId;
    public String userName;
    public String password;
    public String registrationDate;
    public String expiryDate;
    private Long daysLeft;
    private String updateDate;

}
