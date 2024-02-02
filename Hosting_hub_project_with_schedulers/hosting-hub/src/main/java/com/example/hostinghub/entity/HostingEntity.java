// Import necessary annotations and classes for JPA entity
package com.example.hostinghub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
// Import Lombok's Data annotation for generating boilerplate code
import lombok.Data;

// Define this class as an entity
@Entity
// Generate boilerplate code for getters, setters, equals, and hashCode
@Data
// Define the name of the table in the database
@Table(name="HostingTable")
public class HostingEntity {

    // Define the field as the primary key
    @Id
    private String hostingId;
    // Define other fields
    private String hostingProvider;
    private String url;
    private String login;
    private String password;
    private String emailId;
    private String registrationPhoneNumber;
    private String registrationDomain;
    private String registrationDate; // Holds the date of domain registration
    private String expiryDate;
    private String hostingCpannelUrl;
    private String userName;
    private String hostingDnsName;
    private Long daysLeft;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passwordId", referencedColumnName = "passwordId")
    private PasswordsEntity passwordsEntity;

}
