package com.pathbreaker.hostinghub.service;

import com.pathbreaker.hostinghub.entity.HostDomainMapEntity;
import com.pathbreaker.hostinghub.mappers.HostDomainMapMapper;
import com.pathbreaker.hostinghub.repository.DomainRepository;
import com.pathbreaker.hostinghub.repository.HostDomainMapRepository;

import com.pathbreaker.hostinghub.repository.HostingRepository;
import com.pathbreaker.hostinghub.request.HostDomainMapRequest;
import com.pathbreaker.hostinghub.request.HostDomainMapUpdateRequest;

import com.pathbreaker.hostinghub.response.HostDomainMapResponse;
//import com.example.hostinghub.service.HostDomainMapService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class HostDomainMapServiceTest {

    @Mock
    private HostDomainMapRepository hostDomainMapRepository;
    @Mock
    private HostDomainMapMapper hostDomainMapMapper;
    @Mock
    private DomainRepository domainRepository;
    @Mock
    private HostingRepository hostingRepository;

    private HostDomainMapService service = new HostDomainMapService(hostDomainMapRepository,
            hostDomainMapMapper,domainRepository,hostingRepository);

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(service, "hostDomainMapRepository", hostDomainMapRepository);
        ReflectionTestUtils.setField(service, "hostDomainMapMapper", hostDomainMapMapper);
        ReflectionTestUtils.setField(service, "domainRepository", domainRepository);
        ReflectionTestUtils.setField(service, "hostingRepository", hostingRepository);
    }

    @Test
    public void addHostDomainAdd() {
        HostDomainMapRequest request = new HostDomainMapRequest();
        request.setHostDomainId("HD001");
        request.setDomainName("domain1");
        request.setHostProvider("anusha");
        request.setPayment("900");
        request.setDuration("1 year");
        request.setRegistrationDate("2023-10-12");
        request.setExpiryDate("2024-10-12");

        HostDomainMapEntity entity = new HostDomainMapEntity();
        entity.setHostDomainId("HD001");
        entity.setDomainName("domain1");
        entity.setHostProvider("anusha");
        entity.setPayment("900");
        entity.setDuration("1 year");
        entity.setRegistrationDate("2023-10-12");
        entity.setExpiryDate("2024-10-12");

        Mockito.when(hostDomainMapRepository.findHighestHostDomainId()).thenReturn(request.getHostDomainId());
        Mockito.when(hostDomainMapMapper.entityToRequest(ArgumentMatchers.any())).thenReturn(entity);
        Mockito.when(hostDomainMapRepository.save(entity)).thenReturn(entity);

        ResponseEntity<?> hostDomainMapService = service.addHostDomainAdd(request);

        assertNotNull(hostDomainMapService);

        assertEquals("anusha", request.getHostProvider());
        assertEquals("HD002", request.getHostDomainId());
        assertEquals("domain1", request.getDomainName());
        assertEquals("900", request.getPayment());
        assertEquals("1 year", request.getDuration());
        assertEquals("2023-10-12", request.getRegistrationDate());
        assertEquals("2024-10-12", request.getExpiryDate());
    }

    @Test
    public void getAllHostDomains() {

        HostDomainMapResponse request1 = new HostDomainMapResponse();
        request1.setHostDomainId("HD001");
        request1.setDomainName("domain1");
        request1.setHostProvider("anusha");
        request1.setPayment("900");
        request1.setDuration("1 year");
        request1.setRegistrationDate("2023-10-12");
        request1.setExpiryDate("2024-10-12");

        HostDomainMapResponse request2 = new HostDomainMapResponse();
        request2.setHostDomainId("HD001");
        request2.setDomainName("domain1");
        request2.setHostProvider("anusha");
        request2.setPayment("900");
        request2.setDuration("1 year");
        request2.setRegistrationDate("2023-10-12");
        request2.setExpiryDate("2024-10-12");

        List<HostDomainMapResponse> hostDomainMapResponses = new ArrayList<>();
        hostDomainMapResponses.add(request1);
        hostDomainMapResponses.add(request2);

        HostDomainMapEntity entity1 = new HostDomainMapEntity();
        entity1.setHostDomainId("HD001");
        entity1.setDomainName("domain1");
        entity1.setHostProvider("anusha");
        entity1.setPayment("900");
        entity1.setDuration("1 year");
        entity1.setRegistrationDate("2023-10-12");
        entity1.setExpiryDate("2024-10-12");

        HostDomainMapEntity entity2 = new HostDomainMapEntity();
        entity2.setHostDomainId("HD001");
        entity2.setDomainName("domain1");
        entity2.setHostProvider("anusha");
        entity2.setPayment("900");
        entity2.setDuration("1 year");
        entity2.setRegistrationDate("2023-10-12");
        entity2.setExpiryDate("2024-10-12");

        List<HostDomainMapEntity> hostDomainMapEntities = new ArrayList<>();
        hostDomainMapEntities.add(entity1);
        hostDomainMapEntities.add(entity2);

        Mockito.when(hostDomainMapRepository.findAll()).thenReturn(hostDomainMapEntities);
        Mockito.when(hostDomainMapMapper.responseToEntity(ArgumentMatchers.any())).thenReturn(hostDomainMapResponses);

        List<HostDomainMapResponse> hostDomainMapService = service.getAllHostDomains();

        assertNotNull(hostDomainMapService);

        assertEquals("anusha", request1.getHostProvider());
        assertEquals("HD001", request1.getHostDomainId());
        assertEquals("domain1", request1.getDomainName());
        assertEquals("900", request1.getPayment());
        assertEquals("1 year", request1.getDuration());
        assertEquals("2023-10-12", request1.getRegistrationDate());
        assertEquals("2024-10-12", request1.getExpiryDate());

    }

    @Test
    public void getAllHostDomainsById() {

        HostDomainMapResponse response = new HostDomainMapResponse();
        response.setHostDomainId("HD001");
        response.setDomainName("domain1");
        response.setHostProvider("anusha");
        response.setPayment("900");
        response.setDuration("1 year");
        response.setRegistrationDate("12/10/23");
        response.setExpiryDate("12/10/24");

        HostDomainMapEntity entity = new HostDomainMapEntity();
        entity.setHostDomainId("HD001");
        entity.setDomainName("domain1");
        entity.setHostProvider("anusha");
        entity.setPayment("900");
        entity.setDuration("1 year");
        entity.setRegistrationDate("12/10/23");
        entity.setExpiryDate("12/10/24");

        Mockito.when(hostDomainMapRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(entity));
        Mockito.when(hostDomainMapMapper.entityToResponse(ArgumentMatchers.any())).thenReturn(response);

        HostDomainMapResponse hostDomainMapService = service.getAllHostDomainsById(entity.getHostDomainId());

        assertNotNull(hostDomainMapService);

        assertEquals("anusha", response.getHostProvider());
        assertEquals("HD001", response.getHostDomainId());
        assertEquals("domain1", response.getDomainName());
        assertEquals("900", response.getPayment());
        assertEquals("1 year", response.getDuration());
        assertEquals("12/10/23", response.getRegistrationDate());
        assertEquals("12/10/24", response.getExpiryDate());
    }

    @Test
    public void existsById() {
        Mockito.when(hostDomainMapRepository.existsById(ArgumentMatchers.anyString()))
                .thenReturn(Boolean.TRUE);

        Boolean response = service.existsById("HD002");
        System.out.println(response);
        assertNotNull(response);
        assertTrue(response);
    }

    @Test
    public void updateHostDomain() {
        String domainId = "HD001";
        HostDomainMapUpdateRequest request1 = new HostDomainMapUpdateRequest();
        request1.setDomainName("domain1");
        request1.setHostProvider("anusha");
        request1.setPayment("900");
        request1.setDuration("1 year");
        request1.setRegistrationDate("2023-10-12");
        request1.setExpiryDate("2024-10-12");

        HostDomainMapEntity entity1 = new HostDomainMapEntity();
        entity1.setHostDomainId("HD001");
        entity1.setDomainName("domain1");
        entity1.setHostProvider("anusha");
        entity1.setPayment("900");
        entity1.setDuration("1 year");
        entity1.setRegistrationDate("2023-10-12");
        entity1.setExpiryDate("2024-10-12");

        Mockito.when(hostDomainMapRepository.findByHostDomainId(ArgumentMatchers.anyString())).thenReturn(entity1);
        Mockito.when(hostDomainMapMapper.updateEntityFromRequest(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(entity1);

        Mockito.when(hostDomainMapRepository.save(entity1)).thenReturn(entity1);

        HostDomainMapEntity updatedEntity = service.updateHostDomain(domainId, request1);

        assertNotNull(updatedEntity);

        assertEquals("anusha", updatedEntity.getHostProvider());
        assertEquals("HD001", updatedEntity.getHostDomainId());
        assertEquals("domain1", updatedEntity.getDomainName());
        assertEquals("900", updatedEntity.getPayment());
        assertEquals("1 year", updatedEntity.getDuration());
        assertEquals("2023-10-12", updatedEntity.getRegistrationDate());
        assertEquals("2024-10-12", updatedEntity.getExpiryDate());
    }


    @Test
    public void deleteHostDomainById() {
        HostDomainMapEntity entity1 = new HostDomainMapEntity();
        entity1.setHostDomainId("HD001");
        entity1.setDomainName("domain1");
        entity1.setHostProvider("anusha");
        entity1.setPayment("900");
        entity1.setDuration("1 year");
        entity1.setRegistrationDate("12/10/23");
        entity1.setExpiryDate("12/10/24");

        Mockito.when(hostDomainMapRepository.findByHostDomainId(ArgumentMatchers.anyString())).thenReturn(entity1);
        service.deleteHostDomainById(entity1.getHostDomainId());

        verify(hostDomainMapRepository, times(1)).deleteById(entity1.getHostDomainId());

        assertEquals("anusha", entity1.getHostProvider());
        assertEquals("HD001", entity1.getHostDomainId());
        assertEquals("domain1", entity1.getDomainName());
        assertEquals("900", entity1.getPayment());
        assertEquals("1 year", entity1.getDuration());
        assertEquals("12/10/23", entity1.getRegistrationDate());
        assertEquals("12/10/24", entity1.getExpiryDate());
    }

    @Test
    public void getAllDomainNames() {

        List<String> sampleDomainNames = Arrays.asList("anusha", "keerthi", "mamatha");
        // Mock the behavior of domainRepository.findAllDomainNames() to return the sample domain names
        when(domainRepository.findAllDomainNames()).thenReturn(sampleDomainNames);
        // Call the method to be tested
        List<String> result = service.getAllDomainNames();
        System.out.println(result);
        // Verify that the domainRepository.findAllDomainNames() was called exactly once
        verify(domainRepository, times(1)).findAllDomainNames();
        // Assert that the returned list matches the sample domain names
        assertEquals(sampleDomainNames, result);
    }


    @Test
    public void getAllHostingProviders() {
        List<String> sampleHosting = Arrays.asList("aki", "anusha", "anshu");
        // Mock the behavior of domainRepository.findAllDomainNames() to return the sample domain names
        when(hostingRepository.findAllHostingProvider()).thenReturn(sampleHosting);
        // Call the method to be tested
        List<String> result = service.getAllHostingProviders();
        System.out.println(result);
        // Verify that the domainRepository.findAllDomainNames() was called exactly once
        verify(hostingRepository, times(1)).findAllHostingProvider();
        // Assert that the returned list matches the sample domain names
        assertEquals(sampleHosting, result);
    }
}
