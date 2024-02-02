package com.pathbreaker.payslip.mappers;

import com.pathbreaker.payslip.entity.PaySlip;
import com.pathbreaker.payslip.request.PaySlipUpdateRequest;
import com.pathbreaker.payslip.request.PaySlipsRequest;
import com.pathbreaker.payslip.response.PaySlipsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaySlipMapper {
    PaySlip entityToRequest(PaySlipsRequest paySlipsRequest);

    PaySlip entityToUpdateRequest(PaySlipUpdateRequest paySlipsRequest, @MappingTarget PaySlip entity);

    List<PaySlipsResponse> entityToResponseList(List<PaySlip> paySlipUploadEntities);
}
