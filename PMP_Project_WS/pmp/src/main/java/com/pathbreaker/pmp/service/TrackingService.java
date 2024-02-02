package com.pathbreaker.pmp.service;

import com.pathbreaker.pmp.request.TrackingRequest;
import com.pathbreaker.pmp.request.TrackingUpdateRequest;
import com.pathbreaker.pmp.response.TrackingResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TrackingService {

    public ResponseEntity<?> createTrackingEntity(TrackingRequest trackingRequest);

    public List<TrackingResponse> getAllTrackingEntities();

    public Optional<TrackingResponse> getTrackingEntityById(String cnpId);

    public ResponseEntity<?> updateTrackingEntity(String cnpId, TrackingUpdateRequest updatedRequest);

    public ResponseEntity<?> deleteTrackingEntity(String cnpId);
}
