package com.pathbreaker.payslip.request;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeLoginRequest {

    private Integer id;

    private Long otp;

    private String emailId;

    private String password;

    private int status;

    private Date lastLoginTime;

    private String role;

    private String ipAddress;

    private EmployeeRequest employeeRequest;
}
