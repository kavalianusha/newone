package com.example.efilingbazaar.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.efilingbazaar.entity.SubServiceEntity;
import com.example.efilingbazaar.request.SubServiceRequest;
import com.example.efilingbazaar.request.SubServiceRequest2;
import com.example.efilingbazaar.response.SubServiceResponse;

@Mapper(componentModel = "spring")
public interface SubServiceMapper {

	    SubServiceRequest2 entityToRequest(SubServiceEntity subServiceEntity);
	    
	    SubServiceEntity toRequestEntity(SubServiceRequest request);

	    SubServiceResponse toResponse(SubServiceEntity subServiceEntity);
	    
	    SubServiceEntity toResponseEntity(SubServiceResponse subServiceResponse);

	}








