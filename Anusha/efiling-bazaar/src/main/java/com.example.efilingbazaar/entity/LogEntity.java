
package com.example.efilingbazaar.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="log_id")
    private Long logId;
    @Column(name="employee_id")
    private String employeeId;
    @Column(name="email_id")
    private String emailId;
    @Column(name="session_last_login_time", columnDefinition = "TIMESTAMP")
    private Timestamp sessionLastLoginTime;
    @Column(name="session_last_logout_time", columnDefinition = "TIMESTAMP")
    private Timestamp sessionLastLogoutTime;


    public void setSessionLastLoginTime(Timestamp sessionLastLoginTime) {
        this.sessionLastLoginTime = sessionLastLoginTime;
    }

    public Timestamp getSessionLastLogoutTime() {
        return sessionLastLogoutTime;
    }

    public void setSessionLastLogoutTime(Timestamp sessionLastLogoutTime) {
        this.sessionLastLogoutTime = sessionLastLogoutTime;
    }

    @Override
    public String toString() {
        return "Log [logId=" + logId + ", employeeId=" + employeeId + ",emailId="+emailId+", sessionLastLoginTime=" + sessionLastLoginTime
                + ", sessionLastLogoutTime=" + sessionLastLogoutTime + "]";
    }

    public LogEntity() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setSessionLastLoginTime(LocalDateTime loginTime) {
        // TODO Auto-generated method stub

    }

    public void setSessionLastLogoutTime(LocalDateTime logoutTime) {
        // TODO Auto-generated method stub

    }

    public void setAction(String action) {
        // TODO Auto-generated method stub

    }

    public void setTimestamp(LocalDateTime now) {
        // TODO Auto-generated method stub

    }

    public void setSessionLastLoginTime(Object sessionLastLoginTime2) {
        // TODO Auto-generated method stub

    }

    public void setSessionLastLogoutTime(Object sessionLastLogoutTime2) {
        // TODO Auto-generated method stub

    }

    public Object getId() {
        // TODO Auto-generated method stub
        return null;
    }



}