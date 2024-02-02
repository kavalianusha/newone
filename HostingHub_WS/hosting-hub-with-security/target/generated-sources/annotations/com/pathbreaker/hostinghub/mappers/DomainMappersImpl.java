package com.pathbreaker.hostinghub.mappers;

import com.pathbreaker.hostinghub.entity.DomainEntity;
import com.pathbreaker.hostinghub.entity.PasswordsEntity;
import com.pathbreaker.hostinghub.request.DomainRequest;
import com.pathbreaker.hostinghub.request.DomainUpdateRequest;
import com.pathbreaker.hostinghub.request.PasswordRequest;
import com.pathbreaker.hostinghub.request.PasswordUpdateRequest;
import com.pathbreaker.hostinghub.response.DomainResponse;
import com.pathbreaker.hostinghub.response.PasswordsResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-10T16:24:13+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class DomainMappersImpl implements DomainMappers {

    @Override
    public DomainEntity entityToRequest(DomainRequest request) {
        if ( request == null ) {
            return null;
        }

        DomainEntity domainEntity = new DomainEntity();

        domainEntity.setDomainId( request.getDomainId() );
        domainEntity.setDomainName( request.getDomainName() );
        domainEntity.setClientName( request.getClientName() );
        domainEntity.setDuration( request.getDuration() );
        domainEntity.setProviderName( request.getProviderName() );
        domainEntity.setDomainUrl( request.getDomainUrl() );
        domainEntity.setUserName( request.getUserName() );
        domainEntity.setPassword( request.getPassword() );
        domainEntity.setExpiryDate( request.getExpiryDate() );
        domainEntity.setRegistrationName( request.getRegistrationName() );
        domainEntity.setRegistrationDate( request.getRegistrationDate() );
        domainEntity.setRegistrationMobileNumber( request.getRegistrationMobileNumber() );
        domainEntity.setEmailId( request.getEmailId() );
        domainEntity.setPasswordsEntity( passwordRequestToPasswordsEntity( request.getPasswordRequest() ) );
        domainEntity.setDaysLeft( request.getDaysLeft() );

        return domainEntity;
    }

    @Override
    public PasswordsEntity entityPasswordToRequest(DomainRequest domainRequest) {
        if ( domainRequest == null ) {
            return null;
        }

        PasswordsEntity passwordsEntity = new PasswordsEntity();

        passwordsEntity.setUserName( domainRequest.getUserName() );
        passwordsEntity.setPassword( domainRequest.getPassword() );
        passwordsEntity.setRegistrationDate( domainRequest.getRegistrationDate() );
        passwordsEntity.setExpiryDate( domainRequest.getExpiryDate() );
        passwordsEntity.setDaysLeft( domainRequest.getDaysLeft() );

        return passwordsEntity;
    }

    @Override
    public DomainEntity updateEntityFromRequest(DomainEntity existingDomainEntity, DomainUpdateRequest domainUpdateRequest) {
        if ( existingDomainEntity == null && domainUpdateRequest == null ) {
            return null;
        }

        DomainEntity domainEntity = new DomainEntity();

        if ( existingDomainEntity != null ) {
            domainEntity.setDomainId( existingDomainEntity.getDomainId() );
        }
        if ( domainUpdateRequest != null ) {
            domainEntity.setDomainName( domainUpdateRequest.getDomainName() );
            domainEntity.setProviderName( domainUpdateRequest.getProviderName() );
            domainEntity.setClientName( domainUpdateRequest.getClientName() );
            domainEntity.setDuration( domainUpdateRequest.getDuration() );
            domainEntity.setDomainUrl( domainUpdateRequest.getDomainUrl() );
            domainEntity.setUserName( domainUpdateRequest.getUserName() );
            domainEntity.setPassword( domainUpdateRequest.getPassword() );
            domainEntity.setExpiryDate( domainUpdateRequest.getExpiryDate() );
            domainEntity.setRegistrationName( domainUpdateRequest.getRegistrationName() );
            domainEntity.setRegistrationDate( domainUpdateRequest.getRegistrationDate() );
            domainEntity.setRegistrationMobileNumber( domainUpdateRequest.getRegistrationMobileNumber() );
            domainEntity.setEmailId( domainUpdateRequest.getEmailId() );
            domainEntity.setDaysLeft( domainUpdateRequest.getDaysLeft() );
            domainEntity.setPasswordsEntity( passwordUpdateRequestToPasswordsEntity( domainUpdateRequest.getPasswordUpdateRequest() ) );
        }

        return domainEntity;
    }

    @Override
    public PasswordsEntity requestToUpdatePasswordEntity(DomainUpdateRequest updatedDomainRequest) {
        if ( updatedDomainRequest == null ) {
            return null;
        }

        PasswordsEntity passwordsEntity = new PasswordsEntity();

        passwordsEntity.setUserName( updatedDomainRequest.getUserName() );
        passwordsEntity.setPassword( updatedDomainRequest.getPassword() );
        passwordsEntity.setRegistrationDate( updatedDomainRequest.getRegistrationDate() );
        passwordsEntity.setExpiryDate( updatedDomainRequest.getExpiryDate() );
        passwordsEntity.setDaysLeft( updatedDomainRequest.getDaysLeft() );

        return passwordsEntity;
    }

    @Override
    public List<DomainResponse> responseToEntity(List<DomainEntity> domains) {
        if ( domains == null ) {
            return null;
        }

        List<DomainResponse> list = new ArrayList<DomainResponse>( domains.size() );
        for ( DomainEntity domainEntity : domains ) {
            list.add( domainEntityToResponse( domainEntity ) );
        }

        return list;
    }

    @Override
    public DomainResponse domainEntityToResponse(DomainEntity domainEntity) {
        if ( domainEntity == null ) {
            return null;
        }

        DomainResponse domainResponse = new DomainResponse();

        domainResponse.setDomainName( domainEntity.getDomainName() );
        domainResponse.setProviderName( domainEntity.getProviderName() );
        domainResponse.setClientName( domainEntity.getClientName() );
        domainResponse.setDuration( domainEntity.getDuration() );
        domainResponse.setDomainUrl( domainEntity.getDomainUrl() );
        domainResponse.setUserName( domainEntity.getUserName() );
        domainResponse.setPassword( domainEntity.getPassword() );
        domainResponse.setExpiryDate( domainEntity.getExpiryDate() );
        domainResponse.setRegistrationName( domainEntity.getRegistrationName() );
        domainResponse.setRegistrationDate( domainEntity.getRegistrationDate() );
        domainResponse.setRegistrationMobileNumber( domainEntity.getRegistrationMobileNumber() );
        domainResponse.setEmailId( domainEntity.getEmailId() );
        domainResponse.setPasswordsResponse( passwordsEntityToPasswordsResponse( domainEntity.getPasswordsEntity() ) );
        domainResponse.setDomainId( domainEntity.getDomainId() );
        domainResponse.setDaysLeft( domainEntity.getDaysLeft() );

        return domainResponse;
    }

    protected PasswordsEntity passwordRequestToPasswordsEntity(PasswordRequest passwordRequest) {
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

    protected PasswordsEntity passwordUpdateRequestToPasswordsEntity(PasswordUpdateRequest passwordUpdateRequest) {
        if ( passwordUpdateRequest == null ) {
            return null;
        }

        PasswordsEntity passwordsEntity = new PasswordsEntity();

        passwordsEntity.setPasswordId( passwordUpdateRequest.getPasswordId() );
        passwordsEntity.setUserName( passwordUpdateRequest.getUserName() );
        passwordsEntity.setPassword( passwordUpdateRequest.getPassword() );
        passwordsEntity.setRegistrationDate( passwordUpdateRequest.getRegistrationDate() );
        passwordsEntity.setExpiryDate( passwordUpdateRequest.getExpiryDate() );
        passwordsEntity.setDaysLeft( passwordUpdateRequest.getDaysLeft() );
        passwordsEntity.setUpdateDate( passwordUpdateRequest.getUpdateDate() );

        return passwordsEntity;
    }

    protected PasswordsResponse passwordsEntityToPasswordsResponse(PasswordsEntity passwordsEntity) {
        if ( passwordsEntity == null ) {
            return null;
        }

        PasswordsResponse passwordsResponse = new PasswordsResponse();

        passwordsResponse.setPasswordId( passwordsEntity.getPasswordId() );
        passwordsResponse.setUserName( passwordsEntity.getUserName() );
        passwordsResponse.setPassword( passwordsEntity.getPassword() );
        passwordsResponse.setRegistrationDate( passwordsEntity.getRegistrationDate() );
        passwordsResponse.setExpiryDate( passwordsEntity.getExpiryDate() );
        passwordsResponse.setDaysLeft( passwordsEntity.getDaysLeft() );
        passwordsResponse.setUpdateDate( passwordsEntity.getUpdateDate() );

        return passwordsResponse;
    }
}
