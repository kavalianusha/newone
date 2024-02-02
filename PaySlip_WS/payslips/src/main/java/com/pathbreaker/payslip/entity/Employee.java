package com.pathbreaker.payslip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    private String employeeId;

    private String employeeType;

    private String firstName;

    private String lastName;

    private String emailId;

    private String password;

    private String designation;

    @Column(columnDefinition = "TIMESTAMP")
    private Date dateOfHiring;

    private String department;

    private String location;

    private String manager;

    private String role;

    private int status;

    private String ipAddress;

    @OneToOne(mappedBy = "employeeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeLogin loginEntity;



}
