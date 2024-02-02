package com.example.springproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="usertable")
public class User {
	
	@Id
	private String userName;
	private String password;
	
	public User() {
	}
	
	public User(String userName, String password) {
	    this.userName = userName;
	    this.password = password;
	}

}
