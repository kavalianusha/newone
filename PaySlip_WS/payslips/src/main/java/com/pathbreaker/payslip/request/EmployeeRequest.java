package com.pathbreaker.payslip.request;

import com.pathbreaker.payslip.entity.EmployeeLogin;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeRequest {

    private String employeeType;

    private String employeeId;

    private String firstName;

    private String lastName;

    private String emailId;

    private String password;

    private String designation;

    private Date dateOfHiring;

    private String department;

    private String location;

    private String manager;

    private String role;

    private int status;

    private String ipAddress;

    private EmployeeLogin loginEntity;
}
