package com.pathbreaker.payslip.mappers;

import com.pathbreaker.payslip.entity.Role;
import com.pathbreaker.payslip.request.RolesRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolesMapper {
    Role entityToRequest(RolesRequest rolesRequest);
}
