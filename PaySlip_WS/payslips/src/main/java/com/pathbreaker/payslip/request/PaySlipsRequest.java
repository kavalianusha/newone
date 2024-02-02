package com.pathbreaker.payslip.request;

import lombok.Data;

@Data
public class PaySlipsRequest {


    private String employeeId;

    private String month;

    private Long financialYear;

    private String filePaths;
}
