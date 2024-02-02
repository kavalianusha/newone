package com.pathbreaker.authentication.service.response;

import lombok.Data;

@Data
public class ResultResponse {

    private String result;

    private String accessToken;

    private String refreshToken;

    private String role;
}
