package com.pathbreaker.payslip.service;

import org.springframework.http.ResponseEntity;

public interface DashboardService {
    long getEmployees();

    long getRelieving();

    long getAllEmployees();
}
