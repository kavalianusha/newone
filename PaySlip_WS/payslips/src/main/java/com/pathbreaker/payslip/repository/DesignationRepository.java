package com.pathbreaker.payslip.repository;

import com.pathbreaker.payslip.entity.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Integer> {
}
