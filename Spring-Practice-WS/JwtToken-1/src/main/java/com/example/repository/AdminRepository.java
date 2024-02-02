package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.AdminEntity;
import com.example.request.AdminRequest;

public interface AdminRepository extends JpaRepository<AdminEntity, String> {

	void save(AdminRequest request);

}
