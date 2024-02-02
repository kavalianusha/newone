package com.example.efilingbazaar.repository;

import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.efilingbazaar.entity.MainServiceEntity;

@Repository
public interface MainServiceRepository extends JpaRepository<MainServiceEntity, String>{


	@Query("SELECT MAX(m.mainServiceId) FROM MainServiceEntity m")
    String findHighestMainServiceId();
    
    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM MainServiceEntity m WHERE m.mainServiceName = :mainServiceName")
    boolean existsByMainServiceName(@Param("mainServiceName") String mainServiceName);
	 
    Optional<MainServiceEntity> findByMainServiceId(String mainServiceId);
	    

}