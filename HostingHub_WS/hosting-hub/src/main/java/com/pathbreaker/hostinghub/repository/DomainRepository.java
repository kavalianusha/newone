package com.pathbreaker.hostinghub.repository;

import com.pathbreaker.hostinghub.entity.DomainEntity;
import com.pathbreaker.hostinghub.response.DomainResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository  // Marks this interface as a Spring repository
public interface DomainRepository extends JpaRepository<DomainEntity, String> {

    // Define a custom query using the Query annotation.
    // This query finds the maximum domainId in the DomainEntity table.
    @Query("SELECT MAX(d.domainId) FROM DomainEntity d")
    String findHighestDomainId();  // Method to execute the custom query.

    // Method to find a domain entity by its domainId.

    DomainEntity findByDomainId(String domainId);



    // Method to delete a domain entity by its domainId.
    void deleteByDomainId(String domainId);

    @Query("SELECT d.domainName FROM DomainEntity d")
    List<String> findAllDomainNames();

    @Query("SELECT d FROM DomainEntity d WHERE d.expiryDate = '2024-01-09'")
    List<DomainEntity> findDomainsExpiringToday();

    @Query("SELECT dp.providerName FROM DomainEntity dp")
    List<String> findAllDomainProviders();

    @Query("SELECT d FROM DomainEntity d WHERE d.domainName = :domainName")
    DomainEntity findDomainDetails(String domainName);

}