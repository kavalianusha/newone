package com.pathbreaker.authentication.service.mappers;

import com.pathbreaker.authentication.service.entity.AuthenticationDetails;
import com.pathbreaker.authentication.service.request.AuthenticationDetailsPayload;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-11T17:02:51+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class AuthenticationMapperImpl implements AuthenticationMapper {

    @Override
    public AuthenticationDetails requestToEntity(AuthenticationDetailsPayload request) {
        if ( request == null ) {
            return null;
        }

        AuthenticationDetails authenticationDetails = new AuthenticationDetails();

        return authenticationDetails;
    }

    @Override
    public AuthenticationDetailsPayload entityToRequest(AuthenticationDetails loginDetails) {
        if ( loginDetails == null ) {
            return null;
        }

        AuthenticationDetailsPayload authenticationDetailsPayload = new AuthenticationDetailsPayload();

        return authenticationDetailsPayload;
    }
}
