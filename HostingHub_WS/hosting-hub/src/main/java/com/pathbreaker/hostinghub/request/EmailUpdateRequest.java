package com.pathbreaker.hostinghub.request;

import lombok.Data;

@Data
public class EmailUpdateRequest {
    private String email;     // Declaring a field for email
    private String username;  // Declaring a field for username
    private String password;  // Declaring a field for password
    private String domainName;
}
