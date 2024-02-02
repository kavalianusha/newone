package com.example.hostinghub.request;

// Import Lombok's Data annotation for generating boilerplate code
import lombok.Data;

// Use Lombok's Data annotation to generate getters, setters, equals, and hashCode
@Data
// Define the class for handling host domain map requests
public class HostDomainMapUpdateRequest {

    // Fields for admin request data
    private String domainName;

    private String hostProvider;

    private String payment;

    private String registrationDate;

    private String duration;

    private String expiryDate;

    private Long daysLeft;

}
