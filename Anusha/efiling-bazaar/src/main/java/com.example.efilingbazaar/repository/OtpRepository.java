package com.example.efilingbazaar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.efilingbazaar.entity.OtpEntity;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, String>{
	
	 OtpEntity findByEmailId(String emailId);

     OtpEntity findByEmailIdAndOtp(String emailId, String otp);

}
