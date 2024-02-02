package com.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
public class AdminEntity {
	
	
	@Id
	public String userName;
	public String email;
	public String password;
	public String role;
	
}
