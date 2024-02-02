package com.pathbreaker.pmp.mappers;

import com.pathbreaker.pmp.entity.ResourceEntity;
import com.pathbreaker.pmp.entity.ResourceSkillsEntity;
import com.pathbreaker.pmp.request.ResourceRequest;
import com.pathbreaker.pmp.request.ResourceSkillsRequest;
import com.pathbreaker.pmp.request.ResourceUpdateRequest;
import com.pathbreaker.pmp.response.ResourceResponse;
import com.pathbreaker.pmp.response.ResourceSkillsResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-23T19:17:56+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class ResourceMapperImpl implements ResourceMapper {

    @Override
    public ResourceRequest requestToEntity(ResourceEntity resourceTable) {
        if ( resourceTable == null ) {
            return null;
        }

        ResourceRequest resourceRequest = new ResourceRequest();

        resourceRequest.setEmployeeId( resourceTable.getEmployeeId() );
        resourceRequest.setEmployeeName( resourceTable.getEmployeeName() );
        resourceRequest.setExperience( resourceTable.getExperience() );
        resourceRequest.setDesignation( resourceTable.getDesignation() );
        resourceRequest.setTechnology( resourceTable.getTechnology() );
        resourceRequest.setDepartment( resourceTable.getDepartment() );
        resourceRequest.setManager( resourceTable.getManager() );
        resourceRequest.setProject( resourceTable.getProject() );

        return resourceRequest;
    }

    @Override
    public ResourceEntity entityToRequest(ResourceRequest resourceRequest) {
        if ( resourceRequest == null ) {
            return null;
        }

        ResourceEntity resourceEntity = new ResourceEntity();

        resourceEntity.setEmployeeId( resourceRequest.getEmployeeId() );
        resourceEntity.setEmployeeName( resourceRequest.getEmployeeName() );
        resourceEntity.setExperience( resourceRequest.getExperience() );
        resourceEntity.setTechnology( resourceRequest.getTechnology() );
        resourceEntity.setDepartment( resourceRequest.getDepartment() );
        resourceEntity.setManager( resourceRequest.getManager() );
        resourceEntity.setProject( resourceRequest.getProject() );
        resourceEntity.setResourceSkillsEntity( resourceSkillsRequestToResourceSkillsEntity( resourceRequest.getResourceSkillsRequest() ) );
        resourceEntity.setDesignation( resourceRequest.getDesignation() );

        return resourceEntity;
    }

    @Override
    public ResourceResponse responseListToEntity(ResourceEntity resourceEntityList) {
        if ( resourceEntityList == null ) {
            return null;
        }

        ResourceResponse resourceResponse = new ResourceResponse();

        resourceResponse.setEmployeeId( resourceEntityList.getEmployeeId() );
        resourceResponse.setEmployeeName( resourceEntityList.getEmployeeName() );
        resourceResponse.setExperience( resourceEntityList.getExperience() );
        resourceResponse.setTechnology( resourceEntityList.getTechnology() );
        resourceResponse.setDepartment( resourceEntityList.getDepartment() );
        resourceResponse.setManager( resourceEntityList.getManager() );
        resourceResponse.setProject( resourceEntityList.getProject() );
        resourceResponse.setResourceSkillsResponse( resourceSkillsEntityToResourceSkillsResponse( resourceEntityList.getResourceSkillsEntity() ) );
        resourceResponse.setDesignation( resourceEntityList.getDesignation() );

        return resourceResponse;
    }

    @Override
    public ResourceResponse responseToEntity(ResourceEntity resourceEntity) {
        if ( resourceEntity == null ) {
            return null;
        }

        ResourceResponse resourceResponse = new ResourceResponse();

        resourceResponse.setEmployeeId( resourceEntity.getEmployeeId() );
        resourceResponse.setEmployeeName( resourceEntity.getEmployeeName() );
        resourceResponse.setExperience( resourceEntity.getExperience() );
        resourceResponse.setTechnology( resourceEntity.getTechnology() );
        resourceResponse.setDepartment( resourceEntity.getDepartment() );
        resourceResponse.setManager( resourceEntity.getManager() );
        resourceResponse.setProject( resourceEntity.getProject() );
        resourceResponse.setResourceSkillsResponse( resourceSkillsEntityToResourceSkillsResponse( resourceEntity.getResourceSkillsEntity() ) );
        resourceResponse.setDesignation( resourceEntity.getDesignation() );

        return resourceResponse;
    }

    @Override
    public ResourceEntity updateEntityFromRequest(ResourceUpdateRequest resourceUpdateRequest, ResourceEntity resourceEntity) {
        if ( resourceUpdateRequest == null ) {
            return resourceEntity;
        }

        resourceEntity.setEmployeeName( resourceUpdateRequest.getEmployeeName() );
        resourceEntity.setExperience( resourceUpdateRequest.getExperience() );
        resourceEntity.setDesignation( resourceUpdateRequest.getDesignation() );
        resourceEntity.setTechnology( resourceUpdateRequest.getTechnology() );
        resourceEntity.setDepartment( resourceUpdateRequest.getDepartment() );
        resourceEntity.setManager( resourceUpdateRequest.getManager() );
        resourceEntity.setProject( resourceUpdateRequest.getProject() );

        return resourceEntity;
    }

    protected ResourceSkillsEntity resourceSkillsRequestToResourceSkillsEntity(ResourceSkillsRequest resourceSkillsRequest) {
        if ( resourceSkillsRequest == null ) {
            return null;
        }

        ResourceSkillsEntity resourceSkillsEntity = new ResourceSkillsEntity();

        resourceSkillsEntity.setId( resourceSkillsRequest.getId() );
        resourceSkillsEntity.setPrimarySkills( resourceSkillsRequest.getPrimarySkills() );
        resourceSkillsEntity.setSecondarySkills( resourceSkillsRequest.getSecondarySkills() );
        resourceSkillsEntity.setTerinarySkills( resourceSkillsRequest.getTerinarySkills() );
        resourceSkillsEntity.setSkillsPercentage( resourceSkillsRequest.getSkillsPercentage() );

        return resourceSkillsEntity;
    }

    protected ResourceSkillsResponse resourceSkillsEntityToResourceSkillsResponse(ResourceSkillsEntity resourceSkillsEntity) {
        if ( resourceSkillsEntity == null ) {
            return null;
        }

        ResourceSkillsResponse resourceSkillsResponse = new ResourceSkillsResponse();

        resourceSkillsResponse.setId( resourceSkillsEntity.getId() );
        resourceSkillsResponse.setPrimarySkills( resourceSkillsEntity.getPrimarySkills() );
        resourceSkillsResponse.setSecondarySkills( resourceSkillsEntity.getSecondarySkills() );
        resourceSkillsResponse.setTerinarySkills( resourceSkillsEntity.getTerinarySkills() );
        resourceSkillsResponse.setSkillsPercentage( resourceSkillsEntity.getSkillsPercentage() );

        return resourceSkillsResponse;
    }
}
