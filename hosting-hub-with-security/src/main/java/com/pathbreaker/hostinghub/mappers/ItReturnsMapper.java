package com.pathbreaker.hostinghub.mappers;

import com.pathbreaker.hostinghub.entity.ItReturnsEntity;
import com.pathbreaker.hostinghub.entity.PasswordsEntity;
import com.pathbreaker.hostinghub.request.ItReturnsRequest;
import com.pathbreaker.hostinghub.request.ItReturnsUpdateRequest;
import com.pathbreaker.hostinghub.response.ItReturnsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface ItReturnsMapper {


    @Mappings({
            @Mapping(target = "customerId", source = "itReturnsRequest.customerId"),
            @Mapping(target = "serviceType", source = "itReturnsRequest.serviceType"),
            @Mapping(target = "registeredEmail", source = "itReturnsRequest.registeredEmail"),
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
            @Mapping(target = "registeredEmail", source = "itReturnsEntityList.registeredEmail"),
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
            @Mapping(target = "registeredEmail", source = "updatedItReturns.registeredEmail"),
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