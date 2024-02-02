package com.pathbreaker.hostinghub.mappers;

import com.pathbreaker.hostinghub.entity.ItReturnsEntity;
import com.pathbreaker.hostinghub.entity.PasswordsEntity;
import com.pathbreaker.hostinghub.request.ItReturnsRequest;
import com.pathbreaker.hostinghub.request.ItReturnsUpdateRequest;
import com.pathbreaker.hostinghub.request.PasswordUpdateRequest;
import com.pathbreaker.hostinghub.response.ItReturnsResponse;
import com.pathbreaker.hostinghub.response.PasswordsResponse;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-10T16:24:13+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class ItReturnsMapperImpl implements ItReturnsMapper {

    @Override
    public ItReturnsEntity requestToEntity(ItReturnsRequest itReturnsRequest) {
        if ( itReturnsRequest == null ) {
            return null;
        }

        ItReturnsEntity itReturnsEntity = new ItReturnsEntity();

        itReturnsEntity.setCustomerId( itReturnsRequest.getCustomerId() );
        itReturnsEntity.setServiceType( itReturnsRequest.getServiceType() );
        itReturnsEntity.setEmailId( itReturnsRequest.getEmailId() );
        itReturnsEntity.setRegisteredMobileNo( itReturnsRequest.getRegisteredMobileNo() );
        itReturnsEntity.setRegistrationDate( itReturnsRequest.getRegistrationDate() );
        itReturnsEntity.setExpiryDate( itReturnsRequest.getExpiryDate() );
        itReturnsEntity.setLoginUrl( itReturnsRequest.getLoginUrl() );
        itReturnsEntity.setUserName( itReturnsRequest.getUserName() );
        itReturnsEntity.setPassword( itReturnsRequest.getPassword() );
        itReturnsEntity.setCreatedBy( itReturnsRequest.getCreatedBy() );
        itReturnsEntity.setCreatedDate( itReturnsRequest.getCreatedDate() );
        itReturnsEntity.setDaysLeft( itReturnsRequest.getDaysLeft() );
        itReturnsEntity.setPasswordsEntity( itReturnsRequest.getPasswordsEntity() );

        return itReturnsEntity;
    }

    @Override
    public PasswordsEntity requestToPasswordEntity(ItReturnsRequest itReturnsRequest) {
        if ( itReturnsRequest == null ) {
            return null;
        }

        PasswordsEntity passwordsEntity = new PasswordsEntity();

        passwordsEntity.setUserName( itReturnsRequest.getUserName() );
        passwordsEntity.setPassword( itReturnsRequest.getPassword() );
        passwordsEntity.setRegistrationDate( itReturnsRequest.getRegistrationDate() );
        passwordsEntity.setExpiryDate( itReturnsRequest.getExpiryDate() );
        passwordsEntity.setDaysLeft( itReturnsRequest.getDaysLeft() );

        return passwordsEntity;
    }

    @Override
    public ItReturnsResponse responseToEntity(ItReturnsEntity itReturnsEntityList) {
        if ( itReturnsEntityList == null ) {
            return null;
        }

        ItReturnsResponse itReturnsResponse = new ItReturnsResponse();

        itReturnsResponse.setCustomerId( itReturnsEntityList.getCustomerId() );
        itReturnsResponse.setServiceType( itReturnsEntityList.getServiceType() );
        itReturnsResponse.setEmailId( itReturnsEntityList.getEmailId() );
        itReturnsResponse.setRegisteredMobileNo( itReturnsEntityList.getRegisteredMobileNo() );
        itReturnsResponse.setRegistrationDate( itReturnsEntityList.getRegistrationDate() );
        itReturnsResponse.setExpiryDate( itReturnsEntityList.getExpiryDate() );
        itReturnsResponse.setLoginUrl( itReturnsEntityList.getLoginUrl() );
        itReturnsResponse.setUserName( itReturnsEntityList.getUserName() );
        itReturnsResponse.setPassword( itReturnsEntityList.getPassword() );
        itReturnsResponse.setPasswordsResponse( passwordsEntityToPasswordsResponse( itReturnsEntityList.getPasswordsEntity() ) );
        itReturnsResponse.setCreatedBy( itReturnsEntityList.getCreatedBy() );
        if ( itReturnsEntityList.getCreatedDate() != null ) {
            itReturnsResponse.setCreatedDate( LocalDate.parse( itReturnsEntityList.getCreatedDate() ) );
        }
        itReturnsResponse.setDaysLeft( itReturnsEntityList.getDaysLeft() );

        return itReturnsResponse;
    }

    @Override
    public ItReturnsEntity updateEntityFromRequest(ItReturnsUpdateRequest updatedItReturns, ItReturnsEntity existingItReturns) {
        if ( updatedItReturns == null && existingItReturns == null ) {
            return null;
        }

        ItReturnsEntity itReturnsEntity = new ItReturnsEntity();

        if ( updatedItReturns != null ) {
            itReturnsEntity.setServiceType( updatedItReturns.getServiceType() );
            itReturnsEntity.setEmailId( updatedItReturns.getEmailId() );
            itReturnsEntity.setRegisteredMobileNo( updatedItReturns.getRegisteredMobileNo() );
            itReturnsEntity.setRegistrationDate( updatedItReturns.getRegistrationDate() );
            itReturnsEntity.setExpiryDate( updatedItReturns.getExpiryDate() );
            itReturnsEntity.setLoginUrl( updatedItReturns.getLoginUrl() );
            itReturnsEntity.setUserName( updatedItReturns.getUserName() );
            itReturnsEntity.setPassword( updatedItReturns.getPassword() );
            itReturnsEntity.setDaysLeft( updatedItReturns.getDaysLeft() );
            itReturnsEntity.setCreatedBy( updatedItReturns.getCreatedBy() );
            itReturnsEntity.setPasswordsEntity( passwordUpdateRequestToPasswordsEntity( updatedItReturns.getPasswordUpdateRequest() ) );
        }
        if ( existingItReturns != null ) {
            itReturnsEntity.setCustomerId( existingItReturns.getCustomerId() );
            itReturnsEntity.setCreatedDate( existingItReturns.getCreatedDate() );
        }

        return itReturnsEntity;
    }

    @Override
    public PasswordsEntity requestToUpdatePasswordEntity(ItReturnsUpdateRequest updatedItReturns) {
        if ( updatedItReturns == null ) {
            return null;
        }

        PasswordsEntity passwordsEntity = new PasswordsEntity();

        passwordsEntity.setUserName( updatedItReturns.getUserName() );
        passwordsEntity.setPassword( updatedItReturns.getPassword() );
        passwordsEntity.setRegistrationDate( updatedItReturns.getRegistrationDate() );
        passwordsEntity.setExpiryDate( updatedItReturns.getExpiryDate() );
        passwordsEntity.setDaysLeft( updatedItReturns.getDaysLeft() );

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
}
