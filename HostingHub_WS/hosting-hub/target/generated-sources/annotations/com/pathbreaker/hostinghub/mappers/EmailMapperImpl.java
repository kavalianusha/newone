package com.pathbreaker.hostinghub.mappers;

import com.pathbreaker.hostinghub.entity.EmailEntity;
import com.pathbreaker.hostinghub.request.EmailRequest;
import com.pathbreaker.hostinghub.request.EmailUpdateRequest;
import com.pathbreaker.hostinghub.response.EmailResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-18T17:15:08+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class EmailMapperImpl implements EmailMapper {

    @Override
    public EmailRequest requestToEntity(EmailEntity emailEntity) {
        if ( emailEntity == null ) {
            return null;
        }

        EmailRequest emailRequest = new EmailRequest();

        emailRequest.setEmailId( emailEntity.getEmailId() );
        emailRequest.setEmail( emailEntity.getEmail() );
        emailRequest.setUsername( emailEntity.getUsername() );
        emailRequest.setPassword( emailEntity.getPassword() );
        emailRequest.setDomainName( emailEntity.getDomainName() );

        return emailRequest;
    }

    @Override
    public EmailEntity entityToRequest(EmailRequest request) {
        if ( request == null ) {
            return null;
        }

        EmailEntity emailEntity = new EmailEntity();

        emailEntity.setEmailId( request.getEmailId() );
        emailEntity.setEmail( request.getEmail() );
        emailEntity.setUsername( request.getUsername() );
        emailEntity.setPassword( request.getPassword() );
        emailEntity.setDomainName( request.getDomainName() );

        return emailEntity;
    }

    @Override
    public List<EmailResponse> responseToEntity(List<EmailEntity> req) {
        if ( req == null ) {
            return null;
        }

        List<EmailResponse> list = new ArrayList<EmailResponse>( req.size() );
        for ( EmailEntity emailEntity : req ) {
            list.add( entityToResponse( emailEntity ) );
        }

        return list;
    }

    @Override
    public EmailResponse entityToResponse(EmailEntity emailEntity) {
        if ( emailEntity == null ) {
            return null;
        }

        EmailResponse emailResponse = new EmailResponse();

        emailResponse.setEmailId( emailEntity.getEmailId() );
        emailResponse.setEmail( emailEntity.getEmail() );
        emailResponse.setUsername( emailEntity.getUsername() );
        emailResponse.setPassword( emailEntity.getPassword() );
        emailResponse.setDomainName( emailEntity.getDomainName() );

        return emailResponse;
    }

    @Override
    public EmailEntity updateEntityFromRequest(EmailUpdateRequest emailUpdateRequest, EmailEntity emailEntity) {
        if ( emailUpdateRequest == null ) {
            return emailEntity;
        }

        emailEntity.setEmail( emailUpdateRequest.getEmail() );
        emailEntity.setUsername( emailUpdateRequest.getUsername() );
        emailEntity.setPassword( emailUpdateRequest.getPassword() );
        emailEntity.setDomainName( emailUpdateRequest.getDomainName() );

        return emailEntity;
    }
}
