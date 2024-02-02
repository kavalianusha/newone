package com.example.efilingbazaar.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    public String emailId;
    public String password;
    public String otp;
}
