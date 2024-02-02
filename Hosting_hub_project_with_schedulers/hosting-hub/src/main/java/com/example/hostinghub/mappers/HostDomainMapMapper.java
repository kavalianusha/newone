package com.example.hostinghub.mappers;

import com.example.hostinghub.entity.HostDomainMapEntity;
import com.example.hostinghub.request.HostDomainMapRequest;
import com.example.hostinghub.request.HostDomainMapUpdateRequest;
import com.example.hostinghub.response.HostDomainMapResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HostDomainMapMapper {

    HostDomainMapRequest requestToEntity(HostDomainMapEntity domainEntity);

    // Maps a DomainRequest to a DomainEntity
    HostDomainMapEntity entityToRequest(HostDomainMapRequest request);

    // Maps a list of DomainEntities to a list of DomainResponses
    List<HostDomainMapResponse> responseToEntity(List<HostDomainMapEntity> req);

    // Maps a DomainEntity to a DomainResponse
    HostDomainMapResponse entityToResponse(HostDomainMapEntity entity);

    HostDomainMapEntity updateEntityFromRequest(HostDomainMapUpdateRequest hostDomainMapUpdateRequest, @MappingTarget HostDomainMapEntity hostDomainMapEntity);



}