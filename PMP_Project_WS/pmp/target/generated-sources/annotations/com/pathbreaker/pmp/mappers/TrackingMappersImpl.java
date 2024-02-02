package com.pathbreaker.pmp.mappers;

import com.pathbreaker.pmp.entity.TrackingEntity;
import com.pathbreaker.pmp.request.TrackingRequest;
import com.pathbreaker.pmp.request.TrackingUpdateRequest;
import com.pathbreaker.pmp.response.TrackingResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-23T19:18:23+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class TrackingMappersImpl implements TrackingMappers {

    @Override
    public TrackingResponse EntityToResponse(TrackingEntity trackingEntity) {
        if ( trackingEntity == null ) {
            return null;
        }

        TrackingResponse trackingResponse = new TrackingResponse();

        trackingResponse.setMonth( trackingEntity.getMonth() );
        trackingResponse.setRevenueType( trackingEntity.getRevenueType() );
        trackingResponse.setSource( trackingEntity.getSource() );
        if ( trackingEntity.getCompletion() != null ) {
            trackingResponse.setCompletion( trackingEntity.getCompletion() );
        }
        trackingResponse.setNameOfSource( trackingEntity.getNameOfSource() );
        trackingResponse.setResourceAssigned( trackingEntity.getResourceAssigned() );
        if ( trackingEntity.getRevenue() != null ) {
            trackingResponse.setRevenue( trackingEntity.getRevenue() );
        }
        trackingResponse.setDate( trackingEntity.getDate() );

        return trackingResponse;
    }

    @Override
    public List<TrackingResponse> responseToEntity(List<TrackingEntity> trackingEntities) {
        if ( trackingEntities == null ) {
            return null;
        }

        List<TrackingResponse> list = new ArrayList<TrackingResponse>( trackingEntities.size() );
        for ( TrackingEntity trackingEntity : trackingEntities ) {
            list.add( EntityToResponse( trackingEntity ) );
        }

        return list;
    }

    @Override
    public TrackingEntity RequestToEntity(TrackingRequest trackingRequest) {
        if ( trackingRequest == null ) {
            return null;
        }

        TrackingEntity trackingEntity = new TrackingEntity();

        trackingEntity.setMonth( trackingRequest.getMonth() );
        trackingEntity.setRevenueType( trackingRequest.getRevenueType() );
        trackingEntity.setSource( trackingRequest.getSource() );
        trackingEntity.setCompletion( trackingRequest.getCompletion() );
        trackingEntity.setNameOfSource( trackingRequest.getNameOfSource() );
        trackingEntity.setResourceAssigned( trackingRequest.getResourceAssigned() );
        trackingEntity.setRevenue( trackingRequest.getRevenue() );
        trackingEntity.setDate( trackingRequest.getDate() );

        return trackingEntity;
    }

    @Override
    public void updateEntityFromRequest(TrackingUpdateRequest updatedRequest, TrackingEntity existingEntity) {
        if ( updatedRequest == null ) {
            return;
        }

        existingEntity.setMonth( updatedRequest.getMonth() );
        existingEntity.setRevenueType( updatedRequest.getRevenueType() );
        existingEntity.setSource( updatedRequest.getSource() );
        existingEntity.setCompletion( updatedRequest.getCompletion() );
        existingEntity.setNameOfSource( updatedRequest.getNameOfSource() );
        existingEntity.setResourceAssigned( updatedRequest.getResourceAssigned() );
        existingEntity.setRevenue( updatedRequest.getRevenue() );
        existingEntity.setDate( updatedRequest.getDate() );
    }
}
