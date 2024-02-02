package com.pathbreaker.pmp.serviceimpl;

import com.pathbreaker.pmp.entity.RevenueEntity;
import com.pathbreaker.pmp.entity.RevenueProductEntity;
import com.pathbreaker.pmp.entity.RevenueSupportEntity;
import com.pathbreaker.pmp.mappers.RevenueMapper;
import com.pathbreaker.pmp.repository.RevenueRepository;
import com.pathbreaker.pmp.request.RevenueRequest;
import com.pathbreaker.pmp.request.RevenueUpdateRequest;
import com.pathbreaker.pmp.response.ResourceResponse;
import com.pathbreaker.pmp.response.RevenueProductResponse;
import com.pathbreaker.pmp.response.RevenueResponse;
import com.pathbreaker.pmp.response.RevenueSupportResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class RevenueServiceImplTest {

    @Mock
    private RevenueRepository revenueRepository;

    @Mock
    private RevenueMapper revenueMapper;

    @Autowired
    public RevenueServiceImpl revenueService = new RevenueServiceImpl(revenueRepository,revenueMapper);

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(revenueService, "revenueRepository", revenueRepository);
        ReflectionTestUtils.setField(revenueService, "revenueMapper", revenueMapper);
    }

    @Test
    public void createRevenue() {
        RevenueEntity revenueEntity = new RevenueEntity();
        revenueEntity.setProjectName("Uber");
        revenueEntity.setRevenueType("product");
        revenueEntity.setMonth("january");
        revenueEntity.setPayment1(2000.0);
        revenueEntity.setPayment2(2000.0);
        revenueEntity.setPayment3(2000.0);
        revenueEntity.setPayment4(2000.0);
        revenueEntity.setTechStack("JAVA");
        revenueEntity.setSource("Vijay");

        RevenueRequest revenueRequest = new RevenueRequest();
        revenueRequest.setProjectName("Uber");
        revenueRequest.setRevenueType("product");
        revenueRequest.setMonth("january");
        revenueRequest.setPayment1(2000.0);
        revenueRequest.setPayment2(2000.0);
        revenueRequest.setPayment3(2000.0);
        revenueRequest.setPayment4(2000.0);
        revenueRequest.setTechStack("JAVA");
        revenueRequest.setSource("Vijay");

        RevenueProductEntity revenueProductEntity = new RevenueProductEntity();
        revenueProductEntity.setProductRevenue(8000.0);
        revenueProductEntity.setId(1L);

        revenueRequest.setRevenueProducts(revenueProductEntity);

        Mockito.when(revenueMapper.entityToRequestProduct(ArgumentMatchers.any())).thenReturn(revenueEntity);

        ResponseEntity<?> result = revenueService.createRevenue(revenueRequest);

        assertNotNull(result);
        assertEquals("Uber",revenueRequest.getProjectName());
        assertEquals("product",revenueRequest.getRevenueType());
        assertEquals("january",revenueRequest.getMonth());
        assertEquals("JAVA",revenueRequest.getTechStack());
        assertEquals("Vijay",revenueRequest.getSource());
        assertEquals(2000.0, revenueRequest.getPayment1().doubleValue(), 0.001);
        assertEquals(2000.0, revenueRequest.getPayment2().doubleValue(), 0.001);
        assertEquals(2000.0, revenueRequest.getPayment3().doubleValue(), 0.001);
        assertEquals(2000.0, revenueRequest.getPayment4().doubleValue(), 0.001);
        assertEquals(8000.0,revenueRequest.getRevenueProducts().getProductRevenue().doubleValue(), 0.001);


    }

    @Test
    public void getAllRevenues() {

        RevenueEntity revenueEntity = new RevenueEntity();
        revenueEntity.setProjectName("Uber");
        revenueEntity.setRevenueType("product");
        revenueEntity.setMonth("january");
        revenueEntity.setPayment1(2000.0);
        revenueEntity.setPayment2(2000.0);
        revenueEntity.setPayment3(2000.0);
        revenueEntity.setPayment4(2000.0);
        revenueEntity.setTechStack("JAVA");
        revenueEntity.setSource("Vijay");

        RevenueResponse revenueResponse = new RevenueResponse();
        revenueResponse.setProjectName("Uber");
        revenueResponse.setRevenueType("product");
        revenueResponse.setMonth("january");
        revenueResponse.setPayment1(2000.0);
        revenueResponse.setPayment2(2000.0);
        revenueResponse.setPayment3(2000.0);
        revenueResponse.setPayment4(2000.0);
        revenueResponse.setTechStack("JAVA");
        revenueResponse.setSource("Vijay");

        RevenueProductEntity revenueProductEntity = new RevenueProductEntity();
        revenueProductEntity.setProductRevenue(8000.0);
        revenueProductEntity.setId(1L);

        RevenueProductResponse revenueProductResponse = new RevenueProductResponse();
        revenueProductResponse.setProductRevenue(8000.0);
        revenueProductResponse.setId(1L);

        revenueResponse.setRevenueProducts(revenueProductResponse);

        RevenueEntity revenueEntity1 = new RevenueEntity();
        revenueEntity1.setProjectName("Twitter");
        revenueEntity1.setRevenueType("support");
        revenueEntity1.setMonth("febuary");
        revenueEntity1.setPayment1(2000.0);
        revenueEntity1.setPayment2(2000.0);
        revenueEntity1.setPayment3(2000.0);
        revenueEntity1.setPayment4(2000.0);
        revenueEntity1.setTechStack("Python");
        revenueEntity1.setSource("sathar");

        RevenueResponse revenueResponse1 = new RevenueResponse();
        revenueResponse1.setProjectName("Twitter");
        revenueResponse1.setRevenueType("support");
        revenueResponse1.setMonth("febuary");
        revenueResponse1.setPayment1(2000.0);
        revenueResponse1.setPayment2(2000.0);
        revenueResponse1.setPayment3(2000.0);
        revenueResponse1.setPayment4(2000.0);
        revenueResponse1.setTechStack("Python");
        revenueResponse1.setSource("sathar");

        RevenueSupportEntity revenueSupportEntity = new RevenueSupportEntity();
        revenueSupportEntity.setSupportRevenue(8000.0);
        revenueSupportEntity.setId(1L);

        RevenueSupportResponse revenueSupportResponse = new RevenueSupportResponse();
        revenueSupportResponse.setSupportRevenue(8000.0);
        revenueSupportResponse.setId(1L);

        revenueResponse1.setRevenueSupports(revenueSupportResponse);

        List<RevenueEntity> revenueEntities = new ArrayList<>();
        revenueEntities.add(revenueEntity);
        revenueEntities.add(revenueEntity1);

        Mockito.when(revenueRepository.findAll()).thenReturn(revenueEntities);
        Mockito.when(revenueMapper.responseListToEntity(revenueEntity)).thenReturn(revenueResponse);
        Mockito.when(revenueMapper.responseListToEntity(revenueEntity1)).thenReturn(revenueResponse1);

        List<RevenueResponse> result = revenueService.getAllRevenues();
        System.out.println(result);

        assertNotNull(result);

        assertEquals("Uber",result.get(0).getProjectName());
        assertEquals("product",result.get(0).getRevenueType());
        assertEquals("january",result.get(0).getMonth());
        assertEquals("JAVA",result.get(0).getTechStack());
        assertEquals("Vijay",result.get(0).getSource());
        assertEquals(2000.0, result.get(0).getPayment1().doubleValue(), 0.001);
        assertEquals(2000.0, result.get(0).getPayment2().doubleValue(), 0.001);
        assertEquals(2000.0, result.get(0).getPayment3().doubleValue(), 0.001);
        assertEquals(2000.0, result.get(0).getPayment4().doubleValue(), 0.001);
        assertEquals(8000.0,result.get(0).getRevenueProducts().getProductRevenue().doubleValue(), 0.001);

        assertEquals("Twitter",result.get(1).getProjectName());
        assertEquals("support",result.get(1).getRevenueType());
        assertEquals("febuary",result.get(1).getMonth());
        assertEquals("Python",result.get(1).getTechStack());
        assertEquals("sathar",result.get(1).getSource());
        assertEquals(2000.0, result.get(1).getPayment1().doubleValue(), 0.001);
        assertEquals(2000.0, result.get(1).getPayment2().doubleValue(), 0.001);
        assertEquals(2000.0, result.get(1).getPayment3().doubleValue(), 0.001);
        assertEquals(2000.0, result.get(1).getPayment4().doubleValue(), 0.001);
        assertEquals(8000.0,result.get(1).getRevenueSupports().getSupportRevenue().doubleValue(), 0.001);
    }

    @Test
    public void getByProjectName() {

        Mockito.when(revenueRepository.findByProjectName(createMockRevenueEntity().getProjectName())).thenReturn(Optional.of(createMockRevenueEntity()));
        Mockito.when(revenueMapper.responseToEntity(createMockRevenueEntity())).thenReturn(createMockRevenueResponse());

        RevenueResponse result = revenueService.getByProjectName(createMockRevenueEntity().getProjectName());
        System.out.println(result);

        assertNotNull(result);
        assertEquals("Uber",result.getProjectName());
        assertEquals("JAVA",result.getTechStack());
        assertEquals("january",result.getMonth());
        assertEquals("Vijay",result.getSource());
        assertEquals(2000.0,result.getPayment1().doubleValue(),0.001);
        assertEquals(2000.0,result.getPayment2().doubleValue(),0.001);
        assertEquals(2000.0,result.getPayment3().doubleValue(),0.001);
        assertEquals(2000.0,result.getPayment4().doubleValue(),0.001);
        assertEquals(8000.0,result.getRevenueSupports().getSupportRevenue().doubleValue(),0.001);


    }

    @Test
    public void updateRevenueByProjectName() {
        Mockito.when(revenueRepository.findByProjectName(createMockRevenueEntity().getProjectName())).thenReturn(Optional.of(createMockRevenueEntity()));

        Mockito.when(revenueMapper.entityToUpdateRequestSupports(createMockRevenueUpdateRequest(),createMockRevenueEntity())).thenReturn(createMockRevenueEntity());

        ResponseEntity<?> result = revenueService.updateRevenueByProjectName(createMockRevenueEntity().getProjectName(),createMockRevenueUpdateRequest());

        assertNotNull(result);

        assertEquals("support",createMockRevenueUpdateRequest().getRevenueType());
        assertEquals("january",createMockRevenueUpdateRequest().getMonth());
        assertEquals("JAVA",createMockRevenueUpdateRequest().getTechStack());
        assertEquals("Vijay",createMockRevenueUpdateRequest().getSource());
        assertEquals(8000.0,createMockRevenueUpdateRequest().getRevenueSupports().getSupportRevenue().doubleValue(),0.001);
    }


    @Test
    public void deleteRevenueByProjectName() {

        Mockito.when(revenueRepository.findByProjectName(ArgumentMatchers.anyString())).thenReturn(Optional.of(createMockRevenueEntity()));

        ResponseEntity<?> result = revenueService.deleteRevenueByProjectName(createMockRevenueEntity().getProjectName());

        System.out.println(result);
        assertNotNull(result);
        assertEquals("support",createMockRevenueEntity().getRevenueType());
        assertEquals("january",createMockRevenueEntity().getMonth());
        assertEquals("JAVA",createMockRevenueEntity().getTechStack());
        assertEquals("Vijay",createMockRevenueEntity().getSource());
        assertEquals(8000.0,createMockRevenueEntity().getRevenueSupportEntity().getSupportRevenue().doubleValue(),0.001);

    }



    private RevenueEntity createMockRevenueEntity() {
        RevenueEntity revenueEntity = new RevenueEntity();
        revenueEntity.setProjectName("Uber");
        revenueEntity.setRevenueType("support");
        revenueEntity.setMonth("january");
        revenueEntity.setPayment1(2000.0);
        revenueEntity.setPayment2(2000.0);
        revenueEntity.setPayment3(2000.0);
        revenueEntity.setPayment4(2000.0);
        revenueEntity.setTechStack("JAVA");
        revenueEntity.setSource("Vijay");
        revenueEntity.setRevenueSupportEntity(createMockRevenueSupportEntity());
        return revenueEntity;
    }

    private RevenueRequest createMockRevenueRequest() {
        RevenueRequest revenueRequest = new RevenueRequest();
        revenueRequest.setProjectName("Uber");
        revenueRequest.setRevenueType("support");
        revenueRequest.setMonth("january");
        revenueRequest.setPayment1(2000.0);
        revenueRequest.setPayment2(2000.0);
        revenueRequest.setPayment3(2000.0);
        revenueRequest.setPayment4(2000.0);
        revenueRequest.setTechStack("JAVA");
        revenueRequest.setSource("Vijay");
        revenueRequest.setRevenueSupports(createMockRevenueSupportEntity());
        return revenueRequest;
    }

    private RevenueUpdateRequest createMockRevenueUpdateRequest() {
        RevenueUpdateRequest revenueRequest = new RevenueUpdateRequest();
        revenueRequest.setRevenueType("support");
        revenueRequest.setMonth("january");
        revenueRequest.setPayment1(2000.0);
        revenueRequest.setPayment2(2000.0);
        revenueRequest.setPayment3(2000.0);
        revenueRequest.setPayment4(2000.0);
        revenueRequest.setTechStack("JAVA");
        revenueRequest.setSource("Vijay");
        revenueRequest.setRevenueSupports(createMockRevenueSupportEntity());
        return revenueRequest;
    }

    private RevenueResponse createMockRevenueResponse() {
        RevenueResponse revenueResponse = new RevenueResponse();
        revenueResponse.setProjectName("Uber");
        revenueResponse.setRevenueType("support");
        revenueResponse.setMonth("january");
        revenueResponse.setPayment1(2000.0);
        revenueResponse.setPayment2(2000.0);
        revenueResponse.setPayment3(2000.0);
        revenueResponse.setPayment4(2000.0);
        revenueResponse.setTechStack("JAVA");
        revenueResponse.setSource("Vijay");
        revenueResponse.setRevenueSupports(createMockRevenueSupportResponse());
        return revenueResponse;
    }

    private RevenueSupportResponse createMockRevenueSupportResponse() {
        RevenueSupportResponse revenueSupportResponse = new RevenueSupportResponse();
        revenueSupportResponse.setId(1L);
        revenueSupportResponse.setSupportRevenue(8000.0);
        return revenueSupportResponse;
    }

    private RevenueSupportEntity createMockRevenueSupportEntity() {
        RevenueSupportEntity revenueSupportEntity = new RevenueSupportEntity();
        revenueSupportEntity.setId(1L);
        revenueSupportEntity.setSupportRevenue(8000.0);
        return revenueSupportEntity;
    }


}