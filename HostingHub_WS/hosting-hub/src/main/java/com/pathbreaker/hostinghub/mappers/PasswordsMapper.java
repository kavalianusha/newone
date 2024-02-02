package com.pathbreaker.hostinghub.mappers;

import com.pathbreaker.hostinghub.entity.DomainEntity;
import com.pathbreaker.hostinghub.entity.HostingEntity;
import com.pathbreaker.hostinghub.entity.ItReturnsEntity;
import com.pathbreaker.hostinghub.entity.PasswordsEntity;
import com.pathbreaker.hostinghub.request.PasswordRequest;
import com.pathbreaker.hostinghub.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PasswordsMapper {

    @Mappings({
            @Mapping(target = "passwordId", source = "entities.passwordId"),
            @Mapping(target = "userName", source = "entities.userName"),
            @Mapping(target = "password", source = "entities.password"),
            @Mapping(target = "registrationDate", source = "entities.registrationDate"),
            @Mapping(target = "expiryDate", source = "entities.expiryDate"),
            @Mapping(target = "daysLeft", source = "entities.daysLeft")
    })
    List<PasswordsResponse> entitiesToResponses(List<PasswordsEntity> entities);

    /*@Mappings({
            @Mapping(target = "passwordId", source = "entity.passwordId"),
            @Mapping(target = "userName", source = "entity.userName"),
            @Mapping(target = "password", source = "entity.password"),
            @Mapping(target = "registrationDate", source = "entity.registrationDate"),
            @Mapping(target = "expiryDate", source = "entity.expiryDate"),
            @Mapping(target = "daysLeft", source = "entity.daysLeft"),
            @Mapping(target = "domainEntity", source = "entity.domainEntity"),
            @Mapping(target = "hostingEntity", source = "entity.hostingEntity"),
            @Mapping(target = "itReturnsEntity", source = "entity.itReturnsEntity")
    })*/
    PasswordsResponseView entityToResponse(PasswordsEntity entity);

    PasswordsResponse responseToEntity(PasswordsEntity entity);


    PasswordsEntity entityToRequest(PasswordRequest passwordRequest);




    DomainResponseView responseToDomainView(DomainEntity domains);

    HostingResponseView responseToHostingView(HostingEntity hostings);

    ItReturnsResponseView repsonseToItReturnsView(ItReturnsEntity itReturns);


}
