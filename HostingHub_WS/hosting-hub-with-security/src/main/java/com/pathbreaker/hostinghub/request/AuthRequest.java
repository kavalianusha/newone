package com.pathbreaker.hostinghub.request;

import lombok.Data;

@Data
public class AuthRequest {

    private String userName;

    private String password;
}
