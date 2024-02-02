package com.example.efilingbazaar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="employee_registration_entity")
public class EmployeeRegistrationEntity {
   
	@Id
    private String employeeId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String idProof;
    private String contactNumber;
    private String emailId;
    private String password;
    private String qualification;
    private String skillset;
    private String roleName;
    private boolean status;

	@Override
	public String toString() {
		return "Registration [employeeId=" + employeeId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", address=" + address + ", idProof=" + idProof + ", contactNumber="
				+ contactNumber + ", emailId=" + emailId + ", status="+status+",roleName="+roleName+", password=" + password + ", qualification=" + qualification
				+ ", skillset=" + skillset + "]";
	}
	public EmployeeRegistrationEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
    
    
    
}