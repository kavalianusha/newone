package com.example.efilingbazaar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.efilingbazaar.entity.MainServiceEntity;
import com.example.efilingbazaar.request.MainServiceRequest;
import com.example.efilingbazaar.response.MainServiceResponse;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MainServiceMapper {
    @Mappings({
            @Mapping(target = "mainServiceId", source = "request.mainServiceId"),
            @Mapping(target = "mainServiceName", source = "request.mainServiceName"),
            @Mapping(target = "mainServiceShortName", source = "request.mainServiceShortName"),
            @Mapping(target = "liable", source = "request.liable"),
            @Mapping(target = "governmentSection", source = "request.governmentSection"),
            @Mapping(target = "createdBy", source = "request.createdBy"),
            @Mapping(target = "createdDate", source = "request.createdDate"),
            @Mapping(target = "updatedDate", source = "request.updatedDate"),
            @Mapping(target = "mainServiceFilePaths", source = "request.mainServiceFilePaths"),
    })
    MainServiceEntity toMainServiceEntity(MainServiceRequest request);//this for post

    MainServiceRequest toEntityRequest(MainServiceEntity entity);

    MainServiceResponse toResponse(MainServiceEntity entity);

   /* @Mappings({
            @Mapping(target = "mainServiceId", source = "response.mainServiceId"),
            @Mapping(target = "mainServiceName", source = "response.mainServiceName"),
            @Mapping(target = "mainServiceShortName", source = "response.mainServiceShortName"),
            @Mapping(target = "governmentSection", source = "response.governmentSection"),
            @Mapping(target = "liable", source = "response.liable"),
            @Mapping(target = "subServices.subServiceId", source = "response.subServices.subServiceId"),
            @Mapping(target = "subServices.subServiceName", source = "response.subServices.subServiceName"),
            @Mapping(target = "subServices.subServiceShortName", source = "response.subServices.subServiceShortName"),
            @Mapping(target = "subServices.governmentSection", source = "response.subServices.governmentSection"),
            @Mapping(target = "subServices.liable", source = "response.subServices.liable"),
            @Mapping(target = "subServices.prosAndCons", source = "response.subServices.prosAndCons"),
            @Mapping(target = "subServices.description", source = "response.subServices.description")
    })*/
    MainServiceEntity toEntityResponse(MainServiceResponse response);
}

    
    
    
 
    