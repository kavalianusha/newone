package com.example.efilingbazaar.request;

import java.sql.Date;

import lombok.Data;

@Data
public class LogRequest {
	
	
    private Long logId;

    private String employeeId;

    private String emailId;

    private Date sessionLastLoginTime;

    private Date sessionLastLogoutTime;

}
