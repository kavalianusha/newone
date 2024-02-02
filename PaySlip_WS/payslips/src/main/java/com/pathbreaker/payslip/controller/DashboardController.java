package com.pathbreaker.payslip.controller;

import com.pathbreaker.payslip.service.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins="*")
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;


    @GetMapping("/employees")
    public long getEmployees() {
        return dashboardService.getEmployees();
    }

    // Handle GET request to retrieve all hosting providers
    @GetMapping("/relieved")
    public long getAllRelieving() {
        return dashboardService.getRelieving();
    }

    @GetMapping("/all/employees")
    public long getAllEmployees() {
        return dashboardService.getAllEmployees();
    }


}
