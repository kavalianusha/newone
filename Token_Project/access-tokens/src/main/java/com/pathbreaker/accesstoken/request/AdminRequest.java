package com.pathbreaker.accesstoken.request;


import lombok.Data;

@Data
public class AdminRequest {

    private String userName;
    private String password;
    private String emailId;
    private Long otp;
    private String countryCode;
    private String phoneNo;
}
