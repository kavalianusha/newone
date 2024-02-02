package com.pathbreaker.pmp.mappers;

import com.pathbreaker.pmp.entity.ResourceEntity;
import com.pathbreaker.pmp.request.ResourceRequest;
import com.pathbreaker.pmp.request.ResourceSkillsUpdateRequest;
import com.pathbreaker.pmp.request.ResourceUpdateRequest;
import com.pathbreaker.pmp.response.ResourceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ResourceMapper {

    ResourceRequest requestToEntity(ResourceEntity resourceTable);

    @Mappings({
            @Mapping(target = "employeeId", source = "resourceRequest.employeeId"),
            @Mapping(target = "employeeName", source = "resourceRequest.employeeName"),
            @Mapping(target = "experience", source = "resourceRequest.experience"),
            @Mapping(target = "technology", source = "resourceRequest.technology"),
            @Mapping(target = "department", source = "resourceRequest.department"),
            @Mapping(target = "manager", source = "resourceRequest.manager"),
            @Mapping(target = "project", source = "resourceRequest.project"),
            @Mapping(target = "resourceSkillsEntity", source = "resourceRequest.resourceSkillsRequest")
    })
    ResourceEntity entityToRequest(ResourceRequest resourceRequest);

    @Mappings({
            @Mapping(target = "employeeId", source = "resourceEntityList.employeeId"),
            @Mapping(target = "employeeName", source = "resourceEntityList.employeeName"),
            @Mapping(target = "experience", source = "resourceEntityList.experience"),
            @Mapping(target = "technology", source = "resourceEntityList.technology"),
            @Mapping(target = "department", source = "resourceEntityList.department"),
            @Mapping(target = "manager", source = "resourceEntityList.manager"),
            @Mapping(target = "project", source = "resourceEntityList.project"),
            @Mapping(target = "resourceSkillsResponse", source = "resourceEntityList.resourceSkillsEntity"),
    })
    ResourceResponse responseListToEntity(ResourceEntity resourceEntityList);

    @Mappings({
            @Mapping(target = "employeeId", source = "resourceEntity.employeeId"),
            @Mapping(target = "employeeName", source = "resourceEntity.employeeName"),
            @Mapping(target = "experience", source = "resourceEntity.experience"),
            @Mapping(target = "technology", source = "resourceEntity.technology"),
            @Mapping(target = "department", source = "resourceEntity.department"),
            @Mapping(target = "manager", source = "resourceEntity.manager"),
            @Mapping(target = "project", source = "resourceEntity.project"),
            @Mapping(target = "resourceSkillsResponse", source = "resourceEntity.resourceSkillsEntity"),
    })
    ResourceResponse responseToEntity(ResourceEntity resourceEntity);

    ResourceEntity updateEntityFromRequest(ResourceUpdateRequest resourceUpdateRequest, @MappingTarget ResourceEntity resourceEntity);

}
