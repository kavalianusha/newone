package com.pathbreaker.hostinghub.mappers;

import com.pathbreaker.hostinghub.entity.HostDomainMapEntity;
import com.pathbreaker.hostinghub.entity.HostingEntity;
import com.pathbreaker.hostinghub.request.HostDomainMapRequest;
import com.pathbreaker.hostinghub.request.HostDomainMapUpdateRequest;
import com.pathbreaker.hostinghub.response.HostDomainMapResponse;
import com.pathbreaker.hostinghub.response.HostingResponseView;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HostDomainMapMapper {

    HostDomainMapRequest requestToEntity(HostDomainMapEntity domainEntity);

    @Mappings({
            @Mapping(target = "hostDomainId", source = "request.hostDomainId"),
            @Mapping(target = "domainName", source = "request.domainName"),
            @Mapping(target = "hostProvider",source = "request.hostProvider"),
            @Mapping(target = "userName", source = "request.userName"),
            @Mapping(target = "password", source = "request.password"),
            @Mapping(target = "payment", source = "request.payment"),
            @Mapping(target = "domainProvider", source = "request.domainProvider"),
            @Mapping(target = "hostUserName",source = "request.hostUserName"),
            @Mapping(target = "url1", source = "request.url1"),
            @Mapping(target = "hostPassword",source = "request.hostPassword"),
    })
    // Maps a DomainRequest to a DomainEntity
    HostDomainMapEntity entityToRequest(HostDomainMapRequest request);

    // Maps a list of DomainEntities to a list of DomainResponses
    List<HostDomainMapResponse> responseToEntity(List<HostDomainMapEntity> req);

    // Maps a DomainEntity to a DomainResponse
    HostDomainMapResponse entityToResponse(HostDomainMapEntity entity);

    HostDomainMapEntity updateEntityFromRequest(HostDomainMapUpdateRequest hostDomainMapUpdateRequest, @MappingTarget HostDomainMapEntity hostDomainMapEntity);


    HostingResponseView responseToView(HostingEntity hostingEntity);
}