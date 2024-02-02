package com.example.efilingbazaar.repository;

import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.efilingbazaar.entity.SubServiceEntity;

@Repository
public interface SubServiceRepository extends JpaRepository<SubServiceEntity, String> {


	 @Query("SELECT MAX(ss.subServiceId) FROM SubServiceEntity ss")
	 String findHighestSubServiceId();

	Optional<SubServiceEntity> findBySubServiceId(String subServiceId);
}
