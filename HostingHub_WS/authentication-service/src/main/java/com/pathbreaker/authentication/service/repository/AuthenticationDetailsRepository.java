package com.pathbreaker.authentication.service.repository;

import com.pathbreaker.authentication.service.entity.TokenDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationDetailsRepository extends JpaRepository<TokenDetails, Long>{

}
