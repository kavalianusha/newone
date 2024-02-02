package com.example.hostinghub.mappers;

import com.example.hostinghub.entity.HostDomainMapEntity;
import com.example.hostinghub.request.HostDomainMapRequest;
import com.example.hostinghub.request.HostDomainMapUpdateRequest;
import com.example.hostinghub.response.HostDomainMapResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-08T16:33:25+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class HostDomainMapMapperImpl implements HostDomainMapMapper {

    @Override
    public HostDomainMapRequest requestToEntity(HostDomainMapEntity domainEntity) {
        if ( domainEntity == null ) {
            return null;
        }

        HostDomainMapRequest hostDomainMapRequest = new HostDomainMapRequest();

        hostDomainMapRequest.setHostDomainId( domainEntity.getHostDomainId() );
        hostDomainMapRequest.setDomainName( domainEntity.getDomainName() );
        hostDomainMapRequest.setHostProvider( domainEntity.getHostProvider() );
        hostDomainMapRequest.setPayment( domainEntity.getPayment() );
        hostDomainMapRequest.setRegistrationDate( domainEntity.getRegistrationDate() );
        hostDomainMapRequest.setDuration( domainEntity.getDuration() );
        hostDomainMapRequest.setExpiryDate( domainEntity.getExpiryDate() );
        hostDomainMapRequest.setDaysLeft( domainEntity.getDaysLeft() );

        return hostDomainMapRequest;
    }

    @Override
    public HostDomainMapEntity entityToRequest(HostDomainMapRequest request) {
        if ( request == null ) {
            return null;
        }

        HostDomainMapEntity hostDomainMapEntity = new HostDomainMapEntity();

        hostDomainMapEntity.setHostDomainId( request.getHostDomainId() );
        hostDomainMapEntity.setDomainName( request.getDomainName() );
        hostDomainMapEntity.setHostProvider( request.getHostProvider() );
        hostDomainMapEntity.setPayment( request.getPayment() );
        hostDomainMapEntity.setRegistrationDate( request.getRegistrationDate() );
        hostDomainMapEntity.setDuration( request.getDuration() );
        hostDomainMapEntity.setExpiryDate( request.getExpiryDate() );
        hostDomainMapEntity.setDaysLeft( request.getDaysLeft() );

        return hostDomainMapEntity;
    }

    @Override
    public List<HostDomainMapResponse> responseToEntity(List<HostDomainMapEntity> req) {
        if ( req == null ) {
            return null;
        }

        List<HostDomainMapResponse> list = new ArrayList<HostDomainMapResponse>( req.size() );
        for ( HostDomainMapEntity hostDomainMapEntity : req ) {
            list.add( entityToResponse( hostDomainMapEntity ) );
        }

        return list;
    }

    @Override
    public HostDomainMapResponse entityToResponse(HostDomainMapEntity entity) {
        if ( entity == null ) {
            return null;
        }

        HostDomainMapResponse hostDomainMapResponse = new HostDomainMapResponse();

        hostDomainMapResponse.setHostDomainId( entity.getHostDomainId() );
        hostDomainMapResponse.setDomainName( entity.getDomainName() );
        hostDomainMapResponse.setHostProvider( entity.getHostProvider() );
        hostDomainMapResponse.setPayment( entity.getPayment() );
        hostDomainMapResponse.setRegistrationDate( entity.getRegistrationDate() );
        hostDomainMapResponse.setDuration( entity.getDuration() );
        hostDomainMapResponse.setExpiryDate( entity.getExpiryDate() );
        hostDomainMapResponse.setDaysLeft( entity.getDaysLeft() );

        return hostDomainMapResponse;
    }

    @Override
    public HostDomainMapEntity updateEntityFromRequest(HostDomainMapUpdateRequest hostDomainMapUpdateRequest, HostDomainMapEntity hostDomainMapEntity) {
        if ( hostDomainMapUpdateRequest == null ) {
            return hostDomainMapEntity;
        }

        hostDomainMapEntity.setDomainName( hostDomainMapUpdateRequest.getDomainName() );
        hostDomainMapEntity.setHostProvider( hostDomainMapUpdateRequest.getHostProvider() );
        hostDomainMapEntity.setPayment( hostDomainMapUpdateRequest.getPayment() );
        hostDomainMapEntity.setRegistrationDate( hostDomainMapUpdateRequest.getRegistrationDate() );
        hostDomainMapEntity.setDuration( hostDomainMapUpdateRequest.getDuration() );
        hostDomainMapEntity.setExpiryDate( hostDomainMapUpdateRequest.getExpiryDate() );
        if ( hostDomainMapUpdateRequest.getDaysLeft() != null ) {
            hostDomainMapEntity.setDaysLeft( hostDomainMapUpdateRequest.getDaysLeft() );
        }

        return hostDomainMapEntity;
    }
}
