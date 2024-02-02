package com.example.efilingbazaar.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "cust_register")
public class CustomerEntity {

    @Id
    private String customerId;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile_no;
    private String pan_no;
    private String password;
    private String otp;
    private long otpTime;
    private LocalDate createdDate;
    @Column(name="last_login_in", columnDefinition ="TIMESTAMP")
    private Timestamp lastLoginTime;
    @Column(name="last_login_out", columnDefinition ="TIMESTAMP")
    private Timestamp lastLogoutTime;

    @ElementCollection
    @CollectionTable(name = "customerFilePaths", joinColumns = @JoinColumn(name = "customerId"))
    @Column(name = "customerFilePaths")
    private List<String> customerFilePaths;
}
