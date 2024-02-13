package com.pathbreaker.accesstoken.repository;

import com.pathbreaker.accesstoken.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long > {
    Admin findByEmailIdOrUserName(String emailId, String userName);

    Admin findByUserName(String userName);
}
