package com.pathbreaker.hostinghub.request;

import lombok.Data;

@Data
public class PasswordUpdateRequest {

    private String passwordId;
    private String userName;
    private String password;
    private String registrationDate;
    private String expiryDate;
    private Long daysLeft;
    private String updateDate;

}
