package com.pathbreaker.hostinghub.mappers;

import com.pathbreaker.hostinghub.entity.HostingEntity;
import com.pathbreaker.hostinghub.entity.PasswordsEntity;
import com.pathbreaker.hostinghub.request.HostingRequest;
import com.pathbreaker.hostinghub.request.HostingUpdateRequest;
import com.pathbreaker.hostinghub.response.HostingResponse;
import com.pathbreaker.hostinghub.response.PasswordsResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-02T17:56:00+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class HostingMapperImpl implements HostingMapper {

    @Override
    public HostingEntity entityToRequest(HostingRequest reqDto) {
        if ( reqDto == null ) {
            return null;
        }

        HostingEntity hostingEntity = new HostingEntity();

        hostingEntity.setHostingId( reqDto.getHostingId() );
        hostingEntity.setHostingProvider( reqDto.getHostingProvider() );
        hostingEntity.setUrl( reqDto.getUrl() );
        hostingEntity.setLogin( reqDto.getLogin() );
        hostingEntity.setPassword( reqDto.getPassword() );
        hostingEntity.setRegistrationEmailId( reqDto.getRegistrationEmailId() );
        hostingEntity.setRegistrationPhoneNumber( reqDto.getRegistrationPhoneNumber() );
        hostingEntity.setRegistrationDomain( reqDto.getRegistrationDomain() );
        hostingEntity.setRegistrationDate( reqDto.getRegistrationDate() );
        hostingEntity.setExpiryDate( reqDto.getExpiryDate() );
        hostingEntity.setHostingCpannelUrl( reqDto.getHostingCpannelUrl() );
        hostingEntity.setUserName( reqDto.getUserName() );
        hostingEntity.setHostingDnsName( reqDto.getHostingDnsName() );
        hostingEntity.setDaysLeft( reqDto.getDaysLeft() );
        hostingEntity.setPasswordsEntity( reqDto.getPasswordsEntity() );

        return hostingEntity;
    }

    @Override
    public HostingResponse requestToEntity(HostingEntity hostingEntity) {
        if ( hostingEntity == null ) {
            return null;
        }

        HostingResponse hostingResponse = new HostingResponse();

        hostingResponse.setHostingProvider( hostingEntity.getHostingProvider() );
        hostingResponse.setUrl( hostingEntity.getUrl() );
        hostingResponse.setLogin( hostingEntity.getLogin() );
        hostingResponse.setPassword( hostingEntity.getPassword() );
        hostingResponse.setRegistrationEmailId( hostingEntity.getRegistrationEmailId() );
        hostingResponse.setRegistrationPhoneNumber( hostingEntity.getRegistrationPhoneNumber() );
        hostingResponse.setRegistrationDomain( hostingEntity.getRegistrationDomain() );
        hostingResponse.setRegistrationDate( hostingEntity.getRegistrationDate() );
        hostingResponse.setExpiryDate( hostingEntity.getExpiryDate() );
        hostingResponse.setHostingCpannelUrl( hostingEntity.getHostingCpannelUrl() );
        hostingResponse.setUserName( hostingEntity.getUserName() );
        hostingResponse.setHostingDnsName( hostingEntity.getHostingDnsName() );
        hostingResponse.setPasswordsResponse( passwordsEntityToPasswordsResponse( hostingEntity.getPasswordsEntity() ) );
        hostingResponse.setHostingId( hostingEntity.getHostingId() );
        hostingResponse.setDaysLeft( hostingEntity.getDaysLeft() );

        return hostingResponse;
    }

    @Override
    public HostingResponse responseToEntity(HostingEntity host) {
        if ( host == null ) {
            return null;
        }

        HostingResponse hostingResponse = new HostingResponse();

        hostingResponse.setHostingProvider( host.getHostingProvider() );
        hostingResponse.setUrl( host.getUrl() );
        hostingResponse.setLogin( host.getLogin() );
        hostingResponse.setPassword( host.getPassword() );
        hostingResponse.setRegistrationEmailId( host.getRegistrationEmailId() );
        hostingResponse.setRegistrationPhoneNumber( host.getRegistrationPhoneNumber() );
        hostingResponse.setRegistrationDomain( host.getRegistrationDomain() );
        hostingResponse.setRegistrationDate( host.getRegistrationDate() );
        hostingResponse.setExpiryDate( host.getExpiryDate() );
        hostingResponse.setHostingCpannelUrl( host.getHostingCpannelUrl() );
        hostingResponse.setUserName( host.getUserName() );
        hostingResponse.setHostingDnsName( host.getHostingDnsName() );
        hostingResponse.setPasswordsResponse( passwordsEntityToPasswordsResponse( host.getPasswordsEntity() ) );
        hostingResponse.setHostingId( host.getHostingId() );
        hostingResponse.setDaysLeft( host.getDaysLeft() );

        return hostingResponse;
    }

    @Override
    public HostingEntity updateEntityFromRequest(HostingEntity hostingEntity, HostingUpdateRequest hostingUpdateRequest) {
        if ( hostingEntity == null && hostingUpdateRequest == null ) {
            return null;
        }

        HostingEntity hostingEntity1 = new HostingEntity();

        if ( hostingEntity != null ) {
            hostingEntity1.setHostingId( hostingEntity.getHostingId() );
        }
        if ( hostingUpdateRequest != null ) {
            hostingEntity1.setHostingProvider( hostingUpdateRequest.getHostingProvider() );
            hostingEntity1.setUrl( hostingUpdateRequest.getUrl() );
            hostingEntity1.setLogin( hostingUpdateRequest.getLogin() );
            hostingEntity1.setPassword( hostingUpdateRequest.getPassword() );
            hostingEntity1.setRegistrationEmailId( hostingUpdateRequest.getRegistrationEmailId() );
            hostingEntity1.setRegistrationPhoneNumber( hostingUpdateRequest.getRegistrationPhoneNumber() );
            hostingEntity1.setRegistrationDomain( hostingUpdateRequest.getRegistrationDomain() );
            hostingEntity1.setRegistrationDate( hostingUpdateRequest.getRegistrationDate() );
            hostingEntity1.setExpiryDate( hostingUpdateRequest.getExpiryDate() );
            hostingEntity1.setHostingCpannelUrl( hostingUpdateRequest.getHostingCpannelUrl() );
            hostingEntity1.setUserName( hostingUpdateRequest.getUserName() );
            hostingEntity1.setHostingDnsName( hostingUpdateRequest.getHostingDnsName() );
            hostingEntity1.setDaysLeft( hostingUpdateRequest.getDaysLeft() );
            hostingEntity1.setPasswordsEntity( hostingUpdateRequest.getPasswordsEntity() );
        }

        return hostingEntity1;
    }

    @Override
    public PasswordsEntity requestToUpdatePasswordEntity(HostingUpdateRequest hostingUpdateRequest) {
        if ( hostingUpdateRequest == null ) {
            return null;
        }

        PasswordsEntity passwordsEntity = new PasswordsEntity();

        passwordsEntity.setUserName( hostingUpdateRequest.getUserName() );
        passwordsEntity.setPassword( hostingUpdateRequest.getPassword() );
        passwordsEntity.setRegistrationDate( hostingUpdateRequest.getRegistrationDate() );
        passwordsEntity.setExpiryDate( hostingUpdateRequest.getExpiryDate() );
        passwordsEntity.setDaysLeft( hostingUpdateRequest.getDaysLeft() );

        return passwordsEntity;
    }

    @Override
    public PasswordsEntity requestToPasswordEntity(HostingRequest request) {
        if ( request == null ) {
            return null;
        }

        PasswordsEntity passwordsEntity = new PasswordsEntity();

        passwordsEntity.setUserName( request.getUserName() );
        passwordsEntity.setPassword( request.getPassword() );
        passwordsEntity.setRegistrationDate( request.getRegistrationDate() );
        passwordsEntity.setExpiryDate( request.getExpiryDate() );
        passwordsEntity.setDaysLeft( request.getDaysLeft() );

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
