package com.pathbreaker.hostinghub.repository;

import com.pathbreaker.hostinghub.entity.PasswordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PasswordRepository extends JpaRepository<PasswordsEntity, String> {


    @Query("SELECT MAX(p.passwordId) FROM PasswordsEntity p")
    String findHighestPasswordId();
}
