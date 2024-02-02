package com.pathbreaker.pmp.serviceimpl;

import com.pathbreaker.pmp.entity.ResourceEntity;
import com.pathbreaker.pmp.entity.ResourceSkillsEntity;
import com.pathbreaker.pmp.exception.Exceptions;
import com.pathbreaker.pmp.exception.NotFoundException;
import com.pathbreaker.pmp.mappers.ResourceMapper;
import com.pathbreaker.pmp.mappers.ResourceSkillsMapper;
import com.pathbreaker.pmp.repository.ResourceRepository;
import com.pathbreaker.pmp.repository.ResourceSkillRepository;
import com.pathbreaker.pmp.request.ResourceRequest;
import com.pathbreaker.pmp.request.ResourceSkillsRequest;
import com.pathbreaker.pmp.request.ResourceSkillsUpdateRequest;
import com.pathbreaker.pmp.request.ResourceUpdateRequest;
import com.pathbreaker.pmp.response.ResourceResponse;
import com.pathbreaker.pmp.response.ResourceSkillsResponse;
import com.pathbreaker.pmp.response.ResultResponse;
import com.pathbreaker.pmp.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    public ResourceServiceImpl(ResourceRepository resourceRepository,
                               ResourceMapper resourceMapper,
                               ResourceSkillsMapper resourceSkillsMapper,
                               ResourceSkillRepository resourceSkillRepository){
        this.resourceRepository = resourceRepository;
        this.resourceMapper = resourceMapper;
        this.resourceSkillsMapper = resourceSkillsMapper;
        this.resourceSkillRepository = resourceSkillRepository;

    }
    private ResourceRepository resourceRepository;

    private ResourceSkillRepository resourceSkillRepository;

    private ResourceMapper resourceMapper;

    private ResourceSkillsMapper resourceSkillsMapper;


    public String generateEmpId() {
        String hostIdDetails = resourceRepository.findHighestEmpId();

        int numericPart = 1;
        if (hostIdDetails != null) {
            numericPart = Integer.parseInt(hostIdDetails.substring(3)) + 1;
        }
        String idFormat = "EMP%03d";
        System.out.println("the id is :" + String.format(idFormat,numericPart));
        return String.format(idFormat, numericPart);
    }
    @Override
    public ResponseEntity<?> createResource(ResourceRequest resourceRequest) {

        try {
            String employeeId = generateEmpId();
            resourceRequest.setEmployeeId(employeeId);
            ResourceEntity resourceEntity = resourceMapper.entityToRequest(resourceRequest);

            ResourceSkillsEntity resourceSkillsEntity = resourceSkillsMapper.entityToRequest(resourceRequest.getResourceSkillsRequest());
            resourceEntity.setResourceSkillsEntity(resourceSkillsEntity);
            resourceEntity.getResourceSkillsEntity().setResourceEntity(resourceEntity);

            resourceRepository.save(resourceEntity);

            ResultResponse result = new ResultResponse();
            log.info("EMPLOYEE registration is successful " + resourceRequest.getEmployeeName());
            result.setResult("EMPLOYEE registration is successfull.....");

            return ResponseEntity.ok(result);
        }
        catch (Exceptions ex){
            String message = "An error occured during employee registration"+ex;
            log.info("An error occured during employee registration"+ex);
            throw new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,message);
        }

    }

    @Override
    public List<ResourceResponse> getAllResources() {
        List<ResourceEntity> resourceEntityList = resourceRepository.findAll();

        List<ResourceResponse> resourceResponses = resourceEntityList.stream()
                .map(resourceMapper::responseListToEntity)
                .collect(Collectors.toList());
        log.info("The retrieved employee details are "+resourceResponses.size());

        return resourceResponses;
    }

    @Override
    public  ResourceResponse getAllResourceById(String employeeId) {
        try {
            Optional<ResourceEntity> employeeOptional = resourceRepository.findByEmployeeId(employeeId);
            if (employeeOptional.isPresent()) {

                ResourceEntity resourceEntity = employeeOptional.get();
                ResourceResponse response = resourceMapper.responseToEntity(resourceEntity);

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
    public ResponseEntity<?> updateResourceById(String employeeId, ResourceUpdateRequest resourceUpdateRequest) {

        try {
            Optional<ResourceEntity> existingResourceOptional = resourceRepository.findByEmployeeId(employeeId);

            if (existingResourceOptional.isPresent()) {
                ResourceEntity existingResource = existingResourceOptional.get();

                // Update the existing resource with the new data from the request
                ResourceEntity resourceEntity = resourceMapper.updateEntityFromRequest(resourceUpdateRequest, existingResource);

                // Update the resource skills entity as well, assuming it is a separate entity
                ResourceSkillsEntity resourceSkillsEntity = resourceSkillsMapper.updateEntityFromRequest(resourceUpdateRequest.getResourceSkillsUpdateRequest(),existingResource.getResourceSkillsEntity());

                resourceEntity.setResourceSkillsEntity(resourceSkillsEntity);
                // Save the updated resource to the database
                resourceRepository.save(existingResource);

                ResultResponse result = new ResultResponse();
                log.info("EMPLOYEE update is successful for employeeId: " + employeeId);
                result.setResult("EMPLOYEE update is successful.....");

                return ResponseEntity.ok(result);
            } else {
                log.warn("The employee not found with "+employeeId);
                throw new NotFoundException(HttpStatus.NOT_FOUND, "The employee with " + employeeId + " not found");
            }
        } catch (Exceptions ex) {
            log.warn("An error occured while updating the employee "+employeeId);
            throw new NotFoundException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while updating the employee "+employeeId);
        }
    }

    @Override
    public ResponseEntity<?> deleteResourceById(String employeeId) {
        try {
            Optional<ResourceEntity> existingResourceOptional = resourceRepository.findByEmployeeId(employeeId);

            if (existingResourceOptional.isPresent()) {
                ResourceEntity existingResource = existingResourceOptional.get();
                // Delete associated resource skills
                if (existingResource.getResourceSkillsEntity() != null) {
                    resourceSkillRepository.delete(existingResource.getResourceSkillsEntity());
                }
                // Delete the resource
                resourceRepository.delete(existingResource);

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
