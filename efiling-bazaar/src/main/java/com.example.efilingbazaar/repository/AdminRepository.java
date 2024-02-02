package com.example.efilingbazaar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.efilingbazaar.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
        Admin findByEmailId(String emailId);

        Admin findByEmailIdAndOtp(String emailId, String otp);
}
