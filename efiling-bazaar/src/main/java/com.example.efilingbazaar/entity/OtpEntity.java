package com.example.efilingbazaar.entity;



import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="OtpTable")
public class OtpEntity {

    @Id
    private String emailId;

    private String otp;

    @Override
    public String toString() {
        return "OTP [emailId=" + emailId + ", otp=" + otp + "]";
    }

    public OtpEntity() {
        super();
    }

	public Date getGeneratedTime() {
		// TODO Auto-generated method stub
		return null;
	}

	public Date getCreatedAt() {
		// TODO Auto-generated method stub
		return null;
	}
}