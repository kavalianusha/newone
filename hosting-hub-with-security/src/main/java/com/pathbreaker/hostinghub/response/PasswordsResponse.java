package com.pathbreaker.hostinghub.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PasswordsResponse {

    public String passwordId;
    public String userName;
    public String password;
    public String registrationDate;
    public String expiryDate;
    public Long daysLeft;
    private String updateDate;

}
