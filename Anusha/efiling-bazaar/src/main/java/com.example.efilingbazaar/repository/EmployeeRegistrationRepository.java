package com.example.efilingbazaar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.efilingbazaar.entity.EmployeeRegistrationEntity;
import com.example.efilingbazaar.request.EmployeeRegistrationRequest;

@Repository
public interface EmployeeRegistrationRepository extends JpaRepository<EmployeeRegistrationEntity, String> {

    boolean existsByIdProof(String idProof);

    boolean existsByEmailId(String emailId);

    @Query(value = "SELECT MAX(employee_id) FROM employee_registration_entity", nativeQuery = true)
    String findHighestemployeeId();

	EmployeeRegistrationEntity findByEmployeeId(String employeeId);

	EmployeeRegistrationEntity save(EmployeeRegistrationRequest registerRequest);

    // Add custom queries if needed
}