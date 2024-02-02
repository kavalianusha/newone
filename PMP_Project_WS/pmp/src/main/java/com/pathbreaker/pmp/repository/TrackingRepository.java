package com.pathbreaker.pmp.repository;

import com.pathbreaker.pmp.entity.TrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRepository extends JpaRepository<TrackingEntity, String> {

    // Custom query to find the highest cnpId
    @Query("SELECT MAX(t.cnpId) FROM TrackingEntity t")
    String findHighestCnpId();

    // Rename the custom findById method to avoid clashes
    TrackingEntity findByCnpId(String cnpId);
}