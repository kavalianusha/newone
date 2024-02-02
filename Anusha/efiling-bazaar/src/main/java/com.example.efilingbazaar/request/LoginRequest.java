package com.example.efilingbazaar.request;

import java.sql.Date;

import lombok.Data;

@Data
public class LoginRequest {

	
	    private String employeeId;
	    
	    private String emailId;
	    
	    private Date lastLoginTime;
	    
	    private Date lastLogoutTime;
	    
	    private String password;
	    
	    private String roleName;
	    
	    private boolean status;
	    
	    private String otp;
}
