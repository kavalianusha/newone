package com.pathbreaker.payslip.request;

import lombok.Data;

@Data
public class StatusRequest {

    private int status;

    private String statusInfo;
}
