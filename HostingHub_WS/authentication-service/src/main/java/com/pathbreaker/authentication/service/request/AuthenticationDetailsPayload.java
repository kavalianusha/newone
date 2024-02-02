package com.pathbreaker.authentication.service.request;

import lombok.Data;

@Data
public class AuthenticationDetailsPayload {

        private String userName;
        private String password;
        private String clientId;
        private String otp;

}
