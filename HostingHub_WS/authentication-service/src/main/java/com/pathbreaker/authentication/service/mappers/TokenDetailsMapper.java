package com.pathbreaker.authentication.service.mappers;

import com.pathbreaker.authentication.service.entity.TokenDetails;
import com.pathbreaker.authentication.service.request.TokenDetailsPayload;
import com.pathbreaker.authentication.service.request.TokenDetailsUpdateRequest;
import com.pathbreaker.authentication.service.response.TokenDetailsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TokenDetailsMapper {

    TokenDetailsPayload  entityToRequest(TokenDetails tokenDetails);

    TokenDetails requestToEntity(TokenDetailsPayload tokenDetails);


    TokenDetails entityToUpdatedRequest(TokenDetailsUpdateRequest tokenDetailsRequest);

    List<TokenDetailsResponse> entitiesToResponses(List<TokenDetails> entities);


    TokenDetailsResponse entityToResponse(TokenDetails tokenDetails);

    @Mappings({
            @Mapping(target = "clientId", source = "tokenDetailsUpdateRequest.clientId"),
            @Mapping(target = "accessExpirationInMin", source = "tokenDetailsUpdateRequest.accessExpirationInMin"),
            @Mapping(target = "refreshExpirationInMin",source = "tokenDetailsUpdateRequest.refreshExpirationInMin"),
            @Mapping(target = "lastUpdatedDate",source = "tokenDetailsUpdateRequest.lastUpdatedDate"),
    })
    TokenDetails requestToUpdateEntity(TokenDetails tokenDetails,TokenDetailsUpdateRequest tokenDetailsUpdateRequest);

    TokenDetailsUpdateRequest entityToUpdateRequest(TokenDetails tokenDetails);
}
