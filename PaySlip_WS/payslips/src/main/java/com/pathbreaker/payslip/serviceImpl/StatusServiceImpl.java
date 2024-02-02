package com.pathbreaker.payslip.serviceImpl;

import com.pathbreaker.payslip.entity.Status;
import com.pathbreaker.payslip.exception.Exceptions;
import com.pathbreaker.payslip.exception.NotFoundException;
import com.pathbreaker.payslip.mappers.StatusMapper;
import com.pathbreaker.payslip.repository.StatusRepository;
import com.pathbreaker.payslip.request.StatusRequest;
import com.pathbreaker.payslip.response.ResultResponse;
import com.pathbreaker.payslip.service.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusServiceImpl(StatusMapper statusMapper,
                              StatusRepository statusRepository){
        this.statusMapper  = statusMapper;
        this.statusRepository =statusRepository;
    }
    private final StatusMapper statusMapper;
    private final StatusRepository statusRepository;
    @Override
    public ResponseEntity<?> createStatus(StatusRequest statusRequest) {

        try {
            Status statusEntity = statusMapper.entityToRequest(statusRequest);
            statusRepository.save(statusEntity);

            ResultResponse result = new ResultResponse();
            log.info("Status added successfully...");
            result.setResult("Status added successfully...");
            return ResponseEntity.ok(result);
        }
        catch (Exceptions ex){
            log.warn("An error occured while adding Status");
            throw new NotFoundException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while adding Status");
        }

    }

    @Override
    public List<Status> getAllStatus() {
        List<Status> statusEntities = statusRepository.findAll();
        return statusEntities;
    }

    @Override
    public Optional<Status> getById(int id) {
        try {
            Optional<Status> statusEntity= statusRepository.findById(id);
            if (statusEntity.isPresent()){
                return statusEntity;
            }
            else {
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The Status with " + id + " not found");
            }
        } catch (Exceptions ex) {
            // Handle other exceptions
            log.error("An error occurred while retrieving Status by ID: " + id, ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while retrieving Status by ID: " + id);
        }
    }

    @Override
    public ResponseEntity<?> updateById(int id, StatusRequest statusRequest) {
        try {
            Optional<Status> statusEntity = statusRepository.findById(id);

            if (statusEntity.isPresent()) {
                Status entity = statusEntity.get();
                // Update the department title
                entity.setStatus(statusRequest.getStatus());
                entity.setStatusInfo(statusRequest.getStatusInfo());
                // Save the updated entity
                statusRepository.save(entity);

                ResultResponse result = new ResultResponse();
                log.info("Status Updated successfully...");
                result.setResult("Status Updated successfully...");
                return ResponseEntity.ok(result);
            } else {
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The Status with " + id + " not found");
            }
        }catch (Exceptions ex) {
            // Handle other exceptions
            log.error("An error occurred while updating Status by ID: " + id, ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while updating Status by ID: " + id);
        }

    }
    @Override
    public ResponseEntity<?> deleteById(int id) {
        try {
            Optional<Status> statusEntity = statusRepository.findById(id);
            if (statusEntity.isPresent()) {
                Status entity = statusEntity.get();
                statusRepository.delete(entity);

                ResultResponse result = new ResultResponse();
                log.info("Status deleted successfully...");
                result.setResult("Status deleted successfully...");
                return ResponseEntity.ok(result);
            } else {
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The Status with " + id + " not found");
            }
        } catch (Exceptions ex) {
            // Handle other exceptions
            log.error("An error occurred while Deleting Status by ID: " + id, ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while deleting Status    by ID: " + id);
        }
    }
}
