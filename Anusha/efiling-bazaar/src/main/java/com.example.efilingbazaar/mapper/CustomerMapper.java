package com.example.efilingbazaar.mapper;

import com.example.efilingbazaar.entity.CustomerEntity;
import com.example.efilingbazaar.request.CustomerRequest;
import com.example.efilingbazaar.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

       CustomerRequest requestToEntity(CustomerEntity customerEntity);

	    CustomerEntity entityToRequest(CustomerRequest request);

	 List<CustomerResponse> responseToEntityList(List<CustomerEntity> customerEntity);

	 CustomerResponse responseToEntity(CustomerEntity customerEntity);

	    CustomerEntity entityToResponse(CustomerResponse response);

}
