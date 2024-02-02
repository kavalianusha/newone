package com.pathbreaker.pmp.repository;

import com.pathbreaker.pmp.entity.RevenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RevenueRepository extends JpaRepository<RevenueEntity, String> {
    Optional<RevenueEntity> findByProjectName(String projectName);
}
