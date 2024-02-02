package com.example.springproject.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="RoleTable")
public class Role {
	
	@Id
	@GeneratedValue
	private int roleId;
	private String roleName;
	

}
