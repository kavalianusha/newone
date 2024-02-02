package com.pathbreaker.hostinghub.response;

// Import Lombok's Data annotation for generating boilerplate code
import lombok.Data;

// Use Lombok's Data annotation to generate getters, setters, equals, and hashCode
@Data
// Define the class for handling map requests
public class HostingResponse {

        private String hostingId;
        // Define the class for handling requests
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
        private PasswordsResponse passwordsResponse;

    }


