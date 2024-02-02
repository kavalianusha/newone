package com.pathbreaker.hostinghub.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "itreturns")
@Data
public class ItReturnsEntity {
    @Id
    private String customerId;

    private String serviceType;
    private String emailId;
    private String registeredMobileNo;
    private String registrationDate; // Holds the date of domain registration
    private String expiryDate;
    private String loginUrl;
    private String userName;
    private String password;
    private String createdBy;
    private String createdDate;
    private Long daysLeft;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passwordId", referencedColumnName = "passwordId")
    private PasswordsEntity passwordsEntity;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
