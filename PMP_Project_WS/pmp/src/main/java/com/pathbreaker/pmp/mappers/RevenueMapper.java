package com.pathbreaker.pmp.mappers;

import com.pathbreaker.pmp.entity.RevenueEntity;
import com.pathbreaker.pmp.request.RevenueRequest;
import com.pathbreaker.pmp.request.RevenueUpdateRequest;
import com.pathbreaker.pmp.response.ResourceResponse;
import com.pathbreaker.pmp.response.RevenueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface RevenueMapper {

    @Mappings({
            @Mapping(target = "projectName", source = "revenueRequest.projectName"),
            @Mapping(target = "techStack", source = "revenueRequest.techStack"),
            @Mapping(target = "month", source = "revenueRequest.month"),
            @Mapping(target = "revenueType", source = "revenueRequest.revenueType"),
            @Mapping(target = "source", source = "revenueRequest.source"),
            @Mapping(target = "payment1", source = "revenueRequest.payment1"),
            @Mapping(target = "payment2", source = "revenueRequest.payment2"),
            @Mapping(target = "payment3", source = "revenueRequest.payment3"),
            @Mapping(target = "payment4", source = "revenueRequest.payment4"),
            @Mapping(target = "revenueProductEntity", source = "revenueRequest.revenueProducts"),
    })
    RevenueEntity entityToRequestProduct(RevenueRequest revenueRequest);

    @Mappings({
            @Mapping(target = "projectName", source = "revenueRequest.projectName"),
            @Mapping(target = "techStack", source = "revenueRequest.techStack"),
            @Mapping(target = "month", source = "revenueRequest.month"),
            @Mapping(target = "revenueType", source = "revenueRequest.revenueType"),
            @Mapping(target = "source", source = "revenueRequest.source"),
            @Mapping(target = "payment1", source = "revenueRequest.payment1"),
            @Mapping(target = "payment2", source = "revenueRequest.payment2"),
            @Mapping(target = "payment3", source = "revenueRequest.payment3"),
            @Mapping(target = "payment4", source = "revenueRequest.payment4"),
            @Mapping(target = "revenueProjectEntity", source = "revenueRequest.revenueProjects"),
    })
    RevenueEntity entityToRequestProject(RevenueRequest revenueRequest);

    @Mappings({
            @Mapping(target = "projectName", source = "revenueRequest.projectName"),
            @Mapping(target = "techStack", source = "revenueRequest.techStack"),
            @Mapping(target = "month", source = "revenueRequest.month"),
            @Mapping(target = "revenueType", source = "revenueRequest.revenueType"),
            @Mapping(target = "source", source = "revenueRequest.source"),
            @Mapping(target = "payment1", source = "revenueRequest.payment1"),
            @Mapping(target = "payment2", source = "revenueRequest.payment2"),
            @Mapping(target = "payment3", source = "revenueRequest.payment3"),
            @Mapping(target = "payment4", source = "revenueRequest.payment4"),
            @Mapping(target = "revenueSupportEntity", source = "revenueRequest.revenueSupports"),
    })
    RevenueEntity entityToRequestSupports(RevenueRequest revenueRequest);

    @Mappings({
            @Mapping(target = "projectName", source = "revenueRequest.projectName"),
            @Mapping(target = "techStack", source = "revenueRequest.techStack"),
            @Mapping(target = "month", source = "revenueRequest.month"),
            @Mapping(target = "revenueType", source = "revenueRequest.revenueType"),
            @Mapping(target = "source", source = "revenueRequest.source"),
            @Mapping(target = "payment1", source = "revenueRequest.payment1"),
            @Mapping(target = "payment2", source = "revenueRequest.payment2"),
            @Mapping(target = "payment3", source = "revenueRequest.payment3"),
            @Mapping(target = "payment4", source = "revenueRequest.payment4"),
            @Mapping(target = "revenueTraineeEntity", source = "revenueRequest.revenueTrainees"),
    })
    RevenueEntity entityToRequestTrainee(RevenueRequest revenueRequest);

    @Mappings({
            @Mapping(target = "projectName", source = "revenueEntity.projectName"),
            @Mapping(target = "techStack", source = "revenueEntity.techStack"),
            @Mapping(target = "month", source = "revenueEntity.month"),
            @Mapping(target = "revenueType", source = "revenueEntity.revenueType"),
            @Mapping(target = "source", source = "revenueEntity.source"),
            @Mapping(target = "payment1", source = "revenueEntity.payment1"),
            @Mapping(target = "payment2", source = "revenueEntity.payment2"),
            @Mapping(target = "payment3", source = "revenueEntity.payment3"),
            @Mapping(target = "payment4", source = "revenueEntity.payment4"),
            @Mapping(target = "revenueProducts", source = "revenueEntity.revenueProductEntity"),
            @Mapping(target = "revenueProjects", source = "revenueEntity.revenueProjectEntity"),
            @Mapping(target = "revenueSupports", source = "revenueEntity.revenueSupportEntity"),
            @Mapping(target = "revenueTrainees", source = "revenueEntity.revenueTraineeEntity"),
    })
    RevenueResponse responseListToEntity(RevenueEntity revenueEntity);

    @Mappings({
            @Mapping(target = "projectName", source = "revenueEntity.projectName"),
            @Mapping(target = "techStack", source = "revenueEntity.techStack"),
            @Mapping(target = "month", source = "revenueEntity.month"),
            @Mapping(target = "revenueType", source = "revenueEntity.revenueType"),
            @Mapping(target = "source", source = "revenueEntity.source"),
            @Mapping(target = "payment1", source = "revenueEntity.payment1"),
            @Mapping(target = "payment2", source = "revenueEntity.payment2"),
            @Mapping(target = "payment3", source = "revenueEntity.payment3"),
            @Mapping(target = "payment4", source = "revenueEntity.payment4"),
            @Mapping(target = "revenueProducts", source = "revenueEntity.revenueProductEntity"),
            @Mapping(target = "revenueProjects", source = "revenueEntity.revenueProjectEntity"),
            @Mapping(target = "revenueSupports", source = "revenueEntity.revenueSupportEntity"),
            @Mapping(target = "revenueTrainees", source = "revenueEntity.revenueTraineeEntity"),
    })
    RevenueResponse responseToEntity(RevenueEntity revenueEntity);

    RevenueEntity entityToUpdateRequestProduct(RevenueUpdateRequest revenueUpdateRequest, @MappingTarget  RevenueEntity revenueEntity);

    RevenueEntity entityToUpdateRequestProject(RevenueUpdateRequest revenueUpdateRequest, @MappingTarget RevenueEntity existingRevenue);

    RevenueEntity entityToUpdateRequestSupports(RevenueUpdateRequest revenueUpdateRequest, @MappingTarget RevenueEntity existingRevenue);

    RevenueEntity entityToUpdateRequestTrainee(RevenueUpdateRequest revenueUpdateRequest, @MappingTarget RevenueEntity existingRevenue);
}
