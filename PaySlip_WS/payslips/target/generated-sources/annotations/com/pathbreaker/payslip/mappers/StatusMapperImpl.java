package com.pathbreaker.payslip.mappers;

import com.pathbreaker.payslip.entity.Status;
import com.pathbreaker.payslip.request.StatusRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-01T18:56:36+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class StatusMapperImpl implements StatusMapper {

    @Override
    public Status entityToRequest(StatusRequest statusRequest) {
        if ( statusRequest == null ) {
            return null;
        }

        Status status = new Status();

        status.setStatus( statusRequest.getStatus() );
        status.setStatusInfo( statusRequest.getStatusInfo() );

        return status;
    }
}