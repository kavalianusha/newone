package com.pathbreaker.pmp.controller;

import com.pathbreaker.pmp.request.TrackingRequest;
import com.pathbreaker.pmp.request.TrackingUpdateRequest;
import com.pathbreaker.pmp.response.TrackingResponse;
import com.pathbreaker.pmp.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/tracking")
public class TrackingController {

     @Autowired
     private TrackingService trackingService;

      @PostMapping("/create")
      public ResponseEntity<?> createTrackingEntity(@RequestBody TrackingRequest trackingRequest) {
        return trackingService.createTrackingEntity(trackingRequest);
      }

        @GetMapping("/getall")
        public List<TrackingResponse> getAllTrackingEntities() {
            return trackingService.getAllTrackingEntities();
        }

        @GetMapping("/{cnpId}")
        public Optional<TrackingResponse> getTrackingEntityById(@PathVariable String cnpId) {
            return trackingService.getTrackingEntityById(cnpId);
        }

        @PutMapping("/{cnpId}")
        public ResponseEntity<?> updateTrackingEntity(@PathVariable String cnpId, @RequestBody TrackingUpdateRequest updatedRequest) {
            return trackingService.updateTrackingEntity(cnpId, updatedRequest);
        }

        @DeleteMapping("/{cnpId}")
        public ResponseEntity<?> deleteTrackingEntity(@PathVariable String cnpId) {
            return trackingService.deleteTrackingEntity(cnpId);
        }
}
