package com.pathbreaker.payslip.serviceImpl;

import com.pathbreaker.payslip.repository.EmployeeRepository;
import com.pathbreaker.payslip.repository.RelievingRepository;
import com.pathbreaker.payslip.service.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {


    private DashboardServiceImpl(EmployeeRepository employeeRepository,
                                 RelievingRepository relievingRepository){
        this.employeeRepository =employeeRepository;
        this.relievingRepository = relievingRepository;
    }
    private final EmployeeRepository employeeRepository;

    private final RelievingRepository relievingRepository;

    @Override
    public long getEmployees() {
        return employeeRepository.count();
    }

    @Override
    public long getRelieving() {
        return relievingRepository.count();
    }

    @Override
    public long getAllEmployees() {
        return employeeRepository.count()+relievingRepository.count();
    }
}
