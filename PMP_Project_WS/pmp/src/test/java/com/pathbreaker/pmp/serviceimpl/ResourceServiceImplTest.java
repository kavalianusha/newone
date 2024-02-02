package com.pathbreaker.pmp.serviceimpl;

import com.pathbreaker.pmp.entity.ResourceEntity;
import com.pathbreaker.pmp.entity.ResourceSkillsEntity;
import com.pathbreaker.pmp.mappers.ResourceMapper;
import com.pathbreaker.pmp.mappers.ResourceSkillsMapper;
import com.pathbreaker.pmp.repository.ResourceRepository;
import com.pathbreaker.pmp.repository.ResourceSkillRepository;
import com.pathbreaker.pmp.request.ResourceRequest;
import com.pathbreaker.pmp.request.ResourceSkillsRequest;
import com.pathbreaker.pmp.request.ResourceSkillsUpdateRequest;
import com.pathbreaker.pmp.request.ResourceUpdateRequest;
import com.pathbreaker.pmp.response.ResourceResponse;
import com.pathbreaker.pmp.response.ResourceSkillsResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ResourceServiceImplTest {

    @Mock
    private ResourceRepository resourceRepository;
    @Mock
    private ResourceSkillRepository resourceSkillRepository;
    @Mock
    private ResourceMapper resourceMapper;
    @Mock
    private ResourceSkillsMapper resourceSkillsMapper;

    @Autowired
    public ResourceServiceImpl resourceService = new ResourceServiceImpl(resourceRepository,resourceMapper,resourceSkillsMapper,resourceSkillRepository);

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(resourceService, "resourceRepository", resourceRepository);
        ReflectionTestUtils.setField(resourceService, "resourceSkillsMapper", resourceSkillsMapper);
        ReflectionTestUtils.setField(resourceService,"resourceSkillRepository",resourceSkillRepository);
        ReflectionTestUtils.setField(resourceService,"resourceMapper",resourceMapper);
    }


    @Test
    public void generateEmpId() {
        String employeeId = "EMP001";
        Mockito.when(resourceRepository.findHighestEmpId()).thenReturn(employeeId);

        String result = resourceService.generateEmpId();
        System.out.println(result);

        assertNotNull(result);
        assertEquals("EMP002", result);

    }

    @Test
    public void createResource() {

        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setEmployeeId("EMP001");
        resourceEntity.setEmployeeName("Anusha");
        resourceEntity.setExperience(2);
        resourceEntity.setDesignation("Software Developer");
        resourceEntity.setDepartment("IT");
        resourceEntity.setTechnology("Java");
        resourceEntity.setManager("Sathar");
        resourceEntity.setProject("Spring MVC");

        ResourceSkillsEntity resourceSkillsEntity = new ResourceSkillsEntity();
        resourceSkillsEntity.setPrimarySkills("JAVA");
        resourceSkillsEntity.setSecondarySkills("SQL");
        resourceSkillsEntity.setTerinarySkills("WEB");
        resourceSkillsEntity.setSkillsPercentage(50);

        resourceEntity.setResourceSkillsEntity(resourceSkillsEntity);

        ResourceRequest resourceRequest = new ResourceRequest();
        resourceRequest.setEmployeeId("EMP001");
        resourceRequest.setEmployeeName("Anusha");
        resourceRequest.setExperience(2);
        resourceRequest.setDesignation("Software Developer");
        resourceRequest.setDepartment("IT");
        resourceRequest.setTechnology("Java");
        resourceRequest.setManager("Sathar");
        resourceRequest.setProject("Spring MVC");

        ResourceSkillsRequest resourceSkillsRequest = new ResourceSkillsRequest();
        resourceSkillsRequest.setPrimarySkills("JAVA");
        resourceSkillsRequest.setSecondarySkills("SQL");
        resourceSkillsRequest.setTerinarySkills("WEB");
        resourceSkillsRequest.setSkillsPercentage(50);

        resourceRequest.setResourceSkillsRequest(resourceSkillsRequest);

        Mockito.when(resourceMapper.entityToRequest(ArgumentMatchers.any())).thenReturn(resourceEntity);
        Mockito.when(resourceSkillsMapper.entityToRequest(ArgumentMatchers.any())).thenReturn(resourceSkillsEntity);

        ResponseEntity<?> result = resourceService.createResource(resourceRequest);

        assertNotNull(result);
        assertEquals("EMP001",resourceRequest.getEmployeeId());
        assertEquals("Anusha",resourceRequest.getEmployeeName());
        assertEquals(2, resourceRequest.getExperience().intValue());
        assertEquals("Software Developer",resourceRequest.getDesignation());
        assertEquals("IT",resourceRequest.getDepartment());
        assertEquals("Java",resourceRequest.getTechnology());
        assertEquals("Sathar",resourceRequest.getManager());
        assertEquals("Spring MVC",resourceRequest.getProject());
        assertEquals("JAVA",resourceRequest.getResourceSkillsRequest().getPrimarySkills());
        assertEquals("SQL",resourceRequest.getResourceSkillsRequest().getSecondarySkills());
        assertEquals("WEB",resourceRequest.getResourceSkillsRequest().getTerinarySkills());
        assertEquals(50, resourceRequest.getResourceSkillsRequest().getSkillsPercentage().intValue());

    }

    @Test
    public void getAllResources() {
        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setEmployeeId("EMP001");
        resourceEntity.setEmployeeName("Anusha");
        resourceEntity.setExperience(2);
        resourceEntity.setDesignation("Software Developer");
        resourceEntity.setDepartment("IT");
        resourceEntity.setTechnology("Java");
        resourceEntity.setManager("Sathar");
        resourceEntity.setProject("Spring MVC");

        ResourceSkillsEntity resourceSkillsEntity = new ResourceSkillsEntity();
        resourceSkillsEntity.setPrimarySkills("JAVA");
        resourceSkillsEntity.setSecondarySkills("SQL");
        resourceSkillsEntity.setTerinarySkills("WEB");
        resourceSkillsEntity.setSkillsPercentage(50);

        resourceEntity.setResourceSkillsEntity(resourceSkillsEntity);

        ResourceResponse resourceResponse = new ResourceResponse();
        resourceResponse.setEmployeeId("EMP001");
        resourceResponse.setEmployeeName("Anusha");
        resourceResponse.setExperience(2);
        resourceResponse.setDesignation("Software Developer");
        resourceResponse.setDepartment("IT");
        resourceResponse.setTechnology("Java");
        resourceResponse.setManager("Sathar");
        resourceResponse.setProject("Spring MVC");

        ResourceSkillsResponse resourceSkillsResponse = new ResourceSkillsResponse();
        resourceSkillsResponse.setPrimarySkills("JAVA");
        resourceSkillsResponse.setSecondarySkills("SQL");
        resourceSkillsResponse.setTerinarySkills("WEB");
        resourceSkillsResponse.setSkillsPercentage(50);

        resourceResponse.setResourceSkillsResponse(resourceSkillsResponse);

        ResourceEntity resourceEntity1 = new ResourceEntity();
        resourceEntity1.setEmployeeId("EMP001");
        resourceEntity1.setEmployeeName("Anusha");
        resourceEntity1.setExperience(2);
        resourceEntity1.setDesignation("Software Developer");
        resourceEntity1.setDepartment("IT");
        resourceEntity1.setTechnology("Java");
        resourceEntity1.setManager("Sathar");
        resourceEntity1.setProject("Spring MVC");

        ResourceSkillsEntity resourceSkillsEntity1 = new ResourceSkillsEntity();
        resourceSkillsEntity1.setPrimarySkills("JAVA");
        resourceSkillsEntity1.setSecondarySkills("SQL");
        resourceSkillsEntity1.setTerinarySkills("WEB");
        resourceSkillsEntity1.setSkillsPercentage(50);

        resourceEntity1.setResourceSkillsEntity(resourceSkillsEntity1);

        ResourceResponse resourceResponse1 = new ResourceResponse();
        resourceResponse1.setEmployeeId("EMP001");
        resourceResponse1.setEmployeeName("Anusha");
        resourceResponse1.setExperience(2);
        resourceResponse1.setDesignation("Software Developer");
        resourceResponse1.setDepartment("IT");
        resourceResponse1.setTechnology("Java");
        resourceResponse1.setManager("Sathar");
        resourceResponse1.setProject("Spring MVC");

        ResourceSkillsResponse resourceSkillsResponse1 = new ResourceSkillsResponse();
        resourceSkillsResponse1.setPrimarySkills("JAVA");
        resourceSkillsResponse1.setSecondarySkills("SQL");
        resourceSkillsResponse1.setTerinarySkills("WEB");
        resourceSkillsResponse1.setSkillsPercentage(50);

        resourceResponse1.setResourceSkillsResponse(resourceSkillsResponse1);

        List<ResourceEntity> resourceEntityList = new ArrayList<>();
        resourceEntityList.add(resourceEntity);
        resourceEntityList.add(resourceEntity1);

        Mockito.when(resourceRepository.findAll()).thenReturn(resourceEntityList);
        Mockito.when(resourceMapper.responseListToEntity(ArgumentMatchers.any())).thenReturn(resourceResponse);

        List<ResourceResponse> result = resourceService.getAllResources();

        assertNotNull(result);

        assertEquals("EMP001",result.get(0).getEmployeeId());
        assertEquals("Anusha",result.get(0).getEmployeeName());
        assertEquals(2, result.get(0).getExperience().intValue());
        assertEquals("Software Developer",result.get(0).getDesignation());
        assertEquals("IT",result.get(0).getDepartment());
        assertEquals("Java",result.get(0).getTechnology());
        assertEquals("Sathar",result.get(0).getManager());
        assertEquals("Spring MVC",result.get(0).getProject());
        assertEquals("JAVA",result.get(0).getResourceSkillsResponse().getPrimarySkills());
        assertEquals("SQL",result.get(0).getResourceSkillsResponse().getSecondarySkills());
        assertEquals("WEB",result.get(0).getResourceSkillsResponse().getTerinarySkills());
        assertEquals(50, result.get(0).getResourceSkillsResponse().getSkillsPercentage().intValue());
    }

    @Test
    public void getAllResourceById() {
        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setEmployeeId("EMP001");
        resourceEntity.setEmployeeName("Anusha");
        resourceEntity.setExperience(2);
        resourceEntity.setDesignation("Software Developer");
        resourceEntity.setDepartment("IT");
        resourceEntity.setTechnology("Java");
        resourceEntity.setManager("Sathar");
        resourceEntity.setProject("Spring MVC");

        ResourceSkillsEntity resourceSkillsEntity = new ResourceSkillsEntity();
        resourceSkillsEntity.setPrimarySkills("JAVA");
        resourceSkillsEntity.setSecondarySkills("SQL");
        resourceSkillsEntity.setTerinarySkills("WEB");
        resourceSkillsEntity.setSkillsPercentage(50);

        resourceEntity.setResourceSkillsEntity(resourceSkillsEntity);

        ResourceResponse resourceResponse = new ResourceResponse();
        resourceResponse.setEmployeeId("EMP001");
        resourceResponse.setEmployeeName("Anusha");
        resourceResponse.setExperience(2);
        resourceResponse.setDesignation("Software Developer");
        resourceResponse.setDepartment("IT");
        resourceResponse.setTechnology("Java");
        resourceResponse.setManager("Sathar");
        resourceResponse.setProject("Spring MVC");

        ResourceSkillsResponse resourceSkillsResponse = new ResourceSkillsResponse();
        resourceSkillsResponse.setPrimarySkills("JAVA");
        resourceSkillsResponse.setSecondarySkills("SQL");
        resourceSkillsResponse.setTerinarySkills("WEB");
        resourceSkillsResponse.setSkillsPercentage(50);

        resourceResponse.setResourceSkillsResponse(resourceSkillsResponse);

        Mockito.when(resourceRepository.findByEmployeeId(ArgumentMatchers.anyString())).thenReturn(Optional.of(resourceEntity));
        Mockito.when(resourceMapper.responseToEntity(ArgumentMatchers.any())).thenReturn(resourceResponse);

        ResourceResponse result = resourceService.getAllResourceById("EMP001");

        assertNotNull(result);

        assertEquals("EMP001",result.getEmployeeId());
        assertEquals("Anusha",result.getEmployeeName());
        assertEquals(2, result.getExperience().intValue());
        assertEquals("Software Developer",result.getDesignation());
        assertEquals("IT",result.getDepartment());
        assertEquals("Java",result.getTechnology());
        assertEquals("Sathar",result.getManager());
        assertEquals("Spring MVC",result.getProject());
        assertEquals("JAVA",result.getResourceSkillsResponse().getPrimarySkills());
        assertEquals("SQL",result.getResourceSkillsResponse().getSecondarySkills());
        assertEquals("WEB",result.getResourceSkillsResponse().getTerinarySkills());
        assertEquals(50, result.getResourceSkillsResponse().getSkillsPercentage().intValue());

    }

    @Test
    public void updateResourceById() {
        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setEmployeeId("EMP001");
        resourceEntity.setEmployeeName("Anusha");
        resourceEntity.setExperience(2);
        resourceEntity.setDesignation("Software Developer");
        resourceEntity.setDepartment("IT");
        resourceEntity.setTechnology("Java");
        resourceEntity.setManager("Sathar");
        resourceEntity.setProject("Spring MVC");

        ResourceSkillsEntity resourceSkillsEntity = new ResourceSkillsEntity();
        resourceSkillsEntity.setPrimarySkills("JAVA");
        resourceSkillsEntity.setSecondarySkills("SQL");
        resourceSkillsEntity.setTerinarySkills("WEB");
        resourceSkillsEntity.setSkillsPercentage(50);

        resourceEntity.setResourceSkillsEntity(resourceSkillsEntity);

        ResourceUpdateRequest resourceUpdateRequest = new ResourceUpdateRequest();
        resourceUpdateRequest.setEmployeeName("Anusha");
        resourceUpdateRequest.setExperience(2);
        resourceUpdateRequest.setDesignation("Software Developer");
        resourceUpdateRequest.setDepartment("IT");
        resourceUpdateRequest.setTechnology("Java");
        resourceUpdateRequest.setManager("Sathar");
        resourceUpdateRequest.setProject("Spring MVC");

        ResourceSkillsUpdateRequest resourceSkillsUpdateRequest = new ResourceSkillsUpdateRequest();
        resourceSkillsUpdateRequest.setPrimarySkills("JAVA");
        resourceSkillsUpdateRequest.setSecondarySkills("SQL");
        resourceSkillsUpdateRequest.setTerinarySkills("WEB");
        resourceSkillsUpdateRequest.setSkillsPercentage(50);

        resourceUpdateRequest.setResourceSkillsUpdateRequest(resourceSkillsUpdateRequest);

        Mockito.when(resourceRepository.findByEmployeeId(ArgumentMatchers.anyString())).thenReturn(Optional.of(resourceEntity));
        Mockito.when(resourceMapper.updateEntityFromRequest(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(resourceEntity);
        Mockito.when(resourceSkillsMapper.updateEntityFromRequest(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(resourceSkillsEntity);

        ResponseEntity<?> result = resourceService.updateResourceById(resourceEntity.getEmployeeId(),resourceUpdateRequest);

        assertNotNull(result);

        assertEquals("Anusha",resourceUpdateRequest.getEmployeeName());
        assertEquals(2, resourceUpdateRequest.getExperience().intValue());
        assertEquals("Software Developer",resourceUpdateRequest.getDesignation());
        assertEquals("IT",resourceUpdateRequest.getDepartment());
        assertEquals("Java",resourceUpdateRequest.getTechnology());
        assertEquals("Sathar",resourceUpdateRequest.getManager());
        assertEquals("Spring MVC",resourceUpdateRequest.getProject());
        assertEquals("JAVA",resourceUpdateRequest.getResourceSkillsUpdateRequest().getPrimarySkills());
        assertEquals("SQL",resourceUpdateRequest.getResourceSkillsUpdateRequest().getSecondarySkills());
        assertEquals("WEB",resourceUpdateRequest.getResourceSkillsUpdateRequest().getTerinarySkills());
        assertEquals(50, resourceUpdateRequest.getResourceSkillsUpdateRequest().getSkillsPercentage().intValue());


    }

    @Test
    public void deleteResourceById() {
        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setEmployeeId("EMP001");
        resourceEntity.setEmployeeName("Anusha");
        resourceEntity.setExperience(2);
        resourceEntity.setDesignation("Software Developer");
        resourceEntity.setDepartment("IT");
        resourceEntity.setTechnology("Java");
        resourceEntity.setManager("Sathar");
        resourceEntity.setProject("Spring MVC");

        ResourceSkillsEntity resourceSkillsEntity = new ResourceSkillsEntity();
        resourceSkillsEntity.setId(1L);
        resourceSkillsEntity.setPrimarySkills("JAVA");
        resourceSkillsEntity.setSecondarySkills("SQL");
        resourceSkillsEntity.setTerinarySkills("WEB");
        resourceSkillsEntity.setSkillsPercentage(50);

        resourceEntity.setResourceSkillsEntity(resourceSkillsEntity);

        Mockito.when(resourceRepository.findByEmployeeId(ArgumentMatchers.anyString())).thenReturn(Optional.of(resourceEntity));

        ResponseEntity<?> result = resourceService.deleteResourceById(resourceEntity.getEmployeeId());

        assertNotNull(result);
        assertEquals("EMP001",resourceEntity.getEmployeeId());
        assertEquals("Anusha",resourceEntity.getEmployeeName());
        assertEquals(2, resourceEntity.getExperience().intValue());
        assertEquals("Software Developer",resourceEntity.getDesignation());
        assertEquals("IT",resourceEntity.getDepartment());
        assertEquals("Java",resourceEntity.getTechnology());
        assertEquals("Sathar",resourceEntity.getManager());
        assertEquals("Spring MVC",resourceEntity.getProject());
        assertEquals("JAVA",resourceEntity.getResourceSkillsEntity().getPrimarySkills());
        assertEquals("SQL",resourceEntity.getResourceSkillsEntity().getSecondarySkills());
        assertEquals("WEB",resourceEntity.getResourceSkillsEntity().getTerinarySkills());
        assertEquals(50, resourceEntity.getResourceSkillsEntity().getSkillsPercentage().intValue());
        assertEquals(1L, resourceEntity.getResourceSkillsEntity().getId().longValue());




    }
}