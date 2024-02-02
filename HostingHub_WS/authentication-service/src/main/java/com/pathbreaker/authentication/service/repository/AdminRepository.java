// Import necessary classes and annotations
package com.pathbreaker.authentication.service.repository;

import com.pathbreaker.authentication.service.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Define this interface as a Spring Repository
@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, String> {


   AdminEntity findByUserName(String userName);


    AdminEntity findByOtp(String otp);
}
