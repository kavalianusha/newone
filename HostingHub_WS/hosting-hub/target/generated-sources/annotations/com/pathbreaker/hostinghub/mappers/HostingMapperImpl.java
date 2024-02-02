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
    date = "2024-01-18T17:15:08+0530",
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
        hostingEntity.setUrl1( reqDto.getUrl1() );
        hostingEntity.setUrl2( reqDto.getUrl2() );
        hostingEntity.setUrl3( reqDto.getUrl3() );
        hostingEntity.setDuration( reqDto.getDuration() );
        hostingEntity.setPassword( reqDto.getPassword() );
        hostingEntity.setEmailId( reqDto.getEmailId() );
        hostingEntity.setRegistrationPhoneNumber( reqDto.getRegistrationPhoneNumber() );
        hostingEntity.setRegistrationDomain( reqDto.getRegistrationDomain() );
        hostingEntity.setRegistrationDate( reqDto.getRegistrationDate() );
        hostingEntity.setExpiryDate( reqDto.getExpiryDate() );
        hostingEntity.setUserName( reqDto.getUserName() );
        hostingEntity.setNs1( reqDto.getNs1() );
        hostingEntity.setNs2( reqDto.getNs2() );
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
        hostingResponse.setUrl1( hostingEntity.getUrl1() );
        hostingResponse.setUrl2( hostingEntity.getUrl2() );
        hostingResponse.setUrl3( hostingEntity.getUrl3() );
        hostingResponse.setDuration( hostingEntity.getDuration() );
        hostingResponse.setPassword( hostingEntity.getPassword() );
        hostingResponse.setEmailId( hostingEntity.getEmailId() );
        hostingResponse.setRegistrationPhoneNumber( hostingEntity.getRegistrationPhoneNumber() );
        hostingResponse.setRegistrationDomain( hostingEntity.getRegistrationDomain() );
        hostingResponse.setRegistrationDate( hostingEntity.getRegistrationDate() );
        hostingResponse.setExpiryDate( hostingEntity.getExpiryDate() );
        hostingResponse.setUserName( hostingEntity.getUserName() );
        hostingResponse.setHostingDnsName( hostingEntity.getHostingDnsName() );
        hostingResponse.setNs1( hostingEntity.getNs1() );
        hostingResponse.setNs2( hostingEntity.getNs2() );
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
        hostingResponse.setUrl1( host.getUrl1() );
        hostingResponse.setUrl2( host.getUrl2() );
        hostingResponse.setUrl3( host.getUrl3() );
        hostingResponse.setDuration( host.getDuration() );
        hostingResponse.setPassword( host.getPassword() );
        hostingResponse.setEmailId( host.getEmailId() );
        hostingResponse.setRegistrationPhoneNumber( host.getRegistrationPhoneNumber() );
        hostingResponse.setRegistrationDomain( host.getRegistrationDomain() );
        hostingResponse.setRegistrationDate( host.getRegistrationDate() );
        hostingResponse.setExpiryDate( host.getExpiryDate() );
        hostingResponse.setUserName( host.getUserName() );
        hostingResponse.setHostingDnsName( host.getHostingDnsName() );
        hostingResponse.setNs1( host.getNs1() );
        hostingResponse.setNs2( host.getNs2() );
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
            hostingEntity1.setUrl1( hostingUpdateRequest.getUrl1() );
            hostingEntity1.setUrl2( hostingUpdateRequest.getUrl2() );
            hostingEntity1.setUrl3( hostingUpdateRequest.getUrl3() );
            hostingEntity1.setDuration( hostingUpdateRequest.getDuration() );
            hostingEntity1.setPassword( hostingUpdateRequest.getPassword() );
            hostingEntity1.setEmailId( hostingUpdateRequest.getEmailId() );
            hostingEntity1.setRegistrationPhoneNumber( hostingUpdateRequest.getRegistrationPhoneNumber() );
            hostingEntity1.setRegistrationDomain( hostingUpdateRequest.getRegistrationDomain() );
            hostingEntity1.setRegistrationDate( hostingUpdateRequest.getRegistrationDate() );
            hostingEntity1.setExpiryDate( hostingUpdateRequest.getExpiryDate() );
            hostingEntity1.setUserName( hostingUpdateRequest.getUserName() );
            hostingEntity1.setHostingDnsName( hostingUpdateRequest.getHostingDnsName() );
            hostingEntity1.setDaysLeft( hostingUpdateRequest.getDaysLeft() );
            hostingEntity1.setNs1( hostingUpdateRequest.getNs1() );
            hostingEntity1.setNs2( hostingUpdateRequest.getNs2() );
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
