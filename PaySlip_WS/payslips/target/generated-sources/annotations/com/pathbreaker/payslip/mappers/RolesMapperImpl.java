package com.pathbreaker.payslip.mappers;

import com.pathbreaker.payslip.entity.Role;
import com.pathbreaker.payslip.request.RolesRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-01T18:56:36+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class RolesMapperImpl implements RolesMapper {

    @Override
    public Role entityToRequest(RolesRequest rolesRequest) {
        if ( rolesRequest == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( rolesRequest.getId() );
        role.setRole( rolesRequest.getRole() );

        return role;
    }
}
