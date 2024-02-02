package com.example.efilingbazaar.mapper;

import com.example.efilingbazaar.entity.EmployeeRegistrationEntity;
import com.example.efilingbazaar.entity.LoginEntity;
import com.example.efilingbazaar.entity.OtpEntity;
import com.example.efilingbazaar.entity.RoleEntity;
import com.example.efilingbazaar.request.EmployeeRegistrationRequest;
import com.example.efilingbazaar.response.EmployeeRegistrationResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-05T11:43:22+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class EmployeeRegistrationMapperImpl implements EmployeeRegistrationMapper {

    @Override
    public EmployeeRegistrationRequest entityToRequest(EmployeeRegistrationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeRegistrationRequest employeeRegistrationRequest = new EmployeeRegistrationRequest();

        employeeRegistrationRequest.setEmployeeId( entity.getEmployeeId() );
        employeeRegistrationRequest.setFirstName( entity.getFirstName() );
        employeeRegistrationRequest.setMiddleName( entity.getMiddleName() );
        employeeRegistrationRequest.setLastName( entity.getLastName() );
        employeeRegistrationRequest.setAddress( entity.getAddress() );
        employeeRegistrationRequest.setIdProof( entity.getIdProof() );
        employeeRegistrationRequest.setContactNumber( entity.getContactNumber() );
        employeeRegistrationRequest.setEmailId( entity.getEmailId() );
        employeeRegistrationRequest.setPassword( entity.getPassword() );
        employeeRegistrationRequest.setQualification( entity.getQualification() );
        employeeRegistrationRequest.setSkillset( entity.getSkillset() );
        employeeRegistrationRequest.setRoleName( entity.getRoleName() );
        employeeRegistrationRequest.setStatus( entity.isStatus() );

        return employeeRegistrationRequest;
    }

    @Override
    public EmployeeRegistrationEntity requestToEntity(EmployeeRegistrationRequest request) {
        if ( request == null ) {
            return null;
        }

        EmployeeRegistrationEntity employeeRegistrationEntity = new EmployeeRegistrationEntity();

        employeeRegistrationEntity.setRoleName( request.getRoleName() );
        employeeRegistrationEntity.setEmployeeId( request.getEmployeeId() );
        employeeRegistrationEntity.setFirstName( request.getFirstName() );
        employeeRegistrationEntity.setMiddleName( request.getMiddleName() );
        employeeRegistrationEntity.setLastName( request.getLastName() );
        employeeRegistrationEntity.setAddress( request.getAddress() );
        employeeRegistrationEntity.setIdProof( request.getIdProof() );
        employeeRegistrationEntity.setContactNumber( request.getContactNumber() );
        employeeRegistrationEntity.setEmailId( request.getEmailId() );
        employeeRegistrationEntity.setPassword( request.getPassword() );
        employeeRegistrationEntity.setQualification( request.getQualification() );
        employeeRegistrationEntity.setSkillset( request.getSkillset() );
        employeeRegistrationEntity.setStatus( request.isStatus() );

        return employeeRegistrationEntity;
    }

    @Override
    public void updateEntityToRequest(EmployeeRegistrationEntity updatedRegistration, EmployeeRegistrationEntity entity) {
        if ( updatedRegistration == null ) {
            return;
        }

        entity.setRoleName( updatedRegistration.getRoleName() );
        entity.setEmployeeId( updatedRegistration.getEmployeeId() );
        entity.setFirstName( updatedRegistration.getFirstName() );
        entity.setMiddleName( updatedRegistration.getMiddleName() );
        entity.setLastName( updatedRegistration.getLastName() );
        entity.setAddress( updatedRegistration.getAddress() );
        entity.setIdProof( updatedRegistration.getIdProof() );
        entity.setContactNumber( updatedRegistration.getContactNumber() );
        entity.setEmailId( updatedRegistration.getEmailId() );
        entity.setPassword( updatedRegistration.getPassword() );
        entity.setQualification( updatedRegistration.getQualification() );
        entity.setSkillset( updatedRegistration.getSkillset() );
        entity.setStatus( updatedRegistration.isStatus() );
    }

    @Override
    public LoginEntity entityToLoginEntity(EmployeeRegistrationEntity registration) {
        if ( registration == null ) {
            return null;
        }

        LoginEntity loginEntity = new LoginEntity();

        loginEntity.setEmployeeId( registration.getEmployeeId() );
        loginEntity.setEmailId( registration.getEmailId() );
        loginEntity.setPassword( registration.getPassword() );
        loginEntity.setRoleName( registration.getRoleName() );
        loginEntity.setStatus( registration.isStatus() );

        return loginEntity;
    }

    @Override
    public RoleEntity entityToRoleEntity(EmployeeRegistrationEntity registration) {
        if ( registration == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setRoleName( registration.getRoleName() );

        return roleEntity;
    }

    @Override
    public OtpEntity entityToOtpEntity(EmployeeRegistrationEntity registration) {
        if ( registration == null ) {
            return null;
        }

        OtpEntity otpEntity = new OtpEntity();

        otpEntity.setEmailId( registration.getEmailId() );

        return otpEntity;
    }

    @Override
    public EmployeeRegistrationResponse mapEntityToResponse(EmployeeRegistrationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        EmployeeRegistrationResponse employeeRegistrationResponse = new EmployeeRegistrationResponse();

        employeeRegistrationResponse.setEmployeeId( entity.getEmployeeId() );
        employeeRegistrationResponse.setFirstName( entity.getFirstName() );
        employeeRegistrationResponse.setMiddleName( entity.getMiddleName() );
        employeeRegistrationResponse.setLastName( entity.getLastName() );
        employeeRegistrationResponse.setAddress( entity.getAddress() );
        employeeRegistrationResponse.setIdProof( entity.getIdProof() );
        employeeRegistrationResponse.setContactNumber( entity.getContactNumber() );
        employeeRegistrationResponse.setEmailId( entity.getEmailId() );
        employeeRegistrationResponse.setPassword( entity.getPassword() );
        employeeRegistrationResponse.setQualification( entity.getQualification() );
        employeeRegistrationResponse.setSkillset( entity.getSkillset() );
        employeeRegistrationResponse.setRoleName( entity.getRoleName() );
        employeeRegistrationResponse.setStatus( entity.isStatus() );

        return employeeRegistrationResponse;
    }

    @Override
    public EmployeeRegistrationEntity updateEntityToRequest(EmployeeRegistrationEntity updatedRegistration) {
        if ( updatedRegistration == null ) {
            return null;
        }

        EmployeeRegistrationEntity employeeRegistrationEntity = new EmployeeRegistrationEntity();

        employeeRegistrationEntity.setRoleName( updatedRegistration.getRoleName() );
        employeeRegistrationEntity.setEmployeeId( updatedRegistration.getEmployeeId() );
        employeeRegistrationEntity.setFirstName( updatedRegistration.getFirstName() );
        employeeRegistrationEntity.setMiddleName( updatedRegistration.getMiddleName() );
        employeeRegistrationEntity.setLastName( updatedRegistration.getLastName() );
        employeeRegistrationEntity.setAddress( updatedRegistration.getAddress() );
        employeeRegistrationEntity.setIdProof( updatedRegistration.getIdProof() );
        employeeRegistrationEntity.setContactNumber( updatedRegistration.getContactNumber() );
        employeeRegistrationEntity.setEmailId( updatedRegistration.getEmailId() );
        employeeRegistrationEntity.setPassword( updatedRegistration.getPassword() );
        employeeRegistrationEntity.setQualification( updatedRegistration.getQualification() );
        employeeRegistrationEntity.setSkillset( updatedRegistration.getSkillset() );
        employeeRegistrationEntity.setStatus( updatedRegistration.isStatus() );

        return employeeRegistrationEntity;
    }

    @Override
    public EmployeeRegistrationResponse convertToResponseEmployeeDTO(EmployeeRegistrationEntity mockEmployee) {
        if ( mockEmployee == null ) {
            return null;
        }

        EmployeeRegistrationResponse employeeRegistrationResponse = new EmployeeRegistrationResponse();

        employeeRegistrationResponse.setEmployeeId( mockEmployee.getEmployeeId() );
        employeeRegistrationResponse.setFirstName( mockEmployee.getFirstName() );
        employeeRegistrationResponse.setMiddleName( mockEmployee.getMiddleName() );
        employeeRegistrationResponse.setLastName( mockEmployee.getLastName() );
        employeeRegistrationResponse.setAddress( mockEmployee.getAddress() );
        employeeRegistrationResponse.setIdProof( mockEmployee.getIdProof() );
        employeeRegistrationResponse.setContactNumber( mockEmployee.getContactNumber() );
        employeeRegistrationResponse.setEmailId( mockEmployee.getEmailId() );
        employeeRegistrationResponse.setPassword( mockEmployee.getPassword() );
        employeeRegistrationResponse.setQualification( mockEmployee.getQualification() );
        employeeRegistrationResponse.setSkillset( mockEmployee.getSkillset() );
        employeeRegistrationResponse.setRoleName( mockEmployee.getRoleName() );
        employeeRegistrationResponse.setStatus( mockEmployee.isStatus() );

        return employeeRegistrationResponse;
    }
}
