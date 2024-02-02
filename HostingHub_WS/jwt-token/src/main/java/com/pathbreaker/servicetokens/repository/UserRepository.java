package com.pathbreaker.servicetokens.repository;

import com.pathbreaker.servicetokens.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    UserEntity findByUserName(String userName);
}
