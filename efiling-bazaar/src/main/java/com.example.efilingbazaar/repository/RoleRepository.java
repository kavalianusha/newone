package com.example.efilingbazaar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.efilingbazaar.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	
	RoleEntity findByRoleName(String roleName);

	RoleEntity findByRoleId(Long roleId);

}
