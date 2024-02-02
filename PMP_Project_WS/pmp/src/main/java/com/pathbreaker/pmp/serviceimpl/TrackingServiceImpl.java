package com.pathbreaker.pmp.serviceimpl;

import com.pathbreaker.pmp.entity.TrackingEntity;
import com.pathbreaker.pmp.exception.Exceptions;
import com.pathbreaker.pmp.mappers.TrackingMappers;
import com.pathbreaker.pmp.repository.TrackingRepository;
import com.pathbreaker.pmp.request.TrackingRequest;
import com.pathbreaker.pmp.request.TrackingUpdateRequest;
import com.pathbreaker.pmp.response.ResultResponse;
import com.pathbreaker.pmp.response.TrackingResponse;
import com.pathbreaker.pmp.service.TrackingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TrackingServiceImpl implements TrackingService {

    private final TrackingRepository trackingRepository;
    private final TrackingMappers trackingMappers;

    @Autowired
    public TrackingServiceImpl(TrackingRepository trackingRepository, TrackingMappers trackingMappers) {
        this.trackingRepository = trackingRepository;
        this.trackingMappers = trackingMappers;
    }

    @Override
    public ResponseEntity<?> createTrackingEntity(TrackingRequest trackingRequest) {
        try {
            // Logic for generating a unique cnpId
            String highestCnpId = trackingRepository.findHighestCnpId();

            // Parse the numeric part and increment
            int numericPart = 1;
            if (highestCnpId != null && highestCnpId.length() > 4) {
                numericPart = Integer.parseInt(highestCnpId.substring(4)) + 1;
            }

            // Format the new cnpId
            String idFormat = "CnP%03d";
            String cnpId = String.format(idFormat, numericPart);

            TrackingEntity trackingEntity = trackingMappers.RequestToEntity(trackingRequest);
            trackingEntity.setCnpId(cnpId); // Set the generated cnpId

            TrackingEntity createdEntity = trackingRepository.save(trackingEntity);

            return ResponseEntity.ok(trackingMappers.EntityToResponse(createdEntity));
        } catch (Exceptions e) {
            // Handle exceptions and log an error message
            log.error("Error occurred during tracking entity creation: " + e.getMessage());
            throw  new Exceptions(HttpStatus.INTERNAL_SERVER_ERROR,"Error occurred during tracking entity creation: " + e.getMessage());
        }
    }

    @Override
    public List<TrackingResponse> getAllTrackingEntities() {
        try {
            List<TrackingEntity> trackingEntities = trackingRepository.findAll();
            log.info("Number of tracking entities retrieved: {}", trackingEntities.size());

            List<TrackingResponse> trackingResponses = trackingEntities.stream()
                    .map(trackingMappers::EntityToResponse)
                    .collect(Collectors.toList());

            log.info("Number of tracking entities mapped to response: {}", trackingResponses.size());

            return trackingResponses;
        } catch (Exception e) {
            log.error("Error occurred while retrieving all tracking entities: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<TrackingResponse> getTrackingEntityById(String cnpId) {
        try {
            Optional<TrackingEntity> trackingEntityOptional = trackingRepository.findById(cnpId);

            return trackingEntityOptional.map(trackingMappers::EntityToResponse);
        } catch (Exception e) {
            log.error("Error occurred while retrieving tracking entity by ID {}: {}", cnpId, e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public ResponseEntity<ResultResponse> updateTrackingEntity(String cnpId, TrackingUpdateRequest updatedRequest) {
        try {
            Optional<TrackingEntity> existingEntityOptional = trackingRepository.findById(cnpId);

            if (existingEntityOptional.isPresent()) {
                TrackingEntity existingEntity = existingEntityOptional.get();
                trackingMappers.updateEntityFromRequest(updatedRequest, existingEntity);

                TrackingEntity savedEntity = trackingRepository.save(existingEntity);

                ResultResponse response = new ResultResponse();
                log.info("TrackingEntity with ID {} updated successfully.", cnpId);
                response.setResult("TrackingEntity with ID " + cnpId + " updated successfully");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

            log.info("TrackingEntity with ID {} not found.", cnpId);
            ResultResponse response = new ResultResponse();
            response.setResult("TrackingEntity with ID " + cnpId + " not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            log.error("Error occurred during tracking entity update: " + e.getMessage());
            ResultResponse errorResult = new ResultResponse();
            errorResult.setResult("Error occurred during tracking entity update");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResult);
        }
    }

    @Override
    public ResponseEntity<ResultResponse> deleteTrackingEntity(String cnpId) {
        try {
            Optional<TrackingEntity> trackingEntityOptional = trackingRepository.findById(cnpId);

            if (trackingEntityOptional.isPresent()) {
                TrackingEntity trackingEntity = trackingEntityOptional.get();
                trackingRepository.delete(trackingEntity);

                ResultResponse result = new ResultResponse();
                log.info("TrackingEntity with ID {} deleted successfully.", cnpId);
                result.setResult("TrackingEntity deleted successfully");

                return ResponseEntity.ok(result);
            } else {
                log.info("TrackingEntity with ID {} not found.", cnpId);
                ResultResponse response = new ResultResponse();
                response.setResult("TrackingEntity with ID " + cnpId + " not found.");

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            log.error("Error occurred during tracking entity deletion: " + e.getMessage());
            ResultResponse errorResult = new ResultResponse();
            errorResult.setResult("Error occurred during tracking entity deletion");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResult);
        }
    }
}

