package com.pathbreaker.hostinghub.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
