package com.pathbreaker.payslip.repository;

import com.pathbreaker.payslip.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    UserLogin findByEmailIdOrUserName(String emailId, String userName);

    UserLogin findByOtp(String otp);
}
