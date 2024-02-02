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
    private String domainProvider;
    private String userName;
    private String password;
    private String hostUserName;
    private String url1;
    private String hostPassword;

}