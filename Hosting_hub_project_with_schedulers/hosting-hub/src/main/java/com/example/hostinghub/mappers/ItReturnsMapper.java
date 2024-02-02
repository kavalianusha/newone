package com.example.hostinghub.mappers;

import java.util.List;

import com.example.hostinghub.entity.ItReturnsEntity;
import com.example.hostinghub.entity.PasswordsEntity;
import com.example.hostinghub.request.HostingUpdateRequest;
import com.example.hostinghub.request.ItReturnsRequest;
import com.example.hostinghub.request.ItReturnsUpdateRequest;
import com.example.hostinghub.response.ItReturnsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface ItReturnsMapper {


    @Mappings({
            @Mapping(target = "customerId", source = "itReturnsRequest.customerId"),
            @Mapping(target = "serviceType", source = "itReturnsRequest.serviceType"),
            @Mapping(target = "emailId", source = "itReturnsRequest.emailId"),
            @Mapping(target = "registeredMobileNo", source = "itReturnsRequest.registeredMobileNo"),
            @Mapping(target = "registrationDate", source = "itReturnsRequest.registrationDate"),
            @Mapping(target = "expiryDate", source = "itReturnsRequest.expiryDate"),
            @Mapping(target = "loginUrl", source = "itReturnsRequest.loginUrl"),
            @Mapping(target = "userName", source = "itReturnsRequest.userName"),
            @Mapping(target = "password", source = "itReturnsRequest.password"),
            @Mapping(target = "createdBy", source = "itReturnsRequest.createdBy"),
            @Mapping(target = "createdDate", source = "itReturnsRequest.createdDate")
    })
    ItReturnsEntity requestToEntity(ItReturnsRequest itReturnsRequest);

    PasswordsEntity requestToPasswordEntity(ItReturnsRequest itReturnsRequest);

    @Mappings({
            @Mapping(target = "customerId", source = "itReturnsEntityList.customerId"),
            @Mapping(target = "serviceType", source = "itReturnsEntityList.serviceType"),
            @Mapping(target = "emailId", source = "itReturnsEntityList.emailId"),
            @Mapping(target = "registeredMobileNo", source = "itReturnsEntityList.registeredMobileNo"),
            @Mapping(target = "registrationDate", source = "itReturnsEntityList.registrationDate"),
            @Mapping(target = "expiryDate", source = "itReturnsEntityList.expiryDate"),
            @Mapping(target = "loginUrl", source = "itReturnsEntityList.loginUrl"),
            @Mapping(target = "userName", source = "itReturnsEntityList.userName"),
            @Mapping(target = "password", source = "itReturnsEntityList.password"),
            @Mapping(target = "passwordsResponse", source = "itReturnsEntityList.passwordsEntity")
    })
    ItReturnsResponse responseToEntity(ItReturnsEntity itReturnsEntityList);

    @Mappings({
            @Mapping(target = "serviceType", source = "updatedItReturns.serviceType"),
            @Mapping(target = "emailId", source = "updatedItReturns.emailId"),
            @Mapping(target = "registeredMobileNo", source = "updatedItReturns.registeredMobileNo"),
            @Mapping(target = "registrationDate", source = "updatedItReturns.registrationDate"),
            @Mapping(target = "expiryDate", source = "updatedItReturns.expiryDate"),
            @Mapping(target = "loginUrl", source = "updatedItReturns.loginUrl"),
            @Mapping(target = "userName", source = "updatedItReturns.userName"),
            @Mapping(target = "password", source = "updatedItReturns.password"),
            @Mapping(target = "daysLeft", source = "updatedItReturns.daysLeft"),
            @Mapping(target = "createdBy",source = "updatedItReturns.createdBy"),
            @Mapping(target = "passwordsEntity", source = "updatedItReturns.passwordUpdateRequest")

    })
    ItReturnsEntity updateEntityFromRequest(ItReturnsUpdateRequest updatedItReturns, ItReturnsEntity existingItReturns);

    @Mappings({
            @Mapping(target = "userName", source = "updatedItReturns.userName"),
            @Mapping(target = "password", source = "updatedItReturns.password"),
            @Mapping(target = "registrationDate", source = "updatedItReturns.registrationDate"),
            @Mapping(target = "expiryDate", source = "updatedItReturns.expiryDate"),
            @Mapping(target = "daysLeft", source = "updatedItReturns.daysLeft")
    })
    PasswordsEntity requestToUpdatePasswordEntity(ItReturnsUpdateRequest updatedItReturns);
}