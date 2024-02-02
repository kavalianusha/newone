package com.pathbreaker.hostinghub.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@Entity
@Table(name = "HostDomainMapTable")
@AllArgsConstructor
@NoArgsConstructor
public class HostDomainMapEntity {

    @Id
    private String hostDomainId;

    private String domainName;
    private String hostProvider;
    private String payment;
    private String registrationDate;
    private String duration;
    private String expiryDate;
    private long daysLeft;


}