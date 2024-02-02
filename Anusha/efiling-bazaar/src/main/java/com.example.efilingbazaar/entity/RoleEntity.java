package com.example.efilingbazaar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="RoleTable")
public class RoleEntity {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long roleId;
	    private String roleName;
	    
	    public RoleEntity() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "RoleEntity [roleId=" + roleId + ", roleName=" + roleName + "]";
		}
	    

}
