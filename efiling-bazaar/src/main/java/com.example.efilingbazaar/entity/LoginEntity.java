
package com.example.efilingbazaar.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Login")
public class LoginEntity {

    @Id
    @Column(name="employee_id")
    private String employeeId;
    @Column(name="email_id")
    private String emailId;
    @Column(name="last_login_time", columnDefinition = "TIMESTAMP")
    private Timestamp lastLoginTime;
    @Column(name="last_logout_time", columnDefinition = "TIMESTAMP")
    private Timestamp lastLogoutTime;
    @Column(name="password")
    private String password;
    @Column(name="role_name")
    private String roleName;
    @Column(name="status")
    private boolean status;

    // Constructors, getters, and setters...
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime() {
        this.lastLoginTime = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getLastLogoutTime() {
        return lastLogoutTime;
    }

    public void setLastLogoutTime() {
        this.lastLogoutTime = new Timestamp(System.currentTimeMillis());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }





    @Override
    public String toString() {
        return "Login [employeeId=" + employeeId + ", emailId=" + emailId + ", lastLoginTime=" + lastLoginTime
                + ", lastLogoutTime=" + lastLogoutTime + ", password=" + password + ", roleName=" + roleName
                + ", status=" + status + "]";
    }

    public LoginEntity() {
        super();
    }

    public Object getLoginEmployeeId() {
        // TODO Auto-generated method stub
        return this.employeeId;
    }

    public void setLoginLogId(Object id) {
        // TODO Auto-generated method stub

    }
}