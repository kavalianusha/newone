package com.pathbreaker.pmp.mappers;

import com.pathbreaker.pmp.entity.TrackingEntity;
import com.pathbreaker.pmp.request.TrackingRequest;
import com.pathbreaker.pmp.request.TrackingUpdateRequest;
import com.pathbreaker.pmp.response.TrackingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackingMappers {
    TrackingResponse EntityToResponse(TrackingEntity trackingEntity);
    List<TrackingResponse> responseToEntity(List<TrackingEntity> trackingEntities);
    TrackingEntity RequestToEntity(TrackingRequest trackingRequest);
    void updateEntityFromRequest(TrackingUpdateRequest updatedRequest, @MappingTarget TrackingEntity existingEntity);
}