package com.example.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springproject.entity.Audit;

public interface AuditRepository extends JpaRepository<Audit,long>{

}
