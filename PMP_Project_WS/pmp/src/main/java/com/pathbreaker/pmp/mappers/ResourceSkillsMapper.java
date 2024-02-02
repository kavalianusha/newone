package com.pathbreaker.pmp.mappers;

import com.pathbreaker.pmp.entity.ResourceEntity;
import com.pathbreaker.pmp.entity.ResourceSkillsEntity;
import com.pathbreaker.pmp.request.ResourceSkillsRequest;
import com.pathbreaker.pmp.request.ResourceSkillsUpdateRequest;
import com.pathbreaker.pmp.response.ResourceSkillsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResourceSkillsMapper {

    @Mappings({
            @Mapping(target = "id", source = "resourceSkillsRequest.id"),
            @Mapping(target = "primarySkills", source = "resourceSkillsRequest.primarySkills"),
            @Mapping(target = "secondarySkills", source = "resourceSkillsRequest.secondarySkills"),
            @Mapping(target = "terinarySkills", source = "resourceSkillsRequest.terinarySkills"),
            @Mapping(target = "skillsPercentage", source = "resourceSkillsRequest.skillsPercentage"),
            @Mapping(target = "resourceEntity", source = "resourceSkillsRequest.resourceRequest")
    })
    ResourceSkillsEntity entityToRequest(ResourceSkillsRequest resourceSkillsRequest);

    @Mappings({
            @Mapping(target = "id", source = "resourceSkillsEntities.id"),
            @Mapping(target = "primarySkills", source = "resourceSkillsEntities.primarySkills"),
            @Mapping(target = "secondarySkills", source = "resourceSkillsEntities.secondarySkills"),
            @Mapping(target = "terinarySkills", source = "resourceSkillsEntities.terinarySkills"),
            @Mapping(target = "skillsPercentage", source = "resourceSkillsEntities.skillsPercentage"),
            @Mapping(target = "resourceResponse", source = "resourceSkillsEntities.resourceEntity")
    })
    ResourceSkillsResponse responseSkillToEntity(ResourceSkillsEntity resourceSkillsEntities);


    ResourceSkillsEntity updateEntityFromRequest(ResourceSkillsUpdateRequest resourceSkillsUpdateRequest, @MappingTarget ResourceSkillsEntity resourceSkillsEntity);

}
