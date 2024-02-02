package com.pathbreaker.authentication.service.mappers;

import com.pathbreaker.authentication.service.entity.AuthenticationDetails;
import com.pathbreaker.authentication.service.request.AuthenticationDetailsPayload;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {


        AuthenticationDetails requestToEntity(AuthenticationDetailsPayload request);

     AuthenticationDetailsPayload entityToRequest(AuthenticationDetails loginDetails);


}
