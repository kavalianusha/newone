package com.example.hostinghub.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name="PasswordsTable")
@AllArgsConstructor
@NoArgsConstructor
public class PasswordsEntity {

    @Id
    private String passwordId;
    private String userName;
    private String password;
    private String registrationDate;
    private String expiryDate;
    private Long daysLeft;
    private String updateDate;

    @OneToOne(mappedBy = "passwordsEntity",cascade = CascadeType.ALL,orphanRemoval = false)
    @JsonIgnore
    private DomainEntity domainEntity;

    @OneToOne(mappedBy = "passwordsEntity",cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private HostingEntity hostings;

    @OneToOne(mappedBy = "passwordsEntity",cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private ItReturnsEntity itReturns;


}
