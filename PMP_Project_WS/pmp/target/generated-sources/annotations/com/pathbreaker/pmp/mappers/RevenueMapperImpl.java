package com.pathbreaker.pmp.mappers;

import com.pathbreaker.pmp.entity.RevenueEntity;
import com.pathbreaker.pmp.entity.RevenueProductEntity;
import com.pathbreaker.pmp.entity.RevenueProjectEntity;
import com.pathbreaker.pmp.entity.RevenueSupportEntity;
import com.pathbreaker.pmp.entity.RevenueTraineeEntity;
import com.pathbreaker.pmp.request.RevenueRequest;
import com.pathbreaker.pmp.request.RevenueUpdateRequest;
import com.pathbreaker.pmp.response.RevenueProductResponse;
import com.pathbreaker.pmp.response.RevenueProjectResponse;
import com.pathbreaker.pmp.response.RevenueResponse;
import com.pathbreaker.pmp.response.RevenueSupportResponse;
import com.pathbreaker.pmp.response.RevenueTraineeResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-23T19:17:56+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class RevenueMapperImpl implements RevenueMapper {

    @Override
    public RevenueEntity entityToRequestProduct(RevenueRequest revenueRequest) {
        if ( revenueRequest == null ) {
            return null;
        }

        RevenueEntity revenueEntity = new RevenueEntity();

        revenueEntity.setProjectName( revenueRequest.getProjectName() );
        revenueEntity.setTechStack( revenueRequest.getTechStack() );
        revenueEntity.setMonth( revenueRequest.getMonth() );
        revenueEntity.setRevenueType( revenueRequest.getRevenueType() );
        revenueEntity.setSource( revenueRequest.getSource() );
        revenueEntity.setPayment1( revenueRequest.getPayment1() );
        revenueEntity.setPayment2( revenueRequest.getPayment2() );
        revenueEntity.setPayment3( revenueRequest.getPayment3() );
        revenueEntity.setPayment4( revenueRequest.getPayment4() );
        revenueEntity.setRevenueProductEntity( revenueRequest.getRevenueProducts() );

        return revenueEntity;
    }

    @Override
    public RevenueEntity entityToRequestProject(RevenueRequest revenueRequest) {
        if ( revenueRequest == null ) {
            return null;
        }

        RevenueEntity revenueEntity = new RevenueEntity();

        revenueEntity.setProjectName( revenueRequest.getProjectName() );
        revenueEntity.setTechStack( revenueRequest.getTechStack() );
        revenueEntity.setMonth( revenueRequest.getMonth() );
        revenueEntity.setRevenueType( revenueRequest.getRevenueType() );
        revenueEntity.setSource( revenueRequest.getSource() );
        revenueEntity.setPayment1( revenueRequest.getPayment1() );
        revenueEntity.setPayment2( revenueRequest.getPayment2() );
        revenueEntity.setPayment3( revenueRequest.getPayment3() );
        revenueEntity.setPayment4( revenueRequest.getPayment4() );
        revenueEntity.setRevenueProjectEntity( revenueRequest.getRevenueProjects() );

        return revenueEntity;
    }

    @Override
    public RevenueEntity entityToRequestSupports(RevenueRequest revenueRequest) {
        if ( revenueRequest == null ) {
            return null;
        }

        RevenueEntity revenueEntity = new RevenueEntity();

        revenueEntity.setProjectName( revenueRequest.getProjectName() );
        revenueEntity.setTechStack( revenueRequest.getTechStack() );
        revenueEntity.setMonth( revenueRequest.getMonth() );
        revenueEntity.setRevenueType( revenueRequest.getRevenueType() );
        revenueEntity.setSource( revenueRequest.getSource() );
        revenueEntity.setPayment1( revenueRequest.getPayment1() );
        revenueEntity.setPayment2( revenueRequest.getPayment2() );
        revenueEntity.setPayment3( revenueRequest.getPayment3() );
        revenueEntity.setPayment4( revenueRequest.getPayment4() );
        revenueEntity.setRevenueSupportEntity( revenueRequest.getRevenueSupports() );

        return revenueEntity;
    }

    @Override
    public RevenueEntity entityToRequestTrainee(RevenueRequest revenueRequest) {
        if ( revenueRequest == null ) {
            return null;
        }

        RevenueEntity revenueEntity = new RevenueEntity();

        revenueEntity.setProjectName( revenueRequest.getProjectName() );
        revenueEntity.setTechStack( revenueRequest.getTechStack() );
        revenueEntity.setMonth( revenueRequest.getMonth() );
        revenueEntity.setRevenueType( revenueRequest.getRevenueType() );
        revenueEntity.setSource( revenueRequest.getSource() );
        revenueEntity.setPayment1( revenueRequest.getPayment1() );
        revenueEntity.setPayment2( revenueRequest.getPayment2() );
        revenueEntity.setPayment3( revenueRequest.getPayment3() );
        revenueEntity.setPayment4( revenueRequest.getPayment4() );
        revenueEntity.setRevenueTraineeEntity( revenueRequest.getRevenueTrainees() );

        return revenueEntity;
    }

    @Override
    public RevenueResponse responseListToEntity(RevenueEntity revenueEntity) {
        if ( revenueEntity == null ) {
            return null;
        }

        RevenueResponse revenueResponse = new RevenueResponse();

        revenueResponse.setProjectName( revenueEntity.getProjectName() );
        revenueResponse.setTechStack( revenueEntity.getTechStack() );
        revenueResponse.setMonth( revenueEntity.getMonth() );
        revenueResponse.setRevenueType( revenueEntity.getRevenueType() );
        revenueResponse.setSource( revenueEntity.getSource() );
        revenueResponse.setPayment1( revenueEntity.getPayment1() );
        revenueResponse.setPayment2( revenueEntity.getPayment2() );
        revenueResponse.setPayment3( revenueEntity.getPayment3() );
        revenueResponse.setPayment4( revenueEntity.getPayment4() );
        revenueResponse.setRevenueProducts( revenueProductEntityToRevenueProductResponse( revenueEntity.getRevenueProductEntity() ) );
        revenueResponse.setRevenueProjects( revenueProjectEntityToRevenueProjectResponse( revenueEntity.getRevenueProjectEntity() ) );
        revenueResponse.setRevenueSupports( revenueSupportEntityToRevenueSupportResponse( revenueEntity.getRevenueSupportEntity() ) );
        revenueResponse.setRevenueTrainees( revenueTraineeEntityToRevenueTraineeResponse( revenueEntity.getRevenueTraineeEntity() ) );

        return revenueResponse;
    }

    @Override
    public RevenueResponse responseToEntity(RevenueEntity revenueEntity) {
        if ( revenueEntity == null ) {
            return null;
        }

        RevenueResponse revenueResponse = new RevenueResponse();

        revenueResponse.setProjectName( revenueEntity.getProjectName() );
        revenueResponse.setTechStack( revenueEntity.getTechStack() );
        revenueResponse.setMonth( revenueEntity.getMonth() );
        revenueResponse.setRevenueType( revenueEntity.getRevenueType() );
        revenueResponse.setSource( revenueEntity.getSource() );
        revenueResponse.setPayment1( revenueEntity.getPayment1() );
        revenueResponse.setPayment2( revenueEntity.getPayment2() );
        revenueResponse.setPayment3( revenueEntity.getPayment3() );
        revenueResponse.setPayment4( revenueEntity.getPayment4() );
        revenueResponse.setRevenueProducts( revenueProductEntityToRevenueProductResponse( revenueEntity.getRevenueProductEntity() ) );
        revenueResponse.setRevenueProjects( revenueProjectEntityToRevenueProjectResponse( revenueEntity.getRevenueProjectEntity() ) );
        revenueResponse.setRevenueSupports( revenueSupportEntityToRevenueSupportResponse( revenueEntity.getRevenueSupportEntity() ) );
        revenueResponse.setRevenueTrainees( revenueTraineeEntityToRevenueTraineeResponse( revenueEntity.getRevenueTraineeEntity() ) );

        return revenueResponse;
    }

    @Override
    public RevenueEntity entityToUpdateRequestProduct(RevenueUpdateRequest revenueUpdateRequest, RevenueEntity revenueEntity) {
        if ( revenueUpdateRequest == null ) {
            return revenueEntity;
        }

        revenueEntity.setTechStack( revenueUpdateRequest.getTechStack() );
        revenueEntity.setMonth( revenueUpdateRequest.getMonth() );
        revenueEntity.setRevenueType( revenueUpdateRequest.getRevenueType() );
        revenueEntity.setSource( revenueUpdateRequest.getSource() );
        revenueEntity.setPayment1( revenueUpdateRequest.getPayment1() );
        revenueEntity.setPayment2( revenueUpdateRequest.getPayment2() );
        revenueEntity.setPayment3( revenueUpdateRequest.getPayment3() );
        revenueEntity.setPayment4( revenueUpdateRequest.getPayment4() );

        return revenueEntity;
    }

    @Override
    public RevenueEntity entityToUpdateRequestProject(RevenueUpdateRequest revenueUpdateRequest, RevenueEntity existingRevenue) {
        if ( revenueUpdateRequest == null ) {
            return existingRevenue;
        }

        existingRevenue.setTechStack( revenueUpdateRequest.getTechStack() );
        existingRevenue.setMonth( revenueUpdateRequest.getMonth() );
        existingRevenue.setRevenueType( revenueUpdateRequest.getRevenueType() );
        existingRevenue.setSource( revenueUpdateRequest.getSource() );
        existingRevenue.setPayment1( revenueUpdateRequest.getPayment1() );
        existingRevenue.setPayment2( revenueUpdateRequest.getPayment2() );
        existingRevenue.setPayment3( revenueUpdateRequest.getPayment3() );
        existingRevenue.setPayment4( revenueUpdateRequest.getPayment4() );

        return existingRevenue;
    }

    @Override
    public RevenueEntity entityToUpdateRequestSupports(RevenueUpdateRequest revenueUpdateRequest, RevenueEntity existingRevenue) {
        if ( revenueUpdateRequest == null ) {
            return existingRevenue;
        }

        existingRevenue.setTechStack( revenueUpdateRequest.getTechStack() );
        existingRevenue.setMonth( revenueUpdateRequest.getMonth() );
        existingRevenue.setRevenueType( revenueUpdateRequest.getRevenueType() );
        existingRevenue.setSource( revenueUpdateRequest.getSource() );
        existingRevenue.setPayment1( revenueUpdateRequest.getPayment1() );
        existingRevenue.setPayment2( revenueUpdateRequest.getPayment2() );
        existingRevenue.setPayment3( revenueUpdateRequest.getPayment3() );
        existingRevenue.setPayment4( revenueUpdateRequest.getPayment4() );

        return existingRevenue;
    }

    @Override
    public RevenueEntity entityToUpdateRequestTrainee(RevenueUpdateRequest revenueUpdateRequest, RevenueEntity existingRevenue) {
        if ( revenueUpdateRequest == null ) {
            return existingRevenue;
        }

        existingRevenue.setTechStack( revenueUpdateRequest.getTechStack() );
        existingRevenue.setMonth( revenueUpdateRequest.getMonth() );
        existingRevenue.setRevenueType( revenueUpdateRequest.getRevenueType() );
        existingRevenue.setSource( revenueUpdateRequest.getSource() );
        existingRevenue.setPayment1( revenueUpdateRequest.getPayment1() );
        existingRevenue.setPayment2( revenueUpdateRequest.getPayment2() );
        existingRevenue.setPayment3( revenueUpdateRequest.getPayment3() );
        existingRevenue.setPayment4( revenueUpdateRequest.getPayment4() );

        return existingRevenue;
    }

    protected RevenueProductResponse revenueProductEntityToRevenueProductResponse(RevenueProductEntity revenueProductEntity) {
        if ( revenueProductEntity == null ) {
            return null;
        }

        RevenueProductResponse revenueProductResponse = new RevenueProductResponse();

        revenueProductResponse.setId( revenueProductEntity.getId() );
        revenueProductResponse.setProductRevenue( revenueProductEntity.getProductRevenue() );

        return revenueProductResponse;
    }

    protected RevenueProjectResponse revenueProjectEntityToRevenueProjectResponse(RevenueProjectEntity revenueProjectEntity) {
        if ( revenueProjectEntity == null ) {
            return null;
        }

        RevenueProjectResponse revenueProjectResponse = new RevenueProjectResponse();

        revenueProjectResponse.setId( revenueProjectEntity.getId() );
        revenueProjectResponse.setProjectRevenue( revenueProjectEntity.getProjectRevenue() );

        return revenueProjectResponse;
    }

    protected RevenueSupportResponse revenueSupportEntityToRevenueSupportResponse(RevenueSupportEntity revenueSupportEntity) {
        if ( revenueSupportEntity == null ) {
            return null;
        }

        RevenueSupportResponse revenueSupportResponse = new RevenueSupportResponse();

        revenueSupportResponse.setId( revenueSupportEntity.getId() );
        revenueSupportResponse.setSupportRevenue( revenueSupportEntity.getSupportRevenue() );

        return revenueSupportResponse;
    }

    protected RevenueTraineeResponse revenueTraineeEntityToRevenueTraineeResponse(RevenueTraineeEntity revenueTraineeEntity) {
        if ( revenueTraineeEntity == null ) {
            return null;
        }

        RevenueTraineeResponse revenueTraineeResponse = new RevenueTraineeResponse();

        revenueTraineeResponse.setId( revenueTraineeEntity.getId() );
        revenueTraineeResponse.setTraineeRevenue( revenueTraineeEntity.getTraineeRevenue() );

        return revenueTraineeResponse;
    }
}
