package com.pathbreaker.pmp.mappers;

import com.pathbreaker.pmp.entity.ResourceEntity;
import com.pathbreaker.pmp.entity.ResourceSkillsEntity;
import com.pathbreaker.pmp.request.ResourceRequest;
import com.pathbreaker.pmp.request.ResourceSkillsRequest;
import com.pathbreaker.pmp.request.ResourceSkillsUpdateRequest;
import com.pathbreaker.pmp.response.ResourceResponse;
import com.pathbreaker.pmp.response.ResourceSkillsResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-23T19:17:57+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class ResourceSkillsMapperImpl implements ResourceSkillsMapper {

    @Override
    public ResourceSkillsEntity entityToRequest(ResourceSkillsRequest resourceSkillsRequest) {
        if ( resourceSkillsRequest == null ) {
            return null;
        }

        ResourceSkillsEntity resourceSkillsEntity = new ResourceSkillsEntity();

        resourceSkillsEntity.setId( resourceSkillsRequest.getId() );
        resourceSkillsEntity.setPrimarySkills( resourceSkillsRequest.getPrimarySkills() );
        resourceSkillsEntity.setSecondarySkills( resourceSkillsRequest.getSecondarySkills() );
        resourceSkillsEntity.setTerinarySkills( resourceSkillsRequest.getTerinarySkills() );
        resourceSkillsEntity.setSkillsPercentage( resourceSkillsRequest.getSkillsPercentage() );
        resourceSkillsEntity.setResourceEntity( resourceRequestToResourceEntity( resourceSkillsRequest.getResourceRequest() ) );

        return resourceSkillsEntity;
    }

    @Override
    public ResourceSkillsResponse responseSkillToEntity(ResourceSkillsEntity resourceSkillsEntities) {
        if ( resourceSkillsEntities == null ) {
            return null;
        }

        ResourceSkillsResponse resourceSkillsResponse = new ResourceSkillsResponse();

        resourceSkillsResponse.setId( resourceSkillsEntities.getId() );
        resourceSkillsResponse.setPrimarySkills( resourceSkillsEntities.getPrimarySkills() );
        resourceSkillsResponse.setSecondarySkills( resourceSkillsEntities.getSecondarySkills() );
        resourceSkillsResponse.setTerinarySkills( resourceSkillsEntities.getTerinarySkills() );
        resourceSkillsResponse.setSkillsPercentage( resourceSkillsEntities.getSkillsPercentage() );
        resourceSkillsResponse.setResourceResponse( resourceEntityToResourceResponse( resourceSkillsEntities.getResourceEntity() ) );

        return resourceSkillsResponse;
    }

    @Override
    public ResourceSkillsEntity updateEntityFromRequest(ResourceSkillsUpdateRequest resourceSkillsUpdateRequest, ResourceSkillsEntity resourceSkillsEntity) {
        if ( resourceSkillsUpdateRequest == null ) {
            return resourceSkillsEntity;
        }

        resourceSkillsEntity.setPrimarySkills( resourceSkillsUpdateRequest.getPrimarySkills() );
        resourceSkillsEntity.setSecondarySkills( resourceSkillsUpdateRequest.getSecondarySkills() );
        resourceSkillsEntity.setTerinarySkills( resourceSkillsUpdateRequest.getTerinarySkills() );
        resourceSkillsEntity.setSkillsPercentage( resourceSkillsUpdateRequest.getSkillsPercentage() );

        return resourceSkillsEntity;
    }

    protected ResourceEntity resourceRequestToResourceEntity(ResourceRequest resourceRequest) {
        if ( resourceRequest == null ) {
            return null;
        }

        ResourceEntity resourceEntity = new ResourceEntity();

        resourceEntity.setEmployeeId( resourceRequest.getEmployeeId() );
        resourceEntity.setEmployeeName( resourceRequest.getEmployeeName() );
        resourceEntity.setExperience( resourceRequest.getExperience() );
        resourceEntity.setDesignation( resourceRequest.getDesignation() );
        resourceEntity.setTechnology( resourceRequest.getTechnology() );
        resourceEntity.setDepartment( resourceRequest.getDepartment() );
        resourceEntity.setManager( resourceRequest.getManager() );
        resourceEntity.setProject( resourceRequest.getProject() );

        return resourceEntity;
    }

    protected ResourceResponse resourceEntityToResourceResponse(ResourceEntity resourceEntity) {
        if ( resourceEntity == null ) {
            return null;
        }

        ResourceResponse resourceResponse = new ResourceResponse();

        resourceResponse.setEmployeeId( resourceEntity.getEmployeeId() );
        resourceResponse.setEmployeeName( resourceEntity.getEmployeeName() );
        resourceResponse.setExperience( resourceEntity.getExperience() );
        resourceResponse.setDesignation( resourceEntity.getDesignation() );
        resourceResponse.setTechnology( resourceEntity.getTechnology() );
        resourceResponse.setDepartment( resourceEntity.getDepartment() );
        resourceResponse.setManager( resourceEntity.getManager() );
        resourceResponse.setProject( resourceEntity.getProject() );

        return resourceResponse;
    }
}
