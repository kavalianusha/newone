package com.pathbreaker.payslip.serviceImpl;

import com.pathbreaker.payslip.entity.*;
import com.pathbreaker.payslip.exception.Exceptions;
import com.pathbreaker.payslip.exception.NotFoundException;
import com.pathbreaker.payslip.mappers.EmployeeLoginMappers;
import com.pathbreaker.payslip.mappers.EmployeeMapper;
import com.pathbreaker.payslip.repository.*;
import com.pathbreaker.payslip.request.EmployeeRequest;
import com.pathbreaker.payslip.request.EmployeeUpdateRequest;
import com.pathbreaker.payslip.response.EmployeeResponse;
import com.pathbreaker.payslip.response.ResultResponse;
import com.pathbreaker.payslip.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeMapper employeeMapper,
                               EmployeeLoginMappers loginMappers,
                               EmployeeLoginRepository loginRepository){
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.loginMappers = loginMappers;
        this.loginRepository = loginRepository;
    }
    private final EmployeeRepository employeeRepository;

    private final EmployeeLoginRepository loginRepository;

    private final EmployeeMapper employeeMapper;

    private final EmployeeLoginMappers loginMappers;
    @Override
    public ResponseEntity<?> createEmployee(EmployeeRequest employeeRequest) {
        try {
            Employee employeeEntity = employeeMapper.entityToRequest(employeeRequest);
            EmployeeLogin loginEntity = loginMappers.entityToRequest(employeeRequest);
            employeeEntity.setLoginEntity(loginEntity);
            loginEntity.setEmployeeEntity(employeeEntity);

            employeeRepository.save(employeeEntity);

            ResultResponse result = new ResultResponse();
            log.info("Employee Registration is successfull " + employeeRequest.getEmployeeId());
            result.setResult("Employee Registration is successfull " + employeeRequest.getEmployeeId());

            return ResponseEntity.ok(result);
        } catch (Exceptions ex) {
            String message = "An error occured during EMPLOYEE registration " + ex;
            log.info("An error occured during EMPLOYEE registration " + ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, message);
        }
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employeeEntities = employeeRepository.findAll();

        List<EmployeeResponse> employeeResponses = employeeEntities.stream()
                .map(employeeMapper::responseListToEntity)
                .collect(Collectors.toList());
        log.info("The retrieved employee details are "+employeeResponses.size());

        return employeeResponses;
    }

    @Override
    public EmployeeResponse getEmployeeById(String employeeId) {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findByEmployeeId(employeeId);
            if (employeeOptional.isPresent()) {

                Employee employeeEntity = employeeOptional.get();
                EmployeeResponse response = employeeMapper.responseListToEntity(employeeEntity);

                log.info("Retrieving the employee details of {}: " + employeeId);
                return response;
            } else {
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The Employee with " + employeeId + " not found");
            }
        } catch (Exceptions ex) {
            // Handle other exceptions
            log.error("An error occurred while retrieving employee by ID: " + employeeId, ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while retrieving employee by ID: " + employeeId);
        }
    }

    @Override
    public ResponseEntity<?> updateEmployeeById(String employeeId, EmployeeUpdateRequest employeeUpdateRequest) {
        try {
            Optional<Employee> employeeEntityOptional = employeeRepository.findByEmployeeId(employeeId);

            if (employeeEntityOptional.isPresent()) {
                Employee employeeEntity = employeeEntityOptional.get();

                // Update the existing resource with the new data from the request
                Employee employee = employeeMapper.updateEntityFromRequest(employeeUpdateRequest, employeeEntity);
                // Update the resource skills entity as well, assuming it is a separate entity
                EmployeeLogin loginEntity = loginMappers.updateEntityFromRequest(employeeUpdateRequest, employeeEntity.getLoginEntity());

                employee.setLoginEntity(loginEntity);
                // Save the updated resource to the database
                employeeRepository.save(employee);
               // loginRepository.save(loginEntity);

                ResultResponse result = new ResultResponse();
                log.info("EMPLOYEE update is successful for employeeId: " + employeeId);
                result.setResult("EMPLOYEE update is successful for employeeId : "+employeeId);

                return ResponseEntity.ok(result);
            } else {
                log.warn("The employee not found with " + employeeId);
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The employee with " + employeeId + " not found");
            }
        } catch (Exceptions ex) {
            log.warn("An error occured while updating the employee " + employeeId);
            throw new NotFoundException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while updating the employee " + employeeId);
        }
    }
    @Override
    public ResponseEntity<?> deleteEmployeeById(String employeeId) {
        try {
            Optional<Employee> existingEmployeeOptional = employeeRepository.findByEmployeeId(employeeId);

            if (existingEmployeeOptional.isPresent()) {
                Employee employee = existingEmployeeOptional.get();
                // Delete associated resource skills
                if (employee.getLoginEntity() != null) {
                    loginRepository.delete(employee.getLoginEntity());
                }
                // Delete the resource
                employeeRepository.delete(employee);

                ResultResponse result = new ResultResponse();
                log.info("EMPLOYEE deletion is successful for employeeId: " + employeeId);
                result.setResult("EMPLOYEE deletion is successful.....");

                return ResponseEntity.ok(result);
            } else {
                log.warn("The employee not found with "+employeeId);
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The employee with " + employeeId + " not found");
            }
        } catch (Exceptions ex) {
            log.warn("An error occured while deleting the employee "+employeeId);
            throw new NotFoundException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while deleting the employee "+employeeId);
        }
    }


}
