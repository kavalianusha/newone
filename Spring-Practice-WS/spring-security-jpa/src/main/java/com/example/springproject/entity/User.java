package com.example.springproject.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


import lombok.Data;

@Entity
@Data
@Table(name="UserTable")
public class User {

	@Id
	private int userId;
	private String userName;
	private String password;
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="userRole", joinColumns = @JoinColumn(name="userId"),inverseJoinColumns = @JoinColumn(name="roleId"))
	private Set<Role> roles;
	
}
