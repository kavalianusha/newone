package com.pathbreaker.payslip.response;

import lombok.Data;

@Data
public class PaySlipsResponse {


    private Long id;
    private String employeeId;

    private String firstName;

    private String lastName;

    private String month;

    private Long financialYear;

    private String filePaths;
}
