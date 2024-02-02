package com.example.efilingbazaar.service;

import com.example.efilingbazaar.entity.MainServiceEntity;
import com.example.efilingbazaar.entity.SubServiceEntity;
import com.example.efilingbazaar.mapper.SubServiceMapper;
import com.example.efilingbazaar.repository.SubServiceRepository;
import com.example.efilingbazaar.request.MainServiceRequest;
import com.example.efilingbazaar.request.SubServiceRequest;
import com.example.efilingbazaar.request.SubServiceRequest2;
import com.example.efilingbazaar.response.MainServiceResponse;
import com.example.efilingbazaar.response.SubServiceResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SubServiceTest {

    @Mock
    private SubServiceRepository subServiceRepository;

    @Mock
    private SubServiceMapper subServiceMapper;

    private String PATH;

    private SubService subService;

    @Before
    public void setUp() throws Exception {
        PATH = System.getProperty("java.io.tmpdir") + "SubServiceImages/";
        subService = new SubService(subServiceRepository, subServiceMapper, PATH);
        ReflectionTestUtils.setField(subService,"subServiceRepository",subServiceRepository);
        ReflectionTestUtils.setField(subService,"subServiceMapper",subServiceMapper);
    }


    @Test
    public void createSubService() throws Exception{
        SubServiceRequest subServiceRequest = new SubServiceRequest();
        subServiceRequest.setSubServiceName("ITR filing");
        subServiceRequest.setSubServiceId("SS001");
        subServiceRequest.setSubServiceShortName("ITR");
        subServiceRequest.setProsAndCons("pros and cons");
        subServiceRequest.setDescription("ItR filing description");
        subServiceRequest.setGovernmentSection("government section");
        subServiceRequest.setLiable("liable");
        subServiceRequest.setCreatedBy("anusha");
        subServiceRequest.setStatus(true);
        subServiceRequest.setMainServiceEntity(new MainServiceEntity());
        subServiceRequest.setCreatedDate(LocalDate.now());
        List<MultipartFile> subfiles1 = new ArrayList<>();
        List<String> subServiceFile1 = new ArrayList<>();
        subServiceFile1.add(subfiles1.toString());
        subServiceFile1.add(subfiles1.toString());
        subServiceRequest.setSubServiceFilePaths(subServiceFile1);

        System.out.println(subServiceRequest);

        SubServiceEntity entity = new SubServiceEntity();
        entity.setSubServiceName("ITR filing");
        entity.setSubServiceId("SS002");
        entity.setSubServiceShortName("ITR");
        entity.setProsAndCons("pros and cons");
        entity.setDescription("Itr filing description");
        entity.setGovernmentSection("government section");
        entity.setLiable("liable");
        entity.setCreatedBy("anusha");
        entity.setStatus(true);
        entity.setMainServiceEntity(new MainServiceEntity());
        entity.setCreatedDate(LocalDate.now());
        List<MultipartFile> subfiles2 = new ArrayList<>();
        List<String> subServiceFile2 = new ArrayList<>();
        subServiceFile2.add(subfiles2.toString());
        subServiceFile2.add(subfiles2.toString());
        entity.setSubServiceFilePaths(subServiceFile2);
        System.out.println(entity);
        Mockito.when(subServiceMapper.toRequestEntity(any()))
                .thenReturn(entity);
        Mockito.when(subServiceRepository
                        .findHighestSubServiceId())
                .thenReturn("SS001");
        Mockito.when(subServiceRepository
                        .save(entity))
                .thenReturn(entity);

        SubServiceEntity response = subService.createSubService(subServiceRequest, subfiles2);
        Assert.assertNotNull(response);
        Assert.assertEquals("ITR filing",response.getSubServiceName());
        Assert.assertEquals("ITR",response.getSubServiceShortName());
        Assert.assertEquals("pros and cons",response.getProsAndCons());
        Assert.assertEquals("Itr filing description", response.getDescription());
        Assert.assertEquals("government section", response.getGovernmentSection());
        Assert.assertTrue(response.getSubServiceFilePaths().contains(subfiles1.toString()));
        Assert.assertTrue(response.getSubServiceFilePaths().contains(subfiles2.toString()));

    }
    @Test
    public void getSubServiceById() {
        SubServiceRequest2 subService1 = new SubServiceRequest2();
        subService1.setSubServiceName("Sub service 1");
        subService1.setDescription("Description for Sub service 1");
        subService1.setStatus(true);
        subService1.setCreatedBy("anusha");
        subService1.setCreatedDate(LocalDate.now());
        subService1.setSubServiceShortName("ITR");
        subService1.setSubServiceName("SUB");
        subService1.setGovernmentSection("Government session");
        subService1.setLiable("liable");
        subService1.setProsAndCons("pros");
        subService1.setSubServiceId("SS002");
        subService1.setUpdatedDate(LocalDate.now());
        List<MultipartFile> subfiles1 = new ArrayList<>();
        List<String> subServiceFile1 = new ArrayList<>();
        subServiceFile1.add(subfiles1.toString());
        subServiceFile1.add(subfiles1.toString());
        subService1.setSubServiceFilePaths(subServiceFile1);
        subService1.setMainServiceEntity(new MainServiceRequest());


        MainServiceRequest mainService = new MainServiceRequest();
        mainService.setMainServiceId("MS001");
        mainService.setMainServiceName("ITR filing");
        mainService.setMainServiceShortName("ITR");
        mainService.setProsAndCons("main service pros and cons");
        mainService.setDescription("main service Itr filing description");
        mainService.setGovernmentSection("main service Government session");
        mainService.setLiable("liable");
        mainService.setCreatedBy("anusha");
        mainService.setStatus(true);
        mainService.setCreatedDate(LocalDate.now());
        mainService.setUpdatedDate(LocalDate.now());
        List<MultipartFile> mainfiles = new ArrayList<>();
        List<String> mainServiceFiles = new ArrayList<>();
        mainServiceFiles.add(mainfiles.toString());
        mainServiceFiles.add(mainfiles.toString());
        mainService.setMainServiceFilePaths(mainServiceFiles);

        subService1.setMainServiceEntity(mainService);
        System.out.println(subService);

        SubServiceEntity entity = new SubServiceEntity();
        Mockito.when(subServiceRepository
                        .findBySubServiceId(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(entity));
        Mockito.when(subServiceMapper
                        .entityToRequest(any()))
                .thenReturn(subService1);

        SubServiceRequest2 result = subService.getSubServiceById(subService1.getSubServiceId());
        assertNotNull(result);
        Assert.assertEquals("SUB",subService1.getSubServiceName());
        Assert.assertEquals("ITR",subService1.getSubServiceShortName());
        Assert.assertEquals("pros",subService1.getProsAndCons());

        Assert.assertEquals("main service Government session",
                subService1.getMainServiceEntity().getGovernmentSection());
        Assert.assertEquals(true, subService1.getMainServiceEntity().isStatus());
        Assert.assertEquals("anusha", subService1.getMainServiceEntity().getCreatedBy());
        Assert.assertEquals("liable",subService1.getMainServiceEntity().getLiable());
        Assert.assertTrue(result.getSubServiceFilePaths().contains(subfiles1.toString()));
        Assert.assertTrue(result.getMainServiceEntity().getMainServiceFilePaths().contains(mainfiles.toString()));
    }

    @Test
    public void getAllSubServicesWithMainServices(){

        SubServiceEntity entity1 = new SubServiceEntity();
        entity1.setSubServiceName("ITR filing");
        entity1.setSubServiceShortName("ITR");
        entity1.setProsAndCons("pros and cons");
        List<MultipartFile> subfiles1 = new ArrayList<>();
        List<String> subServiceFile1 = new ArrayList<>();
        subServiceFile1.add(subfiles1.toString());
        subServiceFile1.add(subfiles1.toString());
        entity1.setSubServiceFilePaths(subServiceFile1);

        MainServiceEntity mainServiceEntity1 = new MainServiceEntity();
        mainServiceEntity1.setMainServiceId("MS001");
        mainServiceEntity1.setDescription("description of main service1");
        mainServiceEntity1.setMainServiceShortName("GST");
        List<MultipartFile> mainfiles1 = new ArrayList<>();
        List<String> mainServiceFile1 = new ArrayList<>();
        mainServiceFile1.add(mainfiles1.toString());
        mainServiceFile1.add(mainfiles1.toString());
        mainServiceEntity1.setMainServiceFilePaths(mainServiceFile1);

        entity1.setMainServiceEntity(mainServiceEntity1);

        SubServiceEntity entity2 = new SubServiceEntity();
        entity2.setSubServiceName("Goods and files");
        entity2.setSubServiceShortName("GST");
        entity2.setStatus(false);
        List<MultipartFile> subfiles2 = new ArrayList<>();
        List<String> subServiceFile2 = new ArrayList<>();
        subServiceFile2.add(subfiles2.toString());
        subServiceFile2.add(subfiles2.toString());
        entity2.setSubServiceFilePaths(subServiceFile2);

        MainServiceEntity mainServiceEntity2 = new MainServiceEntity();
        mainServiceEntity2.setMainServiceId("MS002");
        mainServiceEntity2.setDescription("description of main service2");
        mainServiceEntity2.setMainServiceShortName("GST");
        List<MultipartFile> mainfiles2 = new ArrayList<>();
        List<String> mainServiceFile2 = new ArrayList<>();
        mainServiceFile2.add(mainfiles2.toString());
        mainServiceFile2.add(mainfiles2.toString());
        mainServiceEntity1.setMainServiceFilePaths(mainServiceFile2);
        entity2.setMainServiceEntity(mainServiceEntity2);

        // Create the list of entities
        List<SubServiceEntity> entityList = new ArrayList<>();
        entityList.add(entity1);
        entityList.add(entity2);

        List<SubServiceRequest2> entities = new ArrayList<>();
        SubServiceRequest2 subServiceRequest1 = new SubServiceRequest2();
        subServiceRequest1.setSubServiceName("ITR filing");
        subServiceRequest1.setSubServiceShortName("ITR");
        subServiceRequest1.setProsAndCons("pros and cons");
        subServiceRequest1.setSubServiceId("SS002");
        List<MultipartFile> subfiles3 = new ArrayList<>();
        List<String> subServiceFile3 = new ArrayList<>();
        subServiceFile3.add(subfiles3.toString());
        subServiceFile3.add(subfiles3.toString());
        subServiceRequest1.setSubServiceFilePaths(subServiceFile3);

        List<MainServiceRequest> entity = new ArrayList<>();
        MainServiceRequest request1 = new MainServiceRequest();
        request1.setMainServiceId("MS001");
        request1.setDescription("description of main service1");
        request1.setMainServiceShortName("GST");
        List<MultipartFile> mainfiles3 = new ArrayList<>();
        List<String> mainServiceFile3 = new ArrayList<>();
        mainServiceFile3.add(mainfiles3.toString());
        mainServiceFile3.add(mainfiles3.toString());
        request1.setMainServiceFilePaths(mainServiceFile3);

        subServiceRequest1.setMainServiceEntity(request1);

        SubServiceRequest2 subServiceRequest2 = new SubServiceRequest2();
        subServiceRequest2.setSubServiceName("Goods and files");
        subServiceRequest2.setSubServiceShortName("GST");
        subServiceRequest2.setStatus(false);
        subServiceRequest2.setSubServiceId("SS003");
        List<MultipartFile> subfiles4 = new ArrayList<>();
        List<String> subServiceFile4 = new ArrayList<>();
        subServiceFile4.add(subfiles4.toString());
        subServiceFile4.add(subfiles4.toString());
        subServiceRequest2.setSubServiceFilePaths(subServiceFile4);


        MainServiceRequest request2 = new MainServiceRequest();
        request2.setMainServiceId("MS002");
        request2.setDescription("description of main service2");
        request2.setMainServiceShortName("GST");
        subServiceRequest2.setMainServiceEntity(request2);
        List<MultipartFile> mainfiles4 = new ArrayList<>();
        List<String> mainServiceFile4 = new ArrayList<>();
        mainServiceFile4.add(mainfiles4.toString());
        mainServiceFile4.add(mainfiles4.toString());
        request2.setMainServiceFilePaths(mainServiceFile4);

        entities.add(subServiceRequest1);
        entities.add(subServiceRequest2);

        Mockito.when(subServiceRepository.findAll()).thenReturn(entityList);

        Mockito.when(subServiceMapper.entityToRequest(any()))
               .thenReturn(subServiceRequest1, subServiceRequest2);

        List<SubServiceRequest2> responseResult = subService.getAllSubServicesWithMainServices();

        // Perform assertions
        assertNotNull(responseResult);
        assertEquals(2, responseResult.size());

        SubServiceRequest2 result1 = responseResult.get(0);
        assertEquals("ITR filing", result1.getSubServiceName());
        assertEquals("ITR", result1.getSubServiceShortName());
        assertEquals("pros and cons", result1.getProsAndCons());
        Assert.assertTrue(result1.getSubServiceFilePaths().contains(subfiles1.toString()));
        Assert.assertTrue(result1.getMainServiceEntity().getMainServiceFilePaths().contains(mainfiles1.toString()));
       // assertEquals(mainServiceEntity1, result1.getMainServiceEntity());

        SubServiceRequest2 result2 = responseResult.get(1);
        assertEquals("Goods and files", result2.getSubServiceName());
        assertEquals("GST", result2.getSubServiceShortName());
        assertFalse(result2.isStatus());
        Assert.assertTrue(result2.getSubServiceFilePaths().contains(subfiles2.toString()));
        Assert.assertTrue(result2.getMainServiceEntity().getMainServiceFilePaths().contains(mainfiles2.toString()));
       // assertEquals(mainServiceEntity2, result2.getMainServiceEntity());

        // Verify interactions with mocks
        Mockito.verify(subServiceRepository, Mockito.times(1)).findAll();
        Mockito.verify(subServiceMapper, Mockito.times(2)).entityToRequest(any());

    }

    @Test
    public void updatedSubService() throws Exception{
        SubServiceEntity subServiceEntity = new SubServiceEntity();
        subServiceEntity.setSubServiceName("ITR filing");
        subServiceEntity.setSubServiceShortName("ITR");
        subServiceEntity.setDescription("Itr filing description");
        subServiceEntity.setStatus(false);
        subServiceEntity.setUpdatedDate(LocalDate.now());
        List<MultipartFile> subfiles1 = new ArrayList<>();
        List<String> subServiceFile1 = new ArrayList<>();
        subServiceFile1.add(subfiles1.toString());
        subServiceFile1.add(subfiles1.toString());
        subServiceEntity.setSubServiceFilePaths(subServiceFile1);
        System.out.println(subServiceEntity);

        Mockito.when(subServiceRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(subServiceEntity));

        subServiceRepository.save(subServiceEntity);

        SubServiceRequest subrequest = new SubServiceRequest();
        subrequest.setSubServiceName("ITR filing");
        subrequest.setSubServiceShortName("ITR");
        subrequest.setDescription("Itr filing description");
        subrequest.setStatus(true);
        subrequest.setUpdatedDate(LocalDate.now());
        List<MultipartFile> subfiles2 = new ArrayList<>();
        List<String> subServiceFile2 = new ArrayList<>();
        subServiceFile2.add(subfiles2.toString());
        subServiceFile2.add(subfiles2.toString());
        subrequest.setSubServiceFilePaths(subServiceFile2);
        System.out.println(subrequest);

        SubServiceRequest response = subService
                .updatedSubService("SS002",subrequest,subfiles2);
        Assert.assertNotNull(response);
        Assert.assertEquals("ITR filing",response.getSubServiceName());
        Assert.assertEquals("ITR", response.getSubServiceShortName());
        Assert.assertEquals("Itr filing description",response.getDescription());
        Assert.assertEquals(true, response.isStatus());
        Assert.assertEquals(LocalDate.now(), response.getUpdatedDate());
        Assert.assertTrue(response.getSubServiceFilePaths().contains(subfiles1.toString()));


    }

/*    @Test
    public void deleteSubServiceById() throws Exception{
        SubServiceEntity subServiceEntity = new SubServiceEntity();
        subServiceEntity.setSubServiceId("SS002");
        subServiceEntity.setSubServiceName("ITR filing");
        subServiceEntity.setSubServiceShortName("ITR");
        subServiceEntity.setProsAndCons("pros and cons");
        subServiceEntity.setDescription("Itr filing description");
        subServiceEntity.setGovernmentSection("government section");
        subServiceEntity.setLiable("liable");
        subServiceEntity.setCreatedBy("anusha");
        subServiceEntity.setStatus(true);
        subServiceEntity.setCreatedDate(LocalDate.now());
        subServiceEntity.setUpdatedDate(LocalDate.now());
        List<MultipartFile> subfiles1 = new ArrayList<>();
        List<String> subServiceFile1 = new ArrayList<>();
        subServiceFile1.add(subfiles1.toString());
        subServiceFile1.add(subfiles1.toString());
        subServiceEntity.setSubServiceFilePaths(subServiceFile1);

        Mockito.when(subServiceRepository.findBySubServiceId(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(subServiceEntity));
        Mockito.when(s)

        SubServiceEntity response = subService.deleteSubServiceById("SS002");
        // Verify that repository methods were called appropriately
        Mockito.verify(subServiceRepository, Mockito.times(1)).findBySubServiceId(ArgumentMatchers.anyString());
        Mockito.verify(subServiceRepository, Mockito.times(1)).delete(any());

        // Additional assertions if needed
        Assert.assertNotNull(response);
        Assert.assertEquals(subServiceEntity.getSubServiceId(), response.getSubServiceId());

    }*/

   @Test
    public void existsById() {
        Mockito.when(subServiceRepository.existsById(ArgumentMatchers.anyString()))
                .thenReturn(Boolean.FALSE);

       Boolean response = subService.existsById("SS002");
       Assert.assertNotNull(response);
       Assert.assertFalse(response);
    }
}