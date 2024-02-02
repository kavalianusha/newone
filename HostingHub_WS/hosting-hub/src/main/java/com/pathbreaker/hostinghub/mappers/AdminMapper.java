// Import necessary classes and annotations
package com.pathbreaker.hostinghub.mappers;

import com.pathbreaker.hostinghub.entity.AdminEntity;
import com.pathbreaker.hostinghub.request.AdminRequest;
import com.pathbreaker.hostinghub.request.AdminUpdateRequest;
import com.pathbreaker.hostinghub.response.AdminResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

// Define this interface as a MapStruct Mapper
@Mapper(componentModel = "spring")
public interface AdminMapper {

    // Map an AdminEntity to an AdminRequest
    AdminRequest requestToEntity(AdminEntity adminEntity);

    // Map an AdminRequest to an AdminEntity
    AdminEntity entityToRequest(AdminRequest request);

    // Map a list of AdminEntities to a list of AdminResponses
    List<AdminResponse> responseToEntityList(List<AdminEntity> adminEntities);

    // Map an AdminEntity to an AdminResponse
    AdminResponse responseToEntity(AdminEntity adminEntity);

    // Map an AdminResponse to an AdminEntity
    AdminEntity entityToResponse(AdminResponse response);

    // Update an AdminEntity from an AdminUpdateRequest
    AdminEntity updateEntityFromRequest(AdminUpdateRequest adminUpdateRequest, @MappingTarget AdminEntity adminEntity);
}
