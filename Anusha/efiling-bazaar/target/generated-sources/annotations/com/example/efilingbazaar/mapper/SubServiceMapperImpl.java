package com.example.efilingbazaar.mapper;

import com.example.efilingbazaar.entity.MainServiceEntity;
import com.example.efilingbazaar.entity.SubServiceEntity;
import com.example.efilingbazaar.request.MainServiceRequest;
import com.example.efilingbazaar.request.SubServiceRequest;
import com.example.efilingbazaar.request.SubServiceRequest2;
import com.example.efilingbazaar.response.SubServiceResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-25T19:58:26+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class SubServiceMapperImpl implements SubServiceMapper {

    @Override
    public SubServiceRequest2 entityToRequest(SubServiceEntity subServiceEntity) {
        if ( subServiceEntity == null ) {
            return null;
        }

        SubServiceRequest2 subServiceRequest2 = new SubServiceRequest2();

        subServiceRequest2.setSubServiceId( subServiceEntity.getSubServiceId() );
        subServiceRequest2.setSubServiceName( subServiceEntity.getSubServiceName() );
        subServiceRequest2.setSubServiceShortName( subServiceEntity.getSubServiceShortName() );
        subServiceRequest2.setGovernmentSection( subServiceEntity.getGovernmentSection() );
        subServiceRequest2.setLiable( subServiceEntity.getLiable() );
        subServiceRequest2.setProsAndCons( subServiceEntity.getProsAndCons() );
        subServiceRequest2.setDescription( subServiceEntity.getDescription() );
        subServiceRequest2.setStatus( subServiceEntity.isStatus() );
        List<String> list = subServiceEntity.getSubServiceFilePaths();
        if ( list != null ) {
            subServiceRequest2.setSubServiceFilePaths( new ArrayList<String>( list ) );
        }
        subServiceRequest2.setCreatedBy( subServiceEntity.getCreatedBy() );
        subServiceRequest2.setCreatedDate( subServiceEntity.getCreatedDate() );
        subServiceRequest2.setUpdatedDate( subServiceEntity.getUpdatedDate() );
        subServiceRequest2.setMainServiceEntity( mainServiceEntityToMainServiceRequest( subServiceEntity.getMainServiceEntity() ) );

        return subServiceRequest2;
    }

    @Override
    public SubServiceEntity toRequestEntity(SubServiceRequest request) {
        if ( request == null ) {
            return null;
        }

        SubServiceEntity subServiceEntity = new SubServiceEntity();

        subServiceEntity.setSubServiceId( request.getSubServiceId() );
        subServiceEntity.setMainServiceEntity( request.getMainServiceEntity() );
        subServiceEntity.setSubServiceName( request.getSubServiceName() );
        subServiceEntity.setSubServiceShortName( request.getSubServiceShortName() );
        subServiceEntity.setDescription( request.getDescription() );
        subServiceEntity.setGovernmentSection( request.getGovernmentSection() );
        subServiceEntity.setLiable( request.getLiable() );
        subServiceEntity.setProsAndCons( request.getProsAndCons() );
        subServiceEntity.setStatus( request.isStatus() );
        List<String> list = request.getSubServiceFilePaths();
        if ( list != null ) {
            subServiceEntity.setSubServiceFilePaths( new ArrayList<String>( list ) );
        }
        subServiceEntity.setCreatedBy( request.getCreatedBy() );
        subServiceEntity.setCreatedDate( request.getCreatedDate() );
        subServiceEntity.setUpdatedDate( request.getUpdatedDate() );

        return subServiceEntity;
    }

    @Override
    public SubServiceResponse toResponse(SubServiceEntity subServiceEntity) {
        if ( subServiceEntity == null ) {
            return null;
        }

        SubServiceResponse subServiceResponse = new SubServiceResponse();

        subServiceResponse.setSubServiceId( subServiceEntity.getSubServiceId() );
        subServiceResponse.setSubServiceName( subServiceEntity.getSubServiceName() );
        subServiceResponse.setSubServiceShortName( subServiceEntity.getSubServiceShortName() );
        subServiceResponse.setGovernmentSection( subServiceEntity.getGovernmentSection() );
        subServiceResponse.setLiable( subServiceEntity.getLiable() );
        subServiceResponse.setProsAndCons( subServiceEntity.getProsAndCons() );
        subServiceResponse.setDescription( subServiceEntity.getDescription() );
        subServiceResponse.setStatus( subServiceEntity.isStatus() );
        List<String> list = subServiceEntity.getSubServiceFilePaths();
        if ( list != null ) {
            subServiceResponse.setSubServiceFilePaths( new ArrayList<String>( list ) );
        }
        subServiceResponse.setCreatedBy( subServiceEntity.getCreatedBy() );
        subServiceResponse.setCreatedDate( subServiceEntity.getCreatedDate() );
        subServiceResponse.setUpdatedDate( subServiceEntity.getUpdatedDate() );

        return subServiceResponse;
    }

    @Override
    public SubServiceEntity toResponseEntity(SubServiceResponse subServiceResponse) {
        if ( subServiceResponse == null ) {
            return null;
        }

        SubServiceEntity subServiceEntity = new SubServiceEntity();

        subServiceEntity.setSubServiceId( subServiceResponse.getSubServiceId() );
        subServiceEntity.setSubServiceName( subServiceResponse.getSubServiceName() );
        subServiceEntity.setSubServiceShortName( subServiceResponse.getSubServiceShortName() );
        subServiceEntity.setDescription( subServiceResponse.getDescription() );
        subServiceEntity.setGovernmentSection( subServiceResponse.getGovernmentSection() );
        subServiceEntity.setLiable( subServiceResponse.getLiable() );
        subServiceEntity.setProsAndCons( subServiceResponse.getProsAndCons() );
        subServiceEntity.setStatus( subServiceResponse.isStatus() );
        List<String> list = subServiceResponse.getSubServiceFilePaths();
        if ( list != null ) {
            subServiceEntity.setSubServiceFilePaths( new ArrayList<String>( list ) );
        }
        subServiceEntity.setCreatedBy( subServiceResponse.getCreatedBy() );
        subServiceEntity.setCreatedDate( subServiceResponse.getCreatedDate() );
        subServiceEntity.setUpdatedDate( subServiceResponse.getUpdatedDate() );

        return subServiceEntity;
    }

    protected MainServiceRequest mainServiceEntityToMainServiceRequest(MainServiceEntity mainServiceEntity) {
        if ( mainServiceEntity == null ) {
            return null;
        }

        MainServiceRequest mainServiceRequest = new MainServiceRequest();

        mainServiceRequest.setMainServiceName( mainServiceEntity.getMainServiceName() );
        mainServiceRequest.setMainServiceShortName( mainServiceEntity.getMainServiceShortName() );
        mainServiceRequest.setGovernmentSection( mainServiceEntity.getGovernmentSection() );
        mainServiceRequest.setLiable( mainServiceEntity.getLiable() );
        mainServiceRequest.setProsAndCons( mainServiceEntity.getProsAndCons() );
        mainServiceRequest.setDescription( mainServiceEntity.getDescription() );
        mainServiceRequest.setStatus( mainServiceEntity.isStatus() );
        List<String> list = mainServiceEntity.getMainServiceFilePaths();
        if ( list != null ) {
            mainServiceRequest.setMainServiceFilePaths( new ArrayList<String>( list ) );
        }
        mainServiceRequest.setCreatedBy( mainServiceEntity.getCreatedBy() );
        mainServiceRequest.setCreatedDate( mainServiceEntity.getCreatedDate() );
        mainServiceRequest.setUpdatedDate( mainServiceEntity.getUpdatedDate() );
        mainServiceRequest.setMainServiceId( mainServiceEntity.getMainServiceId() );

        return mainServiceRequest;
    }
}
