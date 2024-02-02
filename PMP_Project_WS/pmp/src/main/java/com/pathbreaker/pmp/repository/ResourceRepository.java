package com.pathbreaker.pmp.repository;

import com.pathbreaker.pmp.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ResourceRepository extends JpaRepository<ResourceEntity, String> {
     @Query("SELECT MAX(e.employeeId) FROM ResourceEntity e")
     String findHighestEmpId();

    Optional<ResourceEntity> findByEmployeeId(String employeeId);
}
