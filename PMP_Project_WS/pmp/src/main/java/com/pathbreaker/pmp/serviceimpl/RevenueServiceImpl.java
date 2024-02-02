package com.pathbreaker.pmp.serviceimpl;

import com.pathbreaker.pmp.entity.*;
import com.pathbreaker.pmp.exception.Exceptions;
import com.pathbreaker.pmp.exception.NotFoundException;
import com.pathbreaker.pmp.mappers.ResourceMapper;
import com.pathbreaker.pmp.mappers.ResourceSkillsMapper;
import com.pathbreaker.pmp.mappers.RevenueMapper;
import com.pathbreaker.pmp.repository.ResourceRepository;
import com.pathbreaker.pmp.repository.ResourceSkillRepository;
import com.pathbreaker.pmp.repository.RevenueRepository;
import com.pathbreaker.pmp.request.RevenueRequest;
import com.pathbreaker.pmp.request.RevenueUpdateRequest;
import com.pathbreaker.pmp.response.ResourceResponse;
import com.pathbreaker.pmp.response.ResultResponse;
import com.pathbreaker.pmp.response.RevenueResponse;
import com.pathbreaker.pmp.service.RevenueService;
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
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    public RevenueServiceImpl(RevenueRepository revenueRepository,
                              RevenueMapper revenueMapper){
        this.revenueRepository = revenueRepository;
        this.revenueMapper = revenueMapper;
    }
    private RevenueRepository revenueRepository;
    private RevenueMapper revenueMapper;


    @Override
    public ResponseEntity<?> createRevenue(RevenueRequest revenueRequest) {

        RevenueEntity revenueEntity;
        try {
            if (revenueRequest.getRevenueType().equalsIgnoreCase("product")) {
                revenueEntity = revenueMapper.entityToRequestProduct(revenueRequest);

                // Summing up all payments for product revenue
                Double sumOfPayments = revenueRequest.getPayment1() + revenueRequest.getPayment2() +
                        revenueRequest.getPayment3() + revenueRequest.getPayment4();

                // Creating and setting RevenueProductEntity with the total payment
                RevenueProductEntity revenueProductEntity = new RevenueProductEntity();
                revenueProductEntity.setProductRevenue(sumOfPayments);
                revenueProductEntity.setRevenueEntity(revenueEntity);
                revenueEntity.setRevenueProductEntity(revenueProductEntity);
            }
            else if(revenueRequest.getRevenueType().equalsIgnoreCase("project")){
                 revenueEntity = revenueMapper.entityToRequestProject(revenueRequest);

                // Summing up all payments for product revenue
                Double sumOfPayments = revenueRequest.getPayment1() + revenueRequest.getPayment2() +
                        revenueRequest.getPayment3() + revenueRequest.getPayment4();

                // Creating and setting RevenueProjectEntity with the total payment
                RevenueProjectEntity revenueProjectEntity = new RevenueProjectEntity();
                revenueProjectEntity.setProjectRevenue(sumOfPayments);
                revenueProjectEntity.setRevenueEntity(revenueEntity);
                revenueEntity.setRevenueProjectEntity(revenueProjectEntity);
            }
            else if(revenueRequest.getRevenueType().equalsIgnoreCase("support")){
                 revenueEntity = revenueMapper.entityToRequestSupports(revenueRequest);

                // Summing up all payments for product revenue
                Double sumOfPayments = revenueRequest.getPayment1() + revenueRequest.getPayment2() +
                        revenueRequest.getPayment3() + revenueRequest.getPayment4();

                // Creating and setting RevenueProjectEntity with the total payment
                RevenueSupportEntity revenueSupportEntity = new RevenueSupportEntity();
                revenueSupportEntity.setSupportRevenue(sumOfPayments);
                revenueSupportEntity.setRevenueEntity(revenueEntity);
                revenueEntity.setRevenueSupportEntity(revenueSupportEntity);
            }
            else if(revenueRequest.getRevenueType().equalsIgnoreCase("trainee")){
                 revenueEntity = revenueMapper.entityToRequestTrainee(revenueRequest);

                // Summing up all payments for product revenue
                Double sumOfPayments = revenueRequest.getPayment1() + revenueRequest.getPayment2() +
                        revenueRequest.getPayment3() + revenueRequest.getPayment4();

                // Creating and setting RevenueProjectEntity with the total payment
                RevenueTraineeEntity revenueTraineeEntity = new RevenueTraineeEntity();
                revenueTraineeEntity.setTraineeRevenue(sumOfPayments);
                revenueTraineeEntity.setRevenueEntity(revenueEntity);
                revenueEntity.setRevenueTraineeEntity(revenueTraineeEntity);
            }
            else {
                throw new NotFoundException(HttpStatus.NOT_ACCEPTABLE,"The Revenue Type  "+revenueRequest.getRevenueType()+"  is Not Accepted..");
            }
            revenueRepository.save(revenueEntity);

            ResultResponse result = new ResultResponse();
            log.info("Project added successful with revenue type " + revenueRequest.getRevenueType());
            result.setResult("Project added successful with revenue type " + revenueRequest.getRevenueType());

            return ResponseEntity.ok(result);
        }catch (Exceptions ex){
            String message = "An error occured during revenue registration "+ex;
            log.info("An error occured during revenue registration "+ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,message);
        }
    }

    @Override
    public List<RevenueResponse> getAllRevenues() {
        List<RevenueEntity> revenueEntityList = revenueRepository.findAll();
        List<RevenueResponse> revenueResponses = revenueEntityList.stream()
                        .map(revenueMapper::responseListToEntity)
                                .collect(Collectors.toList());
        log.info("The retrieved employee details are "+revenueResponses.size());

        return revenueResponses;
    }

    @Override
    public RevenueResponse getByProjectName(String projectName) {
        try {
            Optional<RevenueEntity> revenueEntityOptional = revenueRepository.findByProjectName(projectName);
            if (revenueEntityOptional.isPresent()) {

                RevenueEntity revenueEntity = revenueEntityOptional.get();
                RevenueResponse response = revenueMapper.responseToEntity(revenueEntity);

                log.info("Retrieving the revenue details of project {}: " + projectName);
                return response;
            } else {
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The Project " + projectName + " is not found");
            }
        } catch (Exceptions ex) {
            // Handle other exceptions
            log.error("An error occurred while retrieving project details: " +projectName, ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,"An error occurred while retrieving employee by ID: " + projectName);
        }
    }

    @Override
    public ResponseEntity<?> updateRevenueByProjectName(String projectName, RevenueUpdateRequest revenueUpdateRequest) {

        RevenueEntity revenueEntity;

        try {
            Optional<RevenueEntity> revenueEntityOptional = revenueRepository.findByProjectName(projectName);

            if (revenueEntityOptional.isPresent()) {
                RevenueEntity existingRevenue = revenueEntityOptional.get();

                if (revenueUpdateRequest.getRevenueType().equalsIgnoreCase("product")) {
                    revenueEntity = revenueMapper.entityToUpdateRequestProduct(revenueUpdateRequest, existingRevenue);

                    // Summing up all payments for product revenue
                    Double sumOfPayments = revenueUpdateRequest.getPayment1() + revenueUpdateRequest.getPayment2() +
                            revenueUpdateRequest.getPayment3() + revenueUpdateRequest.getPayment4();

                    // Creating and setting RevenueProductEntity with the total payment
                    RevenueProductEntity revenueProductEntity = new RevenueProductEntity();
                    revenueProductEntity.setProductRevenue(sumOfPayments);
                    revenueProductEntity.setRevenueEntity(revenueEntity);
                    revenueEntity.setRevenueProductEntity(revenueProductEntity);

                } else if (revenueUpdateRequest.getRevenueType().equalsIgnoreCase("project")) {
                    revenueEntity = revenueMapper.entityToUpdateRequestProject(revenueUpdateRequest, existingRevenue);

                    // Summing up all payments for product revenue
                    Double sumOfPayments = revenueUpdateRequest.getPayment1() + revenueUpdateRequest.getPayment2() +
                            revenueUpdateRequest.getPayment3() + revenueUpdateRequest.getPayment4();

                    // Creating and setting RevenueProjectEntity with the total payment
                    RevenueProjectEntity revenueProjectEntity = new RevenueProjectEntity();
                    revenueProjectEntity.setProjectRevenue(sumOfPayments);
                    revenueProjectEntity.setRevenueEntity(revenueEntity);
                    revenueEntity.setRevenueProjectEntity(revenueProjectEntity);
                } else if (revenueUpdateRequest.getRevenueType().equalsIgnoreCase("support")) {
                    revenueEntity = revenueMapper.entityToUpdateRequestSupports(revenueUpdateRequest, existingRevenue);

                    // Summing up all payments for product revenue
                    Double sumOfPayments = revenueUpdateRequest.getPayment1() + revenueUpdateRequest.getPayment2() +
                            revenueUpdateRequest.getPayment3() + revenueUpdateRequest.getPayment4();

                    // Creating and setting RevenueProjectEntity with the total payment
                    RevenueSupportEntity revenueSupportEntity = new RevenueSupportEntity();
                    revenueSupportEntity.setSupportRevenue(sumOfPayments);
                    revenueSupportEntity.setRevenueEntity(revenueEntity);
                    revenueEntity.setRevenueSupportEntity(revenueSupportEntity);
                } else if (revenueUpdateRequest.getRevenueType().equalsIgnoreCase("trainee")) {
                    revenueEntity = revenueMapper.entityToUpdateRequestTrainee(revenueUpdateRequest, existingRevenue);

                    // Summing up all payments for product revenue
                    Double sumOfPayments = revenueUpdateRequest.getPayment1() + revenueUpdateRequest.getPayment2() +
                            revenueUpdateRequest.getPayment3() + revenueUpdateRequest.getPayment4();

                    // Creating and setting RevenueProjectEntity with the total payment
                    RevenueTraineeEntity revenueTraineeEntity = new RevenueTraineeEntity();
                    revenueTraineeEntity.setTraineeRevenue(sumOfPayments);
                    revenueTraineeEntity.setRevenueEntity(revenueEntity);
                    revenueEntity.setRevenueTraineeEntity(revenueTraineeEntity);
                } else {
                    throw new NotFoundException(HttpStatus.NOT_ACCEPTABLE, "The Revenue Type  " + revenueUpdateRequest.getRevenueType() + "  is Not Accepted..");
                }
                revenueRepository.save(existingRevenue);
                ResultResponse result = new ResultResponse();
                log.info("Revenue update is successful for projectName: " + projectName);
                result.setResult("Revenue update is successful for projectName: " + projectName);
                return ResponseEntity.ok(result);
            }
            else {
                log.warn("The project Revenue not found with name "+projectName);
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The project Revenue not found with name "+projectName);
            }
        }
        catch (Exceptions ex){
            log.warn("An error occured while updating the Revenue "+projectName);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while updating the revenue "+projectName);
        }
    }

    @Override
    public ResponseEntity<?> deleteRevenueByProjectName(String projectName) {
        try {
            Optional<RevenueEntity> revenueEntityOptional = revenueRepository.findByProjectName(projectName);

            if (revenueEntityOptional.isPresent()) {
                RevenueEntity existingRevenue = revenueEntityOptional.get();
                revenueRepository.delete(existingRevenue);

                ResultResponse result = new ResultResponse();
                log.info("Revenue deleted successfully for projectName: " + projectName);
                result.setResult("Revenue deleted successfully for projectName: " + projectName);

                return ResponseEntity.ok(result);
            } else {
                log.warn("The project Revenue not found with name " + projectName);
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The project Revenue not found with name " + projectName);
            }
        } catch (Exceptions ex) {
            log.warn("An error occurred while deleting the Revenue " + projectName);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while deleting the revenue " + projectName);
        }
    }


}
