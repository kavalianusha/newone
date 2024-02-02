package com.example.efilingbazaar.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name="SuperadminTable")
public class Admin {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String emailId;
        private String password;
        private String otp;
        @Column(name = "LoginTime", columnDefinition = "Timestamp")
        @Temporal(TemporalType.TIMESTAMP)
        private Timestamp loginTime;

        @Column(name = "LogoutTime", columnDefinition = "Timestamp")
        @Temporal(TemporalType.TIMESTAMP)
        private Timestamp logoutTime;

        public void setLoggedIn(boolean b) {
        }
        public void setLoginTime() {
                this.loginTime = new Timestamp(System.currentTimeMillis());
        }

        public Timestamp getLogoutTime() {
                return logoutTime;
        }

}