package com.pathbreaker.payslip.serviceImpl;

import com.pathbreaker.payslip.entity.Department;
import com.pathbreaker.payslip.exception.Exceptions;
import com.pathbreaker.payslip.exception.NotFoundException;
import com.pathbreaker.payslip.mappers.DepartmentMapper;
import com.pathbreaker.payslip.repository.DepartmentRepository;
import com.pathbreaker.payslip.request.DepartmentRequest;
import com.pathbreaker.payslip.response.ResultResponse;
import com.pathbreaker.payslip.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {


    private DepartmentServiceImpl(DepartmentMapper departmentMapper,
                                  DepartmentRepository departmentRepository){
        this.departmentMapper  = departmentMapper;
        this.departmentRepository =departmentRepository;
    }
    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;
    @Override
    public ResponseEntity<?> createDepartment(DepartmentRequest departmentRequest) {

        try {
            Department departmentEntity = departmentMapper.entityToRequest(departmentRequest);
            departmentRepository.save(departmentEntity);

            ResultResponse result = new ResultResponse();
            log.info("Department added successfully...");
            result.setResult("Department added successfully...");
            return ResponseEntity.ok(result);
        }
        catch (Exceptions ex){
                log.warn("An error occured while adding department");
                throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while adding department");
        }

    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> departmentEntities = departmentRepository.findAll();
        return departmentEntities;
    }

    @Override
    public Optional<Department> getById(int id) {
        try {
        Optional<Department> departmentEntity= departmentRepository.findById(id);
        if (departmentEntity.isPresent()){
            return departmentEntity;
        }
        else {
            throw new NotFoundException(HttpStatus.NOT_FOUND, "The department with " + id + " not found");
        }
    } catch (Exceptions ex) {
        // Handle other exceptions
        log.error("An error occurred while retrieving department by ID: " + id, ex);
        throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while retrieving department by ID: " + id);
    }
 }

    @Override
    public ResponseEntity<?> updateById(int id, DepartmentRequest departmentRequest) {
        try {
            Optional<Department> departmentEntity = departmentRepository.findById(id);

            if (departmentEntity.isPresent()) {
                Department entity = departmentEntity.get();
                // Update the department title
                entity.setDepartmentTitle(departmentRequest.getDepartmentTitle());
                // Save the updated entity
                departmentRepository.save(entity);

                ResultResponse result = new ResultResponse();
                log.info("Department Updated successfully...");
                result.setResult("Department Updated successfully...");
                return ResponseEntity.ok(result);
            } else {
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The department with " + id + " not found");
            }
        }catch (Exceptions ex) {
            // Handle other exceptions
            log.error("An error occurred while updating department by ID: " + id, ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while updating department by ID: " + id);
        }

    }
    @Override
    public ResponseEntity<?> deleteById(int id) {
        try {
            Optional<Department> departmentEntity = departmentRepository.findById(id);
            if (departmentEntity.isPresent()) {
                Department entity = departmentEntity.get();
                departmentRepository.delete(entity);

                ResultResponse result = new ResultResponse();
                log.info("Department deleted successfully...");
                result.setResult("Department deleted successfully...");
                return ResponseEntity.ok(result);
            } else {
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The department with " + id + " not found");
            }
        } catch (Exceptions ex) {
            // Handle other exceptions
            log.error("An error occurred while Deleting department by ID: " + id, ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while deleting department by ID: " + id);
        }
    }
}
