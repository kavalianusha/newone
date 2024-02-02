package com.example.efilingbazaar.service;


import com.example.efilingbazaar.entity.MainServiceEntity;
import com.example.efilingbazaar.entity.SubServiceEntity;
import com.example.efilingbazaar.mapper.MainServiceMapper;
import com.example.efilingbazaar.repository.MainServiceRepository;
import com.example.efilingbazaar.request.MainServiceRequest;
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
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;
import java.time.LocalDateTime;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MainServiceTest {

    @Mock
    private MainServiceRepository mainServiceRepository;

    @Mock
    private MainServiceMapper mainServiceMapper;

    @Mock
    private String PATH;

    @Mock
    private List<MultipartFile> files;

    private MainService mainService = new MainService(mainServiceRepository, mainServiceMapper,PATH);

    @Before
    public void setUp() throws Exception {
        ReflectionTestUtils.setField(mainService,"mainServiceRepository",mainServiceRepository);
        ReflectionTestUtils.setField(mainService,"mainServiceMapper",mainServiceMapper);
    }

    @Test
    public void createMainService() throws Exception {
        MainServiceRequest mainServiceRequest = new MainServiceRequest();
        mainServiceRequest.setMainServiceName("ITR filing");
        mainServiceRequest.setMainServiceId("MS002");
        mainServiceRequest.setMainServiceShortName("ITR");
        mainServiceRequest.setProsAndCons("pros and cons");
        mainServiceRequest.setDescription("ItR filing description");
        mainServiceRequest.setGovernmentSection("government section");
        mainServiceRequest.setLiable("liable");
        mainServiceRequest.setCreatedBy("anusha");
        mainServiceRequest.setStatus(true);
        mainServiceRequest.setCreatedDate(LocalDate.now());
        System.out.println(mainServiceRequest);

        MainServiceEntity entity = new MainServiceEntity();
        entity.setMainServiceName("ITR filing");
        entity.setMainServiceId("MS002");
        entity.setMainServiceShortName("ITR");
        entity.setProsAndCons("pros and cons");
        entity.setDescription("Itr filing description");
        entity.setGovernmentSection("government section");
        entity.setLiable("liable");
        entity.setCreatedBy("anusha");
        entity.setStatus(true);
        entity.setCreatedDate(LocalDate.now());
      /*  File testFile = ResourceUtils.getFile(this.getClass().getResource("/file.png"));
        entity.setFileData(readFileToBytes(testFile));*/

        Mockito.when(mainServiceRepository
                        .existsByMainServiceName(ArgumentMatchers.anyString()))
                .thenReturn(Boolean.FALSE);
        Mockito.when(mainServiceMapper
                        .toMainServiceEntity(ArgumentMatchers.any()))
                .thenReturn(entity);
        Mockito.when(mainServiceRepository
                        .findHighestMainServiceId())
                .thenReturn("MS001");
        Mockito.when(mainServiceRepository
                        .save(entity))
                .thenReturn(entity);

       /* System.out.println("test file is :"+testFile);
        FileInputStream stream = new FileInputStream(testFile);
        MultipartFile file = new MockMultipartFile("data", "file.png", "multipart/form-data",stream);*/
        MainServiceEntity response = mainService.createMainService(mainServiceRequest, files);
        Assert.assertNotNull(response);
        Assert.assertEquals("ITR filing",response.getMainServiceName());
        Assert.assertEquals("ITR",response.getMainServiceShortName());
        Assert.assertEquals("pros and cons",response.getProsAndCons());
        Assert.assertEquals("Itr filing description", response.getDescription());
        Assert.assertEquals("government section", response.getGovernmentSection());

    }
    private static byte[] readFileToBytes(File file) throws IOException {

        byte[] bytes = new byte[(int) file.length()];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(bytes);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return bytes;
    }

    @Test
    public void getMainServiceById() {
        MainServiceResponse response = new MainServiceResponse();
        response.setMainServiceId("MS001");
        response.setMainServiceName("ITR filing");
        response.setMainServiceShortName("ITR");
        response.setProsAndCons("pros and cons");
        response.setDescription("ItR filing description");
        response.setGovernmentSection("government section");
        response.setLiable("liable");
        response.setCreatedBy("anusha");
        response.setStatus(true);
        response.setCreatedDate(LocalDate.now());
        response.setUpdatedDate(LocalDate.now());
        List<SubServiceResponse> subServices = new ArrayList<>();

        SubServiceResponse subService1 = new SubServiceResponse();
        subService1.setSubServiceName("Sub service 1");
        subService1.setDescription("Description for Sub service 1");
        subService1.setStatus(true);
        subService1.setCreatedBy("anusha");
        subService1.setCreatedDate(LocalDate.now());
        subService1.setSubServiceShortName("ITR");
        subService1.setSubServiceName("SUB");
        subService1.setGovernmentSection("Government session");
        subService1.setLiable("liable");
        subService1.setProsAndCons("pros and corns");
        subService1.setSubServiceId("SS002");
        subService1.setUpdatedDate(LocalDate.now());

        SubServiceResponse subService2 = new SubServiceResponse();
        subService2.setSubServiceName("Sub service 2");
        subService2.setDescription("Description for Sub service 2");
        subService2.setStatus(true);
        subService2.setCreatedBy("anusha");
        subService2.setCreatedDate(LocalDate.now());
        subService2.setSubServiceShortName("SUB");
        subService2.setGovernmentSection("Government session");
        subService2.setLiable("liable");
        subService2.setProsAndCons("pros and corns");
        subService2.setSubServiceId("SS003");
        subService2.setUpdatedDate(LocalDate.now());

        subServices.add(subService1);
        subServices.add(subService2);

        response.setSubServices(subServices);
        System.out.println(response);

        MainServiceEntity entity = new MainServiceEntity();
        Mockito.when(mainServiceRepository
                        .findByMainServiceId(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(entity));
        Mockito.when(mainServiceMapper
                        .toResponse(ArgumentMatchers.any()))
                .thenReturn(response);

        MainServiceResponse result = mainService.getMainServiceById(response.getMainServiceId());
        Assert.assertNotNull(result);
        Assert.assertEquals("ITR filing",response.getMainServiceName());
        Assert.assertEquals("ITR",response.getMainServiceShortName());
        Assert.assertEquals("pros and cons",response.getProsAndCons());

        Assert.assertEquals("Description for Sub service 1",
                response.getSubServices().get(0).getDescription());
        Assert.assertEquals(true, response.getSubServices().get(0).isStatus());
        Assert.assertEquals("anusha", response.getSubServices().get(0).getCreatedBy());

        Assert.assertEquals("Description for Sub service 2",
                response.getSubServices().get(1).getDescription());
        Assert.assertEquals(true, response.getSubServices().get(1).isStatus());
        Assert.assertEquals("anusha", response.getSubServices().get(1).getCreatedBy());
        Assert.assertEquals("liable",response.getSubServices().get(1).getLiable());
    }

    @Test
    public void getAllMainServicesWithSubServices()  {

        MainServiceEntity entity1 = new MainServiceEntity();
        entity1.setMainServiceId("MS001");
        entity1.setMainServiceName("ITR filing");
        entity1.setMainServiceShortName("ITR");
        entity1.setProsAndCons("pros and cons");
        entity1.setDescription("ItR filing description");
        entity1.setGovernmentSection("government section");
        entity1.setLiable("liable");
        entity1.setCreatedBy("anusha");
        entity1.setStatus(true);
        entity1.setCreatedDate(LocalDate.now());
        entity1.setUpdatedDate(LocalDate.now());
        // Set other properties for entity1 as needed
        List<SubServiceEntity> subServices = new ArrayList<>();

        SubServiceEntity subService1 = new SubServiceEntity();
        subService1.setSubServiceName("Sub service 1");
        subService1.setDescription("Description for Sub service 1");
        subService1.setStatus(true);
        subService1.setCreatedBy("anusha");
        subService1.setCreatedDate(LocalDate.now());
        subService1.setSubServiceShortName("ITR");
        subService1.setSubServiceName("SUB");
        subService1.setGovernmentSection("Government session");
        subService1.setLiable("liable");
        subService1.setProsAndCons("pros and corns");
        subService1.setSubServiceId("SS002");
        subService1.setUpdatedDate(LocalDate.now());
        subService1.setMainServiceEntity(entity1);

        SubServiceEntity subService2 = new SubServiceEntity();
        subService2.setSubServiceName("Sub service 2");
        subService2.setDescription("Description for Sub service 2");
        subService2.setStatus(true);
        subService2.setCreatedBy("anusha");
        subService2.setCreatedDate(LocalDate.now());
        subService2.setSubServiceShortName("SUB");
        subService2.setGovernmentSection("Government session");
        subService2.setLiable("liable");
        subService2.setProsAndCons("pros and corns");
        subService2.setSubServiceId("SS003");
        subService2.setUpdatedDate(LocalDate.now());
        subService1.setMainServiceEntity(entity1);

        subServices.add(subService1);
        subServices.add(subService2);

        entity1.setSubServices(subServices);
        //System.out.println(entity1);
        // Set other properties for subService1 as needed
        // entity1.addSubService(subService1);
        MainServiceEntity response1 = new MainServiceEntity();
        response1.setMainServiceId("MS002");
        response1.setMainServiceName("ITR filing");
        response1.setMainServiceShortName("ITR");
        response1.setProsAndCons("pros and cons");
        response1.setDescription("ItR filing description");
        response1.setGovernmentSection("government section");
        response1.setLiable("liable");
        response1.setCreatedBy("anusha");
        response1.setStatus(true);
        response1.setCreatedDate(LocalDate.now());
        response1.setUpdatedDate(LocalDate.now());
        List<SubServiceEntity> subServicess = new ArrayList<>();

        SubServiceEntity subService3 = new SubServiceEntity();
        subService3.setSubServiceName("Sub service 3");
        subService3.setDescription("Description for Sub service 1");
        subService3.setStatus(true);
        subService3.setCreatedBy("anusha");
        subService3.setCreatedDate(LocalDate.now());
        subService3.setSubServiceShortName("ITR");
        subService3.setSubServiceName("SUB");
        subService3.setGovernmentSection("Government session");
        subService3.setLiable("liable");
        subService3.setProsAndCons("pros and corns");
        subService3.setSubServiceId("SS003");
        subService3.setUpdatedDate(LocalDate.now());
        subService3.setMainServiceEntity(response1);

        SubServiceEntity subService4 = new SubServiceEntity();
        subService4.setSubServiceName("Sub service 4");
        subService4.setDescription("Description for Sub service 2");
        subService4.setStatus(true);
        subService4.setCreatedBy("anusha");
        subService4.setCreatedDate(LocalDate.now());
        subService4.setSubServiceShortName("SUB");
        subService4.setGovernmentSection("Government session");
        subService4.setLiable("liable");
        subService4.setProsAndCons("pros and corns");
        subService4.setSubServiceId("SS004");
        subService4.setUpdatedDate(LocalDate.now());
        subService3.setMainServiceEntity(response1);

        subServicess.add(subService3);
        subServicess.add(subService4);
        //System.out.println("response 1 : " + response1);

        List<MainServiceEntity> mainServiceEntities = new ArrayList<>();
        mainServiceEntities.add(entity1);

        List<MainServiceResponse> mainServiceResponses = new ArrayList<>();
        MainServiceResponse response = new MainServiceResponse();
        response.setMainServiceName("ITR filing");
        response.setMainServiceShortName("ITR");
        response.setProsAndCons("pros and cons");

        List<SubServiceResponse> subServiceResponses = new ArrayList<>();
        SubServiceResponse subresponse = new SubServiceResponse();
        subresponse.setDescription("Description for Sub service 1");
        subresponse.setStatus(true);
        subresponse.setCreatedBy("anusha");
        subServiceResponses.add(subresponse);
        response.setSubServices(subServiceResponses);
        mainServiceResponses.add(response);
        // Mock repository behavior
        Mockito.when(mainServiceRepository.findAll())
                .thenReturn(mainServiceEntities);

        Mockito.when(mainServiceMapper.toResponse(ArgumentMatchers.any())).thenReturn(response);
        // Mock mapper behavior

        List<MainServiceResponse> responseResult = mainService.getAllMainServicesWithSubServices();
        System.out.println("The response result : " + responseResult);
        Assert.assertNotNull(responseResult);
        Assert.assertEquals("ITR filing", responseResult.get(0).getMainServiceName());
        Assert.assertEquals("ITR", responseResult.get(0).getMainServiceShortName());
        Assert.assertEquals("pros and cons", responseResult.get(0).getProsAndCons());

        Assert.assertEquals("Description for Sub service 1",
                response.getSubServices().get(0).getDescription());
        Assert.assertEquals(true, response.getSubServices().get(0).isStatus());
        Assert.assertEquals("anusha", response.getSubServices().get(0).getCreatedBy());

    }


    @Test
    public void updatedMainService() throws Exception {

        MainServiceEntity mainServiceRequest = new MainServiceEntity();
        mainServiceRequest.setMainServiceName("ITR filing");
        mainServiceRequest.setMainServiceShortName("ITR");
        mainServiceRequest.setDescription("ItR filing description");
        mainServiceRequest.setStatus(true);
        mainServiceRequest.setUpdatedDate(LocalDate.now());
       /* File testFile = ResourceUtils.getFile(this.getClass().getResource("/file.png"));
        mainServiceRequest.setFileData(readFileToBytes(testFile));*/
        System.out.println(mainServiceRequest);

        Mockito.when(mainServiceRepository.findByMainServiceId(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(mainServiceRequest));

        mainServiceRepository.save(mainServiceRequest);
       /* System.out.println("test file is :"+testFile);
        FileInputStream stream = new FileInputStream(testFile);
        MultipartFile file = new MockMultipartFile("data", "file.png", "multipart/form-data",stream);*/
        MainServiceRequest mrequest = new MainServiceRequest();
        mrequest.setMainServiceName("ITR filing");
        mrequest.setMainServiceShortName("ITR");
        mrequest.setDescription("Itr filing description");
        mrequest.setStatus(true);
        mrequest.setUpdatedDate(LocalDate.now());

        MainServiceEntity response = mainService
                .updatedMainService("MS003",mrequest,files);
        Assert.assertNotNull(response);
        Assert.assertEquals("ITR filing",response.getMainServiceName());
        Assert.assertEquals("ITR", response.getMainServiceShortName());
        Assert.assertEquals("Itr filing description",response.getDescription());
        Assert.assertEquals(true, response.isStatus());
        Assert.assertEquals(LocalDate.now(), response.getUpdatedDate());
    }

    @Test
    public void deleteMainService() throws Exception{
        MainServiceEntity mainServiceEntity = new MainServiceEntity();
        mainServiceEntity.setMainServiceId("MS002");
        mainServiceEntity.setMainServiceName("ITR filing");
        mainServiceEntity.setMainServiceShortName("ITR");
        mainServiceEntity.setProsAndCons("pros and cons");
        mainServiceEntity.setDescription("Itr filing description");
        mainServiceEntity.setGovernmentSection("government section");
        mainServiceEntity.setLiable("liable");
        mainServiceEntity.setCreatedBy("anusha");
        mainServiceEntity.setStatus(true);
        mainServiceEntity.setCreatedDate(LocalDate.now());
        mainServiceEntity.setUpdatedDate(LocalDate.now());
/*        File testFile = ResourceUtils.getFile(this.getClass().getResource("/file.png"));
        mainServiceEntity.setFileData(readFileToBytes(testFile));*/
        // Set other properties for mainServiceEntity
        List<SubServiceEntity> subServiceEntities = new ArrayList<>();
        SubServiceEntity subService1 = new SubServiceEntity();
        subService1.setSubServiceId("SS001");
        subService1.setSubServiceName("Sub service 1");
        subService1.setDescription("Description for Sub service 1");
        subService1.setStatus(true);
        subService1.setCreatedBy("anusha");
        subService1.setCreatedDate(LocalDate.now());
        subService1.setSubServiceShortName("ITR");
        subService1.setSubServiceName("SUB");
        subService1.setGovernmentSection("Government session");
        subService1.setLiable("liable");
        subService1.setProsAndCons("pros and corns");
        subService1.setSubServiceId("SS003");
        subService1.setUpdatedDate(LocalDate.now());
        subService1.setUpdatedDate(LocalDate.now());
/*        File subTestFile1 = ResourceUtils.getFile(this.getClass().getResource("/subservice1.png"));
        subService1.setFileData(readFileToBytes(subTestFile1));*/
        subServiceEntities.add(subService1);

        SubServiceEntity subService2 = new SubServiceEntity();
        subService2.setSubServiceId("SS002");
        subService2.setSubServiceName("Sub service 2");
        subService2.setDescription("Description for Sub service 2");
        subService2.setStatus(true);
        subService2.setCreatedBy("anusha");
        subService2.setCreatedDate(LocalDate.now());
        subService2.setSubServiceShortName("ITR");
        subService2.setSubServiceName("SUB");
        subService2.setGovernmentSection("Government session");
        subService2.setLiable("liable");
        subService2.setProsAndCons("pros and corns");
        subService2.setUpdatedDate(LocalDate.now());
/*        File subTestFile2 = ResourceUtils.getFile(this.getClass().getResource("/subservice2.png"));
        subService2.setFileData(readFileToBytes(subTestFile2));*/
        subServiceEntities.add(subService2);
        mainServiceEntity.setSubServices(subServiceEntities);
        System.out.println(mainServiceEntity);

        Mockito.when(mainServiceRepository.findByMainServiceId(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(mainServiceEntity));


        // Call the deleteMainService method
        MainServiceEntity response = mainService.deleteMainService("MS002");

        // Verify that repository methods were called appropriately
        Mockito.verify(mainServiceRepository, Mockito.times(1)).findByMainServiceId(ArgumentMatchers.anyString());
        Mockito.verify(mainServiceRepository, Mockito.times(1)).delete(ArgumentMatchers.any());

        // Additional assertions if needed
        Assert.assertNotNull(response);
        Assert.assertEquals(mainServiceEntity.getMainServiceId(), response.getMainServiceId());
        // Compare other relevant attributes, not images
    }

    @Test
    public void existsById() {
        Mockito.when(mainServiceRepository.existsById(ArgumentMatchers.anyString()))
                .thenReturn(Boolean.FALSE);

        Boolean response = mainService.existsById("MS002");
        Assert.assertNotNull(response);
        Assert.assertFalse(response);

    }
}