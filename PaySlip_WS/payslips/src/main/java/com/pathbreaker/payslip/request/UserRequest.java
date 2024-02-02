package com.pathbreaker.payslip.request;

import com.pathbreaker.payslip.entity.UserLogin;
import lombok.Data;

import java.util.Date;

@Data
public class UserRequest {

    private String userId;

    private String emailId;

    private String userName;

    private String password;

    private String role;

    private int status;

    private Date registrationDate;

    private UserLogin userLoginEntity;
}