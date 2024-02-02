package com.pathbreaker.hostinghub.mappers;

import com.pathbreaker.hostinghub.entity.DomainEntity;
import com.pathbreaker.hostinghub.entity.HostingEntity;
import com.pathbreaker.hostinghub.entity.ItReturnsEntity;
import com.pathbreaker.hostinghub.entity.PasswordsEntity;
import com.pathbreaker.hostinghub.request.PasswordRequest;
import com.pathbreaker.hostinghub.response.DomainResponseView;
import com.pathbreaker.hostinghub.response.HostingResponseView;
import com.pathbreaker.hostinghub.response.ItReturnsResponseView;
import com.pathbreaker.hostinghub.response.PasswordsResponse;
import com.pathbreaker.hostinghub.response.PasswordsResponseView;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-18T17:15:08+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class PasswordsMapperImpl implements PasswordsMapper {

    @Override
    public List<PasswordsResponse> entitiesToResponses(List<PasswordsEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PasswordsResponse> list = new ArrayList<PasswordsResponse>( entities.size() );
        for ( PasswordsEntity passwordsEntity : entities ) {
            list.add( responseToEntity( passwordsEntity ) );
        }

        return list;
    }

    @Override
    public PasswordsResponseView entityToResponse(PasswordsEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PasswordsResponseView passwordsResponseView = new PasswordsResponseView();

        passwordsResponseView.setPasswordId( entity.getPasswordId() );
        passwordsResponseView.setUserName( entity.getUserName() );
        passwordsResponseView.setPassword( entity.getPassword() );
        passwordsResponseView.setRegistrationDate( entity.getRegistrationDate() );
        passwordsResponseView.setExpiryDate( entity.getExpiryDate() );
        passwordsResponseView.setDaysLeft( entity.getDaysLeft() );
        passwordsResponseView.setUpdateDate( entity.getUpdateDate() );

        return passwordsResponseView;
    }

    @Override
    public PasswordsResponse responseToEntity(PasswordsEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PasswordsResponse passwordsResponse = new PasswordsResponse();

        passwordsResponse.setPasswordId( entity.getPasswordId() );
        passwordsResponse.setUserName( entity.getUserName() );
        passwordsResponse.setPassword( entity.getPassword() );
        passwordsResponse.setRegistrationDate( entity.getRegistrationDate() );
        passwordsResponse.setExpiryDate( entity.getExpiryDate() );
        passwordsResponse.setDaysLeft( entity.getDaysLeft() );
        passwordsResponse.setUpdateDate( entity.getUpdateDate() );

        return passwordsResponse;
    }

    @Override
    public PasswordsEntity entityToRequest(PasswordRequest passwordRequest) {
        if ( passwordRequest == null ) {
            return null;
        }

        PasswordsEntity passwordsEntity = new PasswordsEntity();

        passwordsEntity.setPasswordId( passwordRequest.getPasswordId() );
        passwordsEntity.setUserName( passwordRequest.getUserName() );
        passwordsEntity.setPassword( passwordRequest.getPassword() );
        passwordsEntity.setRegistrationDate( passwordRequest.getRegistrationDate() );
        passwordsEntity.setExpiryDate( passwordRequest.getExpiryDate() );
        passwordsEntity.setDaysLeft( passwordRequest.getDaysLeft() );
        passwordsEntity.setUpdateDate( passwordRequest.getUpdateDate() );

        return passwordsEntity;
    }

    @Override
    public DomainResponseView responseToDomainView(DomainEntity domains) {
        if ( domains == null ) {
            return null;
        }

        DomainResponseView domainResponseView = new DomainResponseView();

        domainResponseView.setDomainId( domains.getDomainId() );
        domainResponseView.setDomainName( domains.getDomainName() );
        domainResponseView.setProviderName( domains.getProviderName() );
        domainResponseView.setDomainUrl( domains.getDomainUrl() );
        domainResponseView.setUserName( domains.getUserName() );
        domainResponseView.setPassword( domains.getPassword() );
        domainResponseView.setRegistrationDate( domains.getRegistrationDate() );
        domainResponseView.setExpiryDate( domains.getExpiryDate() );
        domainResponseView.setClientName( domains.getClientName() );
        domainResponseView.setDuration( domains.getDuration() );
        domainResponseView.setRegistrationName( domains.getRegistrationName() );
        domainResponseView.setRegistrationMobileNumber( domains.getRegistrationMobileNumber() );
        domainResponseView.setEmailId( domains.getEmailId() );
        domainResponseView.setDaysLeft( domains.getDaysLeft() );

        return domainResponseView;
    }

    @Override
    public HostingResponseView responseToHostingView(HostingEntity hostings) {
        if ( hostings == null ) {
            return null;
        }

        HostingResponseView hostingResponseView = new HostingResponseView();

        hostingResponseView.setHostingId( hostings.getHostingId() );
        hostingResponseView.setHostingProvider( hostings.getHostingProvider() );
        hostingResponseView.setUrl1( hostings.getUrl1() );
        hostingResponseView.setUrl2( hostings.getUrl2() );
        hostingResponseView.setUrl3( hostings.getUrl3() );
        hostingResponseView.setDuration( hostings.getDuration() );
        hostingResponseView.setPassword( hostings.getPassword() );
        hostingResponseView.setEmailId( hostings.getEmailId() );
        hostingResponseView.setRegistrationPhoneNumber( hostings.getRegistrationPhoneNumber() );
        hostingResponseView.setRegistrationDomain( hostings.getRegistrationDomain() );
        hostingResponseView.setRegistrationDate( hostings.getRegistrationDate() );
        hostingResponseView.setExpiryDate( hostings.getExpiryDate() );
        hostingResponseView.setUserName( hostings.getUserName() );
        hostingResponseView.setHostingDnsName( hostings.getHostingDnsName() );
        hostingResponseView.setNs1( hostings.getNs1() );
        hostingResponseView.setNs2( hostings.getNs2() );
        hostingResponseView.setDaysLeft( hostings.getDaysLeft() );

        return hostingResponseView;
    }

    @Override
    public ItReturnsResponseView repsonseToItReturnsView(ItReturnsEntity itReturns) {
        if ( itReturns == null ) {
            return null;
        }

        ItReturnsResponseView itReturnsResponseView = new ItReturnsResponseView();

        itReturnsResponseView.setCustomerId( itReturns.getCustomerId() );
        itReturnsResponseView.setServiceType( itReturns.getServiceType() );
        itReturnsResponseView.setEmailId( itReturns.getEmailId() );
        itReturnsResponseView.setRegisteredMobileNo( itReturns.getRegisteredMobileNo() );
        itReturnsResponseView.setRegistrationDate( itReturns.getRegistrationDate() );
        itReturnsResponseView.setExpiryDate( itReturns.getExpiryDate() );
        itReturnsResponseView.setLoginUrl( itReturns.getLoginUrl() );
        itReturnsResponseView.setUserName( itReturns.getUserName() );
        itReturnsResponseView.setPassword( itReturns.getPassword() );
        itReturnsResponseView.setCreatedBy( itReturns.getCreatedBy() );
        if ( itReturns.getCreatedDate() != null ) {
            itReturnsResponseView.setCreatedDate( LocalDate.parse( itReturns.getCreatedDate() ) );
        }
        itReturnsResponseView.setDaysLeft( itReturns.getDaysLeft() );

        return itReturnsResponseView;
    }
}
