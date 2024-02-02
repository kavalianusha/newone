package com.pathbreaker.hostinghub.mappers;

import com.pathbreaker.hostinghub.entity.AdminEntity;
import com.pathbreaker.hostinghub.request.AdminRequest;
import com.pathbreaker.hostinghub.request.AdminUpdateRequest;
import com.pathbreaker.hostinghub.response.AdminEntityPayload;
import com.pathbreaker.hostinghub.response.AdminResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-05T14:56:52+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public AdminRequest requestToEntity(AdminEntity adminEntity) {
        if ( adminEntity == null ) {
            return null;
        }

        AdminRequest adminRequest = new AdminRequest();

        adminRequest.setAdminId( adminEntity.getAdminId() );
        adminRequest.setName( adminEntity.getName() );
        adminRequest.setUserName( adminEntity.getUserName() );
        adminRequest.setEmailId( adminEntity.getEmailId() );
        adminRequest.setPassword( adminEntity.getPassword() );
        adminRequest.setPhoneNo( adminEntity.getPhoneNo() );
        adminRequest.setRole( adminEntity.getRole() );
        adminRequest.setOtp( adminEntity.getOtp() );

        return adminRequest;
    }

    @Override
    public AdminEntity entityToRequest(AdminRequest request) {
        if ( request == null ) {
            return null;
        }

        AdminEntity adminEntity = new AdminEntity();

        adminEntity.setAdminId( request.getAdminId() );
        adminEntity.setName( request.getName() );
        adminEntity.setUserName( request.getUserName() );
        adminEntity.setEmailId( request.getEmailId() );
        adminEntity.setPassword( request.getPassword() );
        adminEntity.setPhoneNo( request.getPhoneNo() );
        adminEntity.setRole( request.getRole() );
        adminEntity.setOtp( request.getOtp() );

        return adminEntity;
    }

    @Override
    public List<AdminResponse> responseToEntityList(List<AdminEntity> adminEntities) {
        if ( adminEntities == null ) {
            return null;
        }

        List<AdminResponse> list = new ArrayList<AdminResponse>( adminEntities.size() );
        for ( AdminEntity adminEntity : adminEntities ) {
            list.add( responseToEntity( adminEntity ) );
        }

        return list;
    }

    @Override
    public AdminResponse responseToEntity(AdminEntity adminEntity) {
        if ( adminEntity == null ) {
            return null;
        }

        AdminResponse adminResponse = new AdminResponse();

        adminResponse.setAdminId( adminEntity.getAdminId() );
        adminResponse.setName( adminEntity.getName() );
        adminResponse.setUserName( adminEntity.getUserName() );
        adminResponse.setEmailId( adminEntity.getEmailId() );
        adminResponse.setPassword( adminEntity.getPassword() );
        adminResponse.setPhoneNo( adminEntity.getPhoneNo() );
        adminResponse.setRole( adminEntity.getRole() );
        adminResponse.setOtp( adminEntity.getOtp() );

        return adminResponse;
    }

    @Override
    public AdminEntityPayload userResponseToEntity(AdminEntity adminEntity) {
        if ( adminEntity == null ) {
            return null;
        }

        AdminEntityPayload adminEntityPayload = new AdminEntityPayload();

        adminEntityPayload.setAdminId( adminEntity.getAdminId() );
        adminEntityPayload.setName( adminEntity.getName() );
        adminEntityPayload.setUserName( adminEntity.getUserName() );
        adminEntityPayload.setEmailId( adminEntity.getEmailId() );
        adminEntityPayload.setPassword( adminEntity.getPassword() );
        adminEntityPayload.setPhoneNo( adminEntity.getPhoneNo() );
        adminEntityPayload.setRole( adminEntity.getRole() );
        adminEntityPayload.setOtp( adminEntity.getOtp() );

        return adminEntityPayload;
    }

    @Override
    public AdminEntity entityToResponse(AdminResponse response) {
        if ( response == null ) {
            return null;
        }

        AdminEntity adminEntity = new AdminEntity();

        adminEntity.setAdminId( response.getAdminId() );
        adminEntity.setName( response.getName() );
        adminEntity.setUserName( response.getUserName() );
        adminEntity.setEmailId( response.getEmailId() );
        adminEntity.setPassword( response.getPassword() );
        adminEntity.setPhoneNo( response.getPhoneNo() );
        adminEntity.setRole( response.getRole() );
        adminEntity.setOtp( response.getOtp() );

        return adminEntity;
    }

    @Override
    public AdminEntity updateEntityFromRequest(AdminUpdateRequest adminUpdateRequest, AdminEntity adminEntity) {
        if ( adminUpdateRequest == null ) {
            return adminEntity;
        }

        adminEntity.setName( adminUpdateRequest.getName() );
        adminEntity.setUserName( adminUpdateRequest.getUserName() );
        adminEntity.setEmailId( adminUpdateRequest.getEmailId() );
        adminEntity.setPassword( adminUpdateRequest.getPassword() );
        adminEntity.setPhoneNo( adminUpdateRequest.getPhoneNo() );
        adminEntity.setRole( adminUpdateRequest.getRole() );
        adminEntity.setOtp( adminUpdateRequest.getOtp() );

        return adminEntity;
    }
}
