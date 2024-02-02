package com.pathbreaker.servicetokens.pojo;

import lombok.Data;

@Data
public class ResultResponse {

    public String jwtToken;

    public String result;

    public String refreshToken;
}
