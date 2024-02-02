package com.pathbreaker.payslip.mappers;

import com.pathbreaker.payslip.entity.Status;
import com.pathbreaker.payslip.request.StatusRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    Status entityToRequest(StatusRequest statusRequest);
}
