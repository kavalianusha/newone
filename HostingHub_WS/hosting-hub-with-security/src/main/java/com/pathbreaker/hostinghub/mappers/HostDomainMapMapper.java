package com.pathbreaker.hostinghub.mappers;

import com.pathbreaker.hostinghub.entity.HostDomainMapEntity;
import com.pathbreaker.hostinghub.request.HostDomainMapRequest;
import com.pathbreaker.hostinghub.request.HostDomainMapUpdateRequest;
import com.pathbreaker.hostinghub.response.HostDomainMapResponse;
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