package com.pathbreaker.payslip.response;

import lombok.Data;

@Data
public class ResultResponse {

    private String result;

    private String role;

    private String userId;

    private String userName;

    private long id;
}
