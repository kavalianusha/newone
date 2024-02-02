package com.pathbreaker.hostinghub.mappers;

import com.pathbreaker.hostinghub.entity.HostDomainMapEntity;
import com.pathbreaker.hostinghub.entity.HostingEntity;
import com.pathbreaker.hostinghub.request.HostDomainMapRequest;
import com.pathbreaker.hostinghub.request.HostDomainMapUpdateRequest;
import com.pathbreaker.hostinghub.response.HostDomainMapResponse;
import com.pathbreaker.hostinghub.response.HostingResponseView;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-02T12:56:32+0530",
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
        hostDomainMapRequest.setDomainProvider( domainEntity.getDomainProvider() );
        hostDomainMapRequest.setUserName( domainEntity.getUserName() );
        hostDomainMapRequest.setPassword( domainEntity.getPassword() );
        hostDomainMapRequest.setHostUserName( domainEntity.getHostUserName() );
        hostDomainMapRequest.setUrl1( domainEntity.getUrl1() );
        hostDomainMapRequest.setHostPassword( domainEntity.getHostPassword() );

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
        hostDomainMapEntity.setUserName( request.getUserName() );
        hostDomainMapEntity.setPassword( request.getPassword() );
        hostDomainMapEntity.setPayment( request.getPayment() );
        hostDomainMapEntity.setDomainProvider( request.getDomainProvider() );
        hostDomainMapEntity.setHostUserName( request.getHostUserName() );
        hostDomainMapEntity.setUrl1( request.getUrl1() );
        hostDomainMapEntity.setHostPassword( request.getHostPassword() );

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
        hostDomainMapResponse.setDomainProvider( entity.getDomainProvider() );
        hostDomainMapResponse.setUserName( entity.getUserName() );
        hostDomainMapResponse.setPassword( entity.getPassword() );
        hostDomainMapResponse.setHostUserName( entity.getHostUserName() );
        hostDomainMapResponse.setUrl1( entity.getUrl1() );
        hostDomainMapResponse.setHostPassword( entity.getHostPassword() );

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
        hostDomainMapEntity.setDomainProvider( hostDomainMapUpdateRequest.getDomainProvider() );
        hostDomainMapEntity.setUserName( hostDomainMapUpdateRequest.getUserName() );
        hostDomainMapEntity.setPassword( hostDomainMapUpdateRequest.getPassword() );
        hostDomainMapEntity.setHostUserName( hostDomainMapUpdateRequest.getHostUserName() );
        hostDomainMapEntity.setUrl1( hostDomainMapUpdateRequest.getUrl1() );
        hostDomainMapEntity.setHostPassword( hostDomainMapUpdateRequest.getHostPassword() );

        return hostDomainMapEntity;
    }

    @Override
    public HostingResponseView responseToView(HostingEntity hostingEntity) {
        if ( hostingEntity == null ) {
            return null;
        }

        HostingResponseView hostingResponseView = new HostingResponseView();

        hostingResponseView.setHostingId( hostingEntity.getHostingId() );
        hostingResponseView.setHostingProvider( hostingEntity.getHostingProvider() );
        hostingResponseView.setUrl1( hostingEntity.getUrl1() );
        hostingResponseView.setUrl2( hostingEntity.getUrl2() );
        hostingResponseView.setUrl3( hostingEntity.getUrl3() );
        hostingResponseView.setDuration( hostingEntity.getDuration() );
        hostingResponseView.setPassword( hostingEntity.getPassword() );
        hostingResponseView.setEmailId( hostingEntity.getEmailId() );
        hostingResponseView.setRegistrationPhoneNumber( hostingEntity.getRegistrationPhoneNumber() );
        hostingResponseView.setRegistrationDomain( hostingEntity.getRegistrationDomain() );
        hostingResponseView.setRegistrationDate( hostingEntity.getRegistrationDate() );
        hostingResponseView.setExpiryDate( hostingEntity.getExpiryDate() );
        hostingResponseView.setUserName( hostingEntity.getUserName() );
        hostingResponseView.setHostingDnsName( hostingEntity.getHostingDnsName() );
        hostingResponseView.setNs1( hostingEntity.getNs1() );
        hostingResponseView.setNs2( hostingEntity.getNs2() );
        hostingResponseView.setDaysLeft( hostingEntity.getDaysLeft() );

        return hostingResponseView;
    }
}
