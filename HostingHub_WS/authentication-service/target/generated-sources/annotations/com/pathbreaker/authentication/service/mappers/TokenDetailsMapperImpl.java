package com.pathbreaker.authentication.service.mappers;

import com.pathbreaker.authentication.service.entity.TokenDetails;
import com.pathbreaker.authentication.service.request.TokenDetailsPayload;
import com.pathbreaker.authentication.service.request.TokenDetailsUpdateRequest;
import com.pathbreaker.authentication.service.response.TokenDetailsResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-11T17:02:51+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class TokenDetailsMapperImpl implements TokenDetailsMapper {

    @Override
    public TokenDetailsPayload entityToRequest(TokenDetails tokenDetails) {
        if ( tokenDetails == null ) {
            return null;
        }

        TokenDetailsPayload tokenDetailsPayload = new TokenDetailsPayload();

        tokenDetailsPayload.setClientId( tokenDetails.getClientId() );
        tokenDetailsPayload.setAccessExpirationInMin( tokenDetails.getAccessExpirationInMin() );
        tokenDetailsPayload.setRefreshExpirationInMin( tokenDetails.getRefreshExpirationInMin() );
        if ( tokenDetails.getCreatedDate() != null ) {
            tokenDetailsPayload.setCreatedDate( Date.from( tokenDetails.getCreatedDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        if ( tokenDetails.getLastUpdatedDate() != null ) {
            tokenDetailsPayload.setLastUpdatedDate( Date.from( tokenDetails.getLastUpdatedDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }

        return tokenDetailsPayload;
    }

    @Override
    public TokenDetails requestToEntity(TokenDetailsPayload tokenDetails) {
        if ( tokenDetails == null ) {
            return null;
        }

        TokenDetails tokenDetails1 = new TokenDetails();

        tokenDetails1.setClientId( tokenDetails.getClientId() );
        tokenDetails1.setAccessExpirationInMin( tokenDetails.getAccessExpirationInMin() );
        tokenDetails1.setRefreshExpirationInMin( tokenDetails.getRefreshExpirationInMin() );
        if ( tokenDetails.getCreatedDate() != null ) {
            tokenDetails1.setCreatedDate( LocalDateTime.ofInstant( tokenDetails.getCreatedDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        if ( tokenDetails.getLastUpdatedDate() != null ) {
            tokenDetails1.setLastUpdatedDate( LocalDateTime.ofInstant( tokenDetails.getLastUpdatedDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }

        return tokenDetails1;
    }

    @Override
    public TokenDetails entityToUpdatedRequest(TokenDetailsUpdateRequest tokenDetailsRequest) {
        if ( tokenDetailsRequest == null ) {
            return null;
        }

        TokenDetails tokenDetails = new TokenDetails();

        tokenDetails.setClientId( tokenDetailsRequest.getClientId() );
        tokenDetails.setAccessExpirationInMin( tokenDetailsRequest.getAccessExpirationInMin() );
        tokenDetails.setRefreshExpirationInMin( tokenDetailsRequest.getRefreshExpirationInMin() );
        if ( tokenDetailsRequest.getLastUpdatedDate() != null ) {
            tokenDetails.setLastUpdatedDate( LocalDateTime.ofInstant( tokenDetailsRequest.getLastUpdatedDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }

        return tokenDetails;
    }

    @Override
    public List<TokenDetailsResponse> entitiesToResponses(List<TokenDetails> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TokenDetailsResponse> list = new ArrayList<TokenDetailsResponse>( entities.size() );
        for ( TokenDetails tokenDetails : entities ) {
            list.add( entityToResponse( tokenDetails ) );
        }

        return list;
    }

    @Override
    public TokenDetailsResponse entityToResponse(TokenDetails tokenDetails) {
        if ( tokenDetails == null ) {
            return null;
        }

        TokenDetailsResponse tokenDetailsResponse = new TokenDetailsResponse();

        tokenDetailsResponse.setId( tokenDetails.getId() );
        tokenDetailsResponse.setClientId( tokenDetails.getClientId() );
        tokenDetailsResponse.setAccessExpirationInMin( tokenDetails.getAccessExpirationInMin() );
        tokenDetailsResponse.setRefreshExpirationInMin( tokenDetails.getRefreshExpirationInMin() );
        tokenDetailsResponse.setCreatedDate( tokenDetails.getCreatedDate() );
        tokenDetailsResponse.setLastUpdatedDate( tokenDetails.getLastUpdatedDate() );

        return tokenDetailsResponse;
    }

    @Override
    public TokenDetails requestToUpdateEntity(TokenDetails tokenDetails, TokenDetailsUpdateRequest tokenDetailsUpdateRequest) {
        if ( tokenDetails == null && tokenDetailsUpdateRequest == null ) {
            return null;
        }

        TokenDetails tokenDetails1 = new TokenDetails();

        if ( tokenDetails != null ) {
            tokenDetails1.setId( tokenDetails.getId() );
            tokenDetails1.setCreatedDate( tokenDetails.getCreatedDate() );
        }
        if ( tokenDetailsUpdateRequest != null ) {
            tokenDetails1.setClientId( tokenDetailsUpdateRequest.getClientId() );
            tokenDetails1.setAccessExpirationInMin( tokenDetailsUpdateRequest.getAccessExpirationInMin() );
            tokenDetails1.setRefreshExpirationInMin( tokenDetailsUpdateRequest.getRefreshExpirationInMin() );
            if ( tokenDetailsUpdateRequest.getLastUpdatedDate() != null ) {
                tokenDetails1.setLastUpdatedDate( LocalDateTime.ofInstant( tokenDetailsUpdateRequest.getLastUpdatedDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
            }
        }

        return tokenDetails1;
    }

    @Override
    public TokenDetailsUpdateRequest entityToUpdateRequest(TokenDetails tokenDetails) {
        if ( tokenDetails == null ) {
            return null;
        }

        TokenDetailsUpdateRequest tokenDetailsUpdateRequest = new TokenDetailsUpdateRequest();

        tokenDetailsUpdateRequest.setClientId( tokenDetails.getClientId() );
        tokenDetailsUpdateRequest.setAccessExpirationInMin( tokenDetails.getAccessExpirationInMin() );
        tokenDetailsUpdateRequest.setRefreshExpirationInMin( tokenDetails.getRefreshExpirationInMin() );
        if ( tokenDetails.getLastUpdatedDate() != null ) {
            tokenDetailsUpdateRequest.setLastUpdatedDate( Date.from( tokenDetails.getLastUpdatedDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }

        return tokenDetailsUpdateRequest;
    }
}
