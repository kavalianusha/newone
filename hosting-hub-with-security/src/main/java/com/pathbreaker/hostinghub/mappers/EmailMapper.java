package com.pathbreaker.hostinghub.mappers;

import java.util.List;

import com.pathbreaker.hostinghub.request.EmailUpdateRequest;
import com.pathbreaker.hostinghub.response.EmailResponse;
import org.mapstruct.Mapper;

import com.pathbreaker.hostinghub.entity.EmailEntity;
import com.pathbreaker.hostinghub.request.EmailRequest;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring") // Marks this interface as a MapStruct mapper and specifies component model

public interface EmailMapper {

    // Maps an EmailEntity to an EmailRequest
    EmailRequest requestToEntity(EmailEntity emailEntity);

    // Maps an EmailRequest to an EmailEntity
    EmailEntity entityToRequest(EmailRequest request);

    // Maps a list of EmailEntities to a list of EmailResponses
    List<EmailResponse> responseToEntity(List<EmailEntity> req);

    // Maps an EmailResponse to an EmailEntity
    EmailResponse entityToResponse(EmailEntity emailEntity);
    EmailEntity updateEntityFromRequest(EmailUpdateRequest emailUpdateRequest, @MappingTarget EmailEntity emailEntity);





}
