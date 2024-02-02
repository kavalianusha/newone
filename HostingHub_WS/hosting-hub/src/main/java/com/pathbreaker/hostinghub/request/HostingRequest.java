package com.pathbreaker.hostinghub.request;

// Import Lombok's Data annotation for generating boilerplate code
import com.pathbreaker.hostinghub.entity.PasswordsEntity;
import lombok.Data;

// Use Lombok's Data annotation to generate getters, setters, equals, and hashCode
@Data
// Define the class for handling host domain map requests
public class HostingRequest {

    // Fields for admin request data
    private String hostingId;
    private String hostingProvider;
    private String url1;
    private String url2;
    private String url3;
    private String duration;
    private String password;
    private String emailId;
    private String registrationPhoneNumber;
    private String registrationDomain;
    private String registrationDate; // Holds the date of domain registration
    private String expiryDate;
    private String userName;
    private String hostingDnsName;
    private String ns1;
    private String ns2;
    private Long daysLeft;
    private PasswordsEntity passwordsEntity;

}
