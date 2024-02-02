package com.example.efilingbazaar.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.efilingbazaar.entity.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, String>{

	LoginEntity findByEmailIdAndPassword(String emailId, String password);

	LoginEntity findByEmailId(String emailId);
	@Modifying
	@Query("UPDATE LoginEntity l SET l.password = :password WHERE l.emailId = :emailId")
	int updatePassword(String emailId, String password);

}