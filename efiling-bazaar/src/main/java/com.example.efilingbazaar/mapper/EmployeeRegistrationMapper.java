
package com.example.efilingbazaar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import com.example.efilingbazaar.entity.EmployeeRegistrationEntity;
import com.example.efilingbazaar.entity.LoginEntity;
import com.example.efilingbazaar.entity.OtpEntity;
import com.example.efilingbazaar.entity.RoleEntity;
import com.example.efilingbazaar.request.EmployeeRegistrationRequest;
import com.example.efilingbazaar.response.EmployeeRegistrationResponse;
@Component
@Mapper(componentModel = "spring")
public interface EmployeeRegistrationMapper {

    EmployeeRegistrationRequest entityToRequest(EmployeeRegistrationEntity entity);
    EmployeeRegistrationEntity requestToEntity(EmployeeRegistrationRequest request);
    void updateEntityToRequest(EmployeeRegistrationEntity updatedRegistration, @MappingTarget EmployeeRegistrationEntity entity);
    LoginEntity entityToLoginEntity(EmployeeRegistrationEntity registration);
    RoleEntity entityToRoleEntity(EmployeeRegistrationEntity registration);
    OtpEntity entityToOtpEntity(EmployeeRegistrationEntity registration);
    EmployeeRegistrationResponse mapEntityToResponse(EmployeeRegistrationEntity entity);
    EmployeeRegistrationEntity updateEntityToRequest(EmployeeRegistrationEntity updatedRegistration);
    EmployeeRegistrationResponse convertToResponseEmployeeDTO(EmployeeRegistrationEntity mockEmployee);





}


