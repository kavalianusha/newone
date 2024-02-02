package com.example.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springproject.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String username);
}
