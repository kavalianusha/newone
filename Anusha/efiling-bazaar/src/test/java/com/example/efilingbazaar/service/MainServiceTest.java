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
import org.springframework.test.util.ReflectionTestUtils;

import org.springframework.web.multipart.MultipartFile;

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

    private String PATH;

    private MainService mainService;

    @Before
    public void setUp() throws Exception {
        PATH = System.getProperty("java.io.tmpdir") + "MainServiceImages/";
        mainService = new MainService(mainServiceRepository, mainServiceMapper, PATH);
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
        List<MultipartFile> files1 = new ArrayList<>();
        List<String> mainServiceFilePaths = new ArrayList<>();
        mainServiceFilePaths.add(files1.toString());
        mainServiceFilePaths.add(files1.toString());
        mainServiceRequest.setMainServiceFilePaths(mainServiceFilePaths);
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
        List<MultipartFile> files = new ArrayList<>();
        List<String> mainServiceFile = new ArrayList<>();
        mainServiceFile.add(files.toString());
        mainServiceFile.add(files.toString());
        entity.setMainServiceFilePaths(mainServiceFile);
        entity.setCreatedDate(LocalDate.now());
        System.out.println(entity);

        Mockito.when(mainServiceRepository
                        .existsByMainServiceName(ArgumentMatchers.anyString()))
                .thenReturn(Boolean.FALSE);
        Mockito.when(mainServiceMapper
                        .toMainServiceEntity(ArgumentMatchers.any()))
                .thenReturn(entity);
        Mockito.when(mainServiceRepository
                        .findHighestMainServiceId
                                ())
                .thenReturn("MS001");
        Mockito.when(mainServiceRepository
                        .save(entity))
                .thenReturn(entity);

        MainServiceEntity response = mainService.createMainService(mainServiceRequest, files);
        Assert.assertNotNull(response);
        Assert.assertEquals("ITR filing", response.getMainServiceName());
        Assert.assertEquals("ITR", response.getMainServiceShortName());
        Assert.assertEquals("pros and cons", response.getProsAndCons());
        Assert.assertEquals("Itr filing description", response.getDescription());
        Assert.assertEquals("government section", response.getGovernmentSection());
        Assert.assertEquals(2, response.getMainServiceFilePaths().size());
        Assert.assertTrue(response.getMainServiceFilePaths().contains(files1.toString()));
        Assert.assertTrue(response.getMainServiceFilePaths().contains(files.toString()));
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
        List<MultipartFile> files1 = new ArrayList<>();
        List<String> mainServiceFilePaths = new ArrayList<>();
        mainServiceFilePaths.add(files1.toString());
        mainServiceFilePaths.add(files1.toString());
        response.setMainServiceFilePaths(mainServiceFilePaths);
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
        List<MultipartFile> files2 = new ArrayList<>();
        List<String> subServiceFilePaths1 = new ArrayList<>();
        subServiceFilePaths1.add(files2.toString());
        subServiceFilePaths1.add(files2.toString());
        subService1.setSubServiceFilePaths(subServiceFilePaths1);

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
        List<MultipartFile> files3 = new ArrayList<>();
        List<String> subServiceFilePaths2 = new ArrayList<>();
        subServiceFilePaths2.add(files3.toString());
        subServiceFilePaths2.add(files3.toString());
        subService2.setSubServiceFilePaths(subServiceFilePaths2);

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
        Assert.assertTrue(result.getMainServiceFilePaths().contains(files1.toString()));

        Assert.assertEquals("Description for Sub service 1",
                response.getSubServices().get(0).getDescription());
        Assert.assertEquals(true, response.getSubServices().get(0).isStatus());
        Assert.assertEquals("anusha", response.getSubServices().get(0).getCreatedBy());
        Assert.assertTrue(result.getSubServices().get(0).getSubServiceFilePaths().contains(files2.toString()));

        Assert.assertEquals("Description for Sub service 2",
                response.getSubServices().get(1).getDescription());
        Assert.assertEquals(true, response.getSubServices().get(1).isStatus());
        Assert.assertEquals("anusha", response.getSubServices().get(1).getCreatedBy());
        Assert.assertEquals("liable",response.getSubServices().get(1).getLiable());
        Assert.assertTrue(result.getSubServices().get(1).getSubServiceFilePaths().contains(files3.toString()));

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
        List<MultipartFile> files1 = new ArrayList<>();
        List<String> mainServiceFilePaths1 = new ArrayList<>();
        mainServiceFilePaths1.add(files1.toString());
        mainServiceFilePaths1.add(files1.toString());
        entity1.setMainServiceFilePaths(mainServiceFilePaths1);
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
        List<MultipartFile> subfiles1 = new ArrayList<>();
        List<String> subServiceFilePaths1 = new ArrayList<>();
        subServiceFilePaths1.add(subfiles1.toString());
        subServiceFilePaths1.add(subfiles1.toString());
        subService1.setSubServiceFilePaths(subServiceFilePaths1);
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
        List<MultipartFile> subfiles2 = new ArrayList<>();
        List<String> subServiceFilePaths2 = new ArrayList<>();
        subServiceFilePaths2.add(subfiles2.toString());
        subServiceFilePaths2.add(subfiles2.toString());
        subService2.setSubServiceFilePaths(subServiceFilePaths2);
        subService2.setMainServiceEntity(entity1);

        subServices.add(subService1);
        subServices.add(subService2);

        entity1.setSubServices(subServices);

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
        List<MultipartFile> files2 = new ArrayList<>();
        List<String> mainServiceFilePaths2 = new ArrayList<>();
        mainServiceFilePaths2.add(files2.toString());
        mainServiceFilePaths2.add(files2.toString());
        response1.setMainServiceFilePaths(mainServiceFilePaths2);
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
        List<MultipartFile> subfiles3 = new ArrayList<>();
        List<String> subServiceFilePaths3 = new ArrayList<>();
        subServiceFilePaths3.add(subfiles3.toString());
        subServiceFilePaths3.add(subfiles3.toString());
        subService3.setSubServiceFilePaths(subServiceFilePaths3);
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
        List<MultipartFile> subfiles4 = new ArrayList<>();
        List<String> subServiceFilePaths4 = new ArrayList<>();
        subServiceFilePaths4.add(subfiles4.toString());
        subServiceFilePaths4.add(subfiles4.toString());
        subService4.setSubServiceFilePaths(subServiceFilePaths4);
        subService4.setMainServiceEntity(response1);

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

        Mockito.when(mainServiceMapper.toResponse(ArgumentMatchers.any()))
                .thenReturn(response);
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
        mainServiceRequest.setMainServiceId("MS002");
        List<MultipartFile> mainfiles = new ArrayList<>();
        List<String> mainServiceFile1 = new ArrayList<>();
        mainServiceFile1.add(mainfiles.toString());
        mainServiceFile1.add(mainfiles.toString());
        mainServiceRequest.setMainServiceFilePaths(mainServiceFile1);
        mainServiceRequest.setUpdatedDate(LocalDate.now());

        System.out.println(mainServiceRequest);

        Mockito.when(mainServiceRepository.findByMainServiceId("MS002"))
                .thenReturn(Optional.of(mainServiceRequest));

        MainServiceRequest mrequest = new MainServiceRequest();
        mrequest.setMainServiceName("ITR filing");
        mrequest.setMainServiceShortName("ITR");
        mrequest.setDescription("Itr filing description");
        mrequest.setStatus(true);
        mrequest.setMainServiceId("MS002");
        mrequest.setUpdatedDate(LocalDate.now());
        List<MultipartFile> files = new ArrayList<>();
        List<String> mainServiceFile = new ArrayList<>();
        mainServiceFile.add(files.toString());
        mainServiceFile.add(files.toString());
        mrequest.setMainServiceFilePaths(mainServiceFile);

        MainServiceEntity response = mainService.updatedMainService("MS002", mrequest, mainfiles);

        Assert.assertNotNull(response);
        Assert.assertEquals("ITR filing", response.getMainServiceName());
        Assert.assertEquals("ITR", response.getMainServiceShortName());
        Assert.assertEquals("Itr filing description", response.getDescription());
        Assert.assertTrue(response.isStatus());
        Assert.assertEquals(LocalDate.now(), response.getUpdatedDate());
        Assert.assertTrue(response.getMainServiceFilePaths().contains(files.toString()));
    }


   /* @Test
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
        List<MultipartFile> mainfiles = new ArrayList<>();
        List<String> mainServiceFile1 = new ArrayList<>();
        mainServiceFile1.add(mainfiles.toString());
        mainServiceFile1.add(mainfiles.toString());
        mainServiceEntity.setMainServiceFilePaths(mainServiceFile1);

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
        List<MultipartFile> subfiles1 = new ArrayList<>();
        List<String> subServiceFile1 = new ArrayList<>();
        subServiceFile1.add(subfiles1.toString());
        subServiceFile1.add(subfiles1.toString());
        subService1.setSubServiceFilePaths(subServiceFile1);
        subService1.setMainServiceEntity(mainServiceEntity);
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
        subService2.setMainServiceEntity(mainServiceEntity);
        subService2.setUpdatedDate(LocalDate.now());
        List<MultipartFile> subfiles2 = new ArrayList<>();
        List<String> subServiceFile2 = new ArrayList<>();
        subServiceFile2.add(subfiles2.toString());
        subServiceFile2.add(subfiles2.toString());
        subService2.setSubServiceFilePaths(subServiceFile2);
        subServiceEntities.add(subService2);

        mainServiceEntity.setSubServices(subServiceEntities);
        System.out.println(mainServiceEntity);

        Mockito.when(mainServiceRepository.findByMainServiceId(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(mainServiceEntity));

        Mockito.when(mainServiceRepository.delete(ArgumentMatchers.anyString())).thenReturn(mainServiceEntity);


        // Call the deleteMainService method
        MainServiceEntity response = mainService.deleteMainService("MS002");

        // Verify that repository methods were called appropriately
        Mockito.verify(mainServiceRepository, Mockito.times(1)).findByMainServiceId(ArgumentMatchers.anyString());
        Mockito.verify(mainServiceRepository, Mockito.times(1)).delete(ArgumentMatchers.any());

        // Additional assertions if needed
        Assert.assertNotNull(response);
        Assert.assertEquals(mainServiceEntity.getMainServiceId(), response.getMainServiceId());
        Assert.assertTrue(response.getMainServiceFilePaths().contains(toString()));
        // Compare other relevant attributes, not images
    }*/

    @Test
    public void existsById() {
        Mockito.when(mainServiceRepository.existsById(ArgumentMatchers.anyString()))
                .thenReturn(Boolean.FALSE);

        Boolean response = mainService.existsById("MS002");
        Assert.assertNotNull(response);
        Assert.assertFalse(response);

    }
}