package com.pathbreaker.hostinghub.request;

// Import Lombok's Data annotation for generating boilerplate code
import lombok.Data;

// Use Lombok's Data annotation to generate getters, setters, equals, and hashCode
@Data
// Define the class for handling host domain map requests
public class HostDomainMapRequest {

        // Fields for admin request data
        private String hostDomainId;

        private String domainName;
        private String hostProvider;
        private String payment;
        private String domainProvider;
        private String userName;
        private String password;
        private String hostUserName;
        private String url1;
        private String hostPassword;



}
