package com.example.efilingbazaar.mapper;

import com.example.efilingbazaar.entity.MainServiceEntity;
import com.example.efilingbazaar.entity.SubServiceEntity;
import com.example.efilingbazaar.request.MainServiceRequest;
import com.example.efilingbazaar.response.MainServiceResponse;
import com.example.efilingbazaar.response.SubServiceResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-25T19:58:25+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class MainServiceMapperImpl implements MainServiceMapper {

    @Override
    public MainServiceEntity toMainServiceEntity(MainServiceRequest request) {
        if ( request == null ) {
            return null;
        }

        MainServiceEntity mainServiceEntity = new MainServiceEntity();

        mainServiceEntity.setMainServiceId( request.getMainServiceId() );
        mainServiceEntity.setMainServiceName( request.getMainServiceName() );
        mainServiceEntity.setMainServiceShortName( request.getMainServiceShortName() );
        mainServiceEntity.setLiable( request.getLiable() );
        mainServiceEntity.setGovernmentSection( request.getGovernmentSection() );
        mainServiceEntity.setCreatedBy( request.getCreatedBy() );
        mainServiceEntity.setCreatedDate( request.getCreatedDate() );
        mainServiceEntity.setUpdatedDate( request.getUpdatedDate() );
        List<String> list = request.getMainServiceFilePaths();
        if ( list != null ) {
            mainServiceEntity.setMainServiceFilePaths( new ArrayList<String>( list ) );
        }
        mainServiceEntity.setProsAndCons( request.getProsAndCons() );
        mainServiceEntity.setDescription( request.getDescription() );
        mainServiceEntity.setStatus( request.isStatus() );

        return mainServiceEntity;
    }

    @Override
    public MainServiceRequest toEntityRequest(MainServiceEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MainServiceRequest mainServiceRequest = new MainServiceRequest();

        mainServiceRequest.setMainServiceName( entity.getMainServiceName() );
        mainServiceRequest.setMainServiceShortName( entity.getMainServiceShortName() );
        mainServiceRequest.setGovernmentSection( entity.getGovernmentSection() );
        mainServiceRequest.setLiable( entity.getLiable() );
        mainServiceRequest.setProsAndCons( entity.getProsAndCons() );
        mainServiceRequest.setDescription( entity.getDescription() );
        mainServiceRequest.setStatus( entity.isStatus() );
        List<String> list = entity.getMainServiceFilePaths();
        if ( list != null ) {
            mainServiceRequest.setMainServiceFilePaths( new ArrayList<String>( list ) );
        }
        mainServiceRequest.setCreatedBy( entity.getCreatedBy() );
        mainServiceRequest.setCreatedDate( entity.getCreatedDate() );
        mainServiceRequest.setUpdatedDate( entity.getUpdatedDate() );
        mainServiceRequest.setMainServiceId( entity.getMainServiceId() );

        return mainServiceRequest;
    }

    @Override
    public MainServiceResponse toResponse(MainServiceEntity entity) {
        if ( entity == null ) {
            return null;
        }

        MainServiceResponse mainServiceResponse = new MainServiceResponse();

        mainServiceResponse.setMainServiceId( entity.getMainServiceId() );
        mainServiceResponse.setMainServiceName( entity.getMainServiceName() );
        mainServiceResponse.setMainServiceShortName( entity.getMainServiceShortName() );
        mainServiceResponse.setGovernmentSection( entity.getGovernmentSection() );
        mainServiceResponse.setLiable( entity.getLiable() );
        mainServiceResponse.setProsAndCons( entity.getProsAndCons() );
        mainServiceResponse.setDescription( entity.getDescription() );
        mainServiceResponse.setStatus( entity.isStatus() );
        List<String> list = entity.getMainServiceFilePaths();
        if ( list != null ) {
            mainServiceResponse.setMainServiceFilePaths( new ArrayList<String>( list ) );
        }
        mainServiceResponse.setCreatedBy( entity.getCreatedBy() );
        mainServiceResponse.setCreatedDate( entity.getCreatedDate() );
        mainServiceResponse.setUpdatedDate( entity.getUpdatedDate() );
        mainServiceResponse.setSubServices( subServiceEntityListToSubServiceResponseList( entity.getSubServices() ) );

        return mainServiceResponse;
    }

    @Override
    public MainServiceEntity toEntityResponse(MainServiceResponse response) {
        if ( response == null ) {
            return null;
        }

        MainServiceEntity mainServiceEntity = new MainServiceEntity();

        mainServiceEntity.setMainServiceId( response.getMainServiceId() );
        mainServiceEntity.setSubServices( subServiceResponseListToSubServiceEntityList( response.getSubServices() ) );
        mainServiceEntity.setMainServiceName( response.getMainServiceName() );
        mainServiceEntity.setMainServiceShortName( response.getMainServiceShortName() );
        mainServiceEntity.setGovernmentSection( response.getGovernmentSection() );
        mainServiceEntity.setLiable( response.getLiable() );
        mainServiceEntity.setProsAndCons( response.getProsAndCons() );
        mainServiceEntity.setDescription( response.getDescription() );
        mainServiceEntity.setStatus( response.isStatus() );
        List<String> list1 = response.getMainServiceFilePaths();
        if ( list1 != null ) {
            mainServiceEntity.setMainServiceFilePaths( new ArrayList<String>( list1 ) );
        }
        mainServiceEntity.setCreatedBy( response.getCreatedBy() );
        mainServiceEntity.setCreatedDate( response.getCreatedDate() );
        mainServiceEntity.setUpdatedDate( response.getUpdatedDate() );

        return mainServiceEntity;
    }

    protected SubServiceResponse subServiceEntityToSubServiceResponse(SubServiceEntity subServiceEntity) {
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

    protected List<SubServiceResponse> subServiceEntityListToSubServiceResponseList(List<SubServiceEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<SubServiceResponse> list1 = new ArrayList<SubServiceResponse>( list.size() );
        for ( SubServiceEntity subServiceEntity : list ) {
            list1.add( subServiceEntityToSubServiceResponse( subServiceEntity ) );
        }

        return list1;
    }

    protected SubServiceEntity subServiceResponseToSubServiceEntity(SubServiceResponse subServiceResponse) {
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

    protected List<SubServiceEntity> subServiceResponseListToSubServiceEntityList(List<SubServiceResponse> list) {
        if ( list == null ) {
            return null;
        }

        List<SubServiceEntity> list1 = new ArrayList<SubServiceEntity>( list.size() );
        for ( SubServiceResponse subServiceResponse : list ) {
            list1.add( subServiceResponseToSubServiceEntity( subServiceResponse ) );
        }

        return list1;
    }
}
