package com.pathbreaker.payslip.mappers;

import com.pathbreaker.payslip.entity.Department;
import com.pathbreaker.payslip.request.DepartmentRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-01T18:56:36+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public Department entityToRequest(DepartmentRequest departmentRequest) {
        if ( departmentRequest == null ) {
            return null;
        }

        Department department = new Department();

        department.setId( departmentRequest.getId() );
        department.setDepartmentTitle( departmentRequest.getDepartmentTitle() );

        return department;
    }
}