package com.pathbreaker.hostinghub.service;

import com.pathbreaker.hostinghub.entity.DomainEntity;
import com.pathbreaker.hostinghub.entity.PasswordsEntity;
import com.pathbreaker.hostinghub.mappers.DomainMappers;
import com.pathbreaker.hostinghub.mappers.PasswordsMapper;
import com.pathbreaker.hostinghub.passwordencrypt.PasswordEncoder;
import com.pathbreaker.hostinghub.repository.DomainRepository;
import com.pathbreaker.hostinghub.repository.PasswordRepository;
import com.pathbreaker.hostinghub.request.DomainRequest;
import com.pathbreaker.hostinghub.request.DomainUpdateRequest;
import com.pathbreaker.hostinghub.request.PasswordUpdateRequest;
import com.pathbreaker.hostinghub.response.DomainResponse;
import com.pathbreaker.hostinghub.response.PasswordsResponse;
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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DomainServiceTest {

    @Mock
    private DomainRepository domainRepository;
    @Mock
    private DomainMappers domainMappers;
    @Mock
    private PasswordRepository passwordRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private PasswordsMapper passwordsMapper;

    public DomainService domainService = new DomainService(domainRepository, domainMappers, passwordRepository,passwordEncoder,passwordsMapper);

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(domainService, "domainRepository", domainRepository);
        ReflectionTestUtils.setField(domainService, "domainMappers", domainMappers);
        ReflectionTestUtils.setField(domainService, "passwordRepository", passwordRepository);
        ReflectionTestUtils.setField(domainService, "passwordEncoder", passwordEncoder);
        ReflectionTestUtils.setField(domainService,"passwordsMapper",passwordsMapper);
    }

    @Test
    public void registerDomain() {
        // Create a mock DomainEntity
        DomainEntity entity = new DomainEntity();
        entity.setDomainId("DOM001");
        entity.setDomainName("keerthi");
        entity.setProviderName("vineeth");
        entity.setDomainUrl("www.google.com");
        entity.setUserName("keerthi07");
        entity.setPassword("0726");
        entity.setDuration("1year");
        entity.setClientName("raj");
        entity.setRegistrationDate("2023-12-01");
        entity.setExpiryDate("2024-12-05");
        entity.setRegistrationMobileNumber("8985290929");
        entity.setRegistrationName("arthi");
        entity.setRegistrationEmail("keerthidrlng7@gmail.com");

        PasswordsEntity passwordsEntity = new PasswordsEntity();
        passwordsEntity.setPasswordId("PWO001");
        passwordsEntity.setDaysLeft(122L);
        passwordsEntity.setPassword("anusha");
        passwordsEntity.setExpiryDate("2024-12-05");
        passwordsEntity.setUserName("akhil");
        passwordsEntity.setRegistrationDate("2023-12-01");

        DomainRequest request = new DomainRequest();
        request.setDomainId("DOM002");
        request.setDomainName("keerthi sree");
        request.setProviderName("vineeth kumar");
        request.setDomainUrl("www.google.com");
        request.setUserName("keerthi0700");
        request.setPassword("0726");
        request.setDuration("1year");
        request.setClientName("raj");
        request.setRegistrationDate("2023-12-01");
        request.setExpiryDate("2024-12-05");
        request.setRegistrationMobileNumber("895290929");
        request.setRegistrationName("arthi");
        request.setRegistrationEmail("keerthidrlng7@gmail.com");
        //request.setPasswordsEntity(entity.getPasswordsEntity());

        Mockito.when(domainRepository.findHighestDomainId()).thenReturn("DOM001");
        Mockito.when(domainMappers.entityToRequest(ArgumentMatchers.any())).thenReturn(entity);
       // Mockito.when(domainMappers.entityPasswordToRequest(ArgumentMatchers.any())).thenReturn(passwordsEntity);
       //Mockito.when(domainRepository.save(entity)).thenReturn(entity);

        ResponseEntity<?> domainEntity = domainService.registerDomain(request);

        assertNotNull(domainEntity);

        assertEquals("DOM002", request.getDomainId());
        assertEquals("keerthi sree", request.getDomainName());
        assertEquals("vineeth kumar", request.getProviderName());
        assertEquals("www.google.com", request.getDomainUrl());
        assertEquals("keerthi0700", request.getUserName());
        assertEquals("0726", request.getPassword());
        assertEquals("1year", request.getDuration());
        assertEquals("raj", request.getClientName());
        assertEquals("2023-12-01", request.getRegistrationDate());
        assertEquals("2024-12-05", request.getExpiryDate());
        assertEquals("895290929", request.getRegistrationMobileNumber());
        assertEquals("arthi", request.getRegistrationName());
        assertEquals("keerthidrlng7@gmail.com", request.getRegistrationEmail());
    }

    @Test
    public void getDomainById() {

        PasswordsResponse passwordsResponse = new PasswordsResponse();
        passwordsResponse.setPassword("password1");
        passwordsResponse.setPasswordId("PWD001");
        passwordsResponse.setUserName("user1");
        passwordsResponse.setRegistrationDate("2022-10-15");
        passwordsResponse.setExpiryDate("2023-10-14");
        passwordsResponse.setDaysLeft(122L);

        PasswordsEntity passwordsEntity = new PasswordsEntity();
        passwordsEntity.setPassword("password1");
        passwordsEntity.setPasswordId("PWD001");
        passwordsEntity.setUserName("user1");
        passwordsEntity.setRegistrationDate("2022-10-15");
        passwordsEntity.setExpiryDate("2023-10-14");
        passwordsEntity.setDaysLeft(122L);

        DomainEntity entity = new DomainEntity();

        entity.setDomainId("DOM001");
        entity.setDomainName("anusha");
        entity.setProviderName("sameera");
        entity.setDomainUrl("https://example.com");
        entity.setUserName("swati");
        entity.setPassword("23456");
        entity.setDuration("1year");
        entity.setClientName("naziya");
        entity.setRegistrationDate("2022-10-15");
        entity.setExpiryDate("2023-10-14");
        entity.setRegistrationMobileNumber("1234567890");
        entity.setRegistrationName("bhavani");
        entity.setRegistrationEmail("bhavani@gmail.com");
        entity.setPasswordsEntity(passwordsEntity);

        DomainResponse response = new DomainResponse();

        response.setDomainId("DOM001");
        response.setDomainName("anusha");
        response.setProviderName("sameera");
        response.setDomainUrl("https://example.com");
        response.setUserName("swati");
        response.setPassword("23456");
        response.setDuration("1year");
        response.setClientName("naziya");
        response.setRegistrationDate("2022-10-15");
        response.setExpiryDate("2023-10-14");
        response.setRegistrationMobileNumber("1234567890");
        response.setRegistrationName("bhavani");
        response.setRegistrationEmail("bhavani@gmail.com");
        response.setPasswordsResponse(passwordsResponse);

        Mockito.when(domainRepository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(entity));
        Mockito.when(domainMappers.domainEntityToResponse(ArgumentMatchers.any())).thenReturn(response);
        Mockito.when(passwordEncoder.decryptPassword(ArgumentMatchers.anyString())).thenReturn(passwordsResponse.getPassword());

        ResponseEntity<?> result = domainService.getDomainById(entity.getDomainId());

        assertNotNull(result);
        assertEquals("DOM001", response.getDomainId());
        assertEquals("anusha", response.getDomainName());
        assertEquals("sameera", response.getProviderName());
        assertEquals("https://example.com", response.getDomainUrl());
        assertEquals("swati", response.getUserName());
        assertEquals("password1", response.getPassword());
        assertEquals("1year", response.getDuration());
        assertEquals("naziya", response.getClientName());
        assertEquals("2022-10-15", response.getRegistrationDate());
        assertEquals("2023-10-14", response.getExpiryDate());
        assertEquals("1234567890", response.getRegistrationMobileNumber());
        assertEquals("bhavani", response.getRegistrationName());
        assertEquals("bhavani@gmail.com", response.getRegistrationEmail());
    }


    @Test
    public void getAllDomains() {

        PasswordsResponse passwordsResponse = new PasswordsResponse();
        passwordsResponse.setPassword("password1");
        passwordsResponse.setPasswordId("PWD001");
        passwordsResponse.setUserName("user1");
        passwordsResponse.setRegistrationDate("2022-10-15");
        passwordsResponse.setExpiryDate("2023-10-14");
        passwordsResponse.setDaysLeft(122L);

        PasswordsEntity passwordsEntity = new PasswordsEntity();
        passwordsEntity.setPassword("password1");
        passwordsEntity.setPasswordId("PWD001");
        passwordsEntity.setUserName("user1");
        passwordsEntity.setRegistrationDate("2022-10-15");
        passwordsEntity.setExpiryDate("2023-10-14");
        passwordsEntity.setDaysLeft(122L);

        DomainEntity domain1 = new DomainEntity();
        domain1.setDomainId("DOM001");
        domain1.setDomainName("Example Domain 1");
        domain1.setProviderName("Provider 1");
        domain1.setDomainUrl("www.googl.com");
        domain1.setUserName("user1");
        domain1.setPassword("password1");
        domain1.setDuration("1year");
        domain1.setClientName("client1");
        domain1.setRegistrationDate("2022-10-15");
        domain1.setExpiryDate("2023-10-14");
        domain1.setRegistrationMobileNumber("9876543210");
        domain1.setRegistrationName("John Doe");
        domain1.setRegistrationEmail("john@example1.com");
        domain1.setPasswordsEntity(passwordsEntity);

        DomainResponse domainResponse = new DomainResponse();
        domainResponse.setDomainId("DOM002");
        domainResponse.setDomainName("Example Domain 2");
        domainResponse.setProviderName("Provider 2");
        domainResponse.setDomainUrl("www.google.com");
        domainResponse.setUserName("user2");
        domainResponse.setPassword("password2");
        domainResponse.setDuration("2years");
        domainResponse.setClientName("client2");
        domainResponse.setRegistrationDate("2022-11-15");
        domainResponse.setExpiryDate("2024-10-14");
        domainResponse.setRegistrationMobileNumber("9876543210");
        domainResponse.setRegistrationName("Jane Doe");
        domainResponse.setRegistrationEmail("jane@example2.com");
        domainResponse.setPasswordsResponse(passwordsResponse);

        DomainEntity domain2 = new DomainEntity();
        domain2.setDomainId("DOM002");
        domain2.setDomainName("Example Domain 2");
        domain2.setProviderName("Provider 2");
        domain2.setDomainUrl("www.google.com");
        domain2.setUserName("user2");
        domain2.setPassword("password2");
        domain2.setDuration("2years");
        domain2.setClientName("client2");
        domain2.setRegistrationDate("2022-11-15");
        domain2.setExpiryDate("2024-10-14");
        domain2.setRegistrationMobileNumber("9876543210");
        domain2.setRegistrationName("Jane Doe");
        domain2.setRegistrationEmail("jane@example2.com");
        domain2.setPasswordsEntity(passwordsEntity);

        List<DomainEntity> domainList = new ArrayList<>();
        domainList.add(domain1);
        domainList.add(domain2);

        Mockito.when(domainRepository.findAll()).thenReturn(domainList);
        Mockito.when(domainMappers.domainEntityToResponse(ArgumentMatchers.any())).thenReturn(domainResponse);
        Mockito.when(passwordEncoder.decryptPassword(ArgumentMatchers.anyString())).thenReturn(passwordsResponse.getPassword());

        List<DomainResponse> result = domainService.getAllDomains();



        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("DOM001", domain1.getDomainId());
        assertEquals("Example Domain 1", domain1.getDomainName());
        assertEquals("Provider 1", domain1.getProviderName());
        assertEquals("www.googl.com", domain1.getDomainUrl());
        assertEquals("user1", domain1.getUserName());
        assertEquals("password1", domain1.getPassword());
        assertEquals("1year", domain1.getDuration());
        assertEquals("client1", domain1.getClientName());
        assertEquals("2022-10-15", domain1.getRegistrationDate());
        assertEquals("2023-10-14", domain1.getExpiryDate());
        assertEquals("9876543210", domain1.getRegistrationMobileNumber());
        assertEquals("John Doe", domain1.getRegistrationName());
        assertEquals("john@example1.com", domain1.getRegistrationEmail());



        assertEquals("DOM002", domain2.getDomainId());
        assertEquals("Example Domain 2", domain2.getDomainName());
        assertEquals("Provider 2", domain2.getProviderName());
        assertEquals("www.google.com", domain2.getDomainUrl());
        assertEquals("user2", domain2.getUserName());
        assertEquals("password2", domain2.getPassword());
        assertEquals("2years", domain2.getDuration());
        assertEquals("client2", domain2.getClientName());
        assertEquals("2022-11-15", domain2.getRegistrationDate());
        assertEquals("2024-10-14", domain2.getExpiryDate());
        assertEquals("9876543210", domain2.getRegistrationMobileNumber());
        assertEquals("Jane Doe", domain2.getRegistrationName());
        assertEquals("jane@example2.com", domain2.getRegistrationEmail());

    }


    @Test
    public void updateDomain() {
        String domainId = "DOM001";

        PasswordsEntity passwordsEntity = new PasswordsEntity();
        passwordsEntity.setPasswordId("PWO001");
        passwordsEntity.setDaysLeft(122L);
        passwordsEntity.setPassword("anusha");
        passwordsEntity.setExpiryDate("2024-12-05");
        passwordsEntity.setUserName("akhil");
        passwordsEntity.setRegistrationDate("2023-12-01");

        PasswordUpdateRequest passwordsResponse = new PasswordUpdateRequest();
        passwordsResponse.setPasswordId("PWO001");
        passwordsResponse.setDaysLeft(122L);
        passwordsResponse.setPassword("anusha");
        passwordsResponse.setExpiryDate("2024-12-05");
        passwordsResponse.setUserName("akhil");
        passwordsResponse.setRegistrationDate("2023-12-01");

        DomainEntity existingDomain = new DomainEntity();
        existingDomain.setDomainId(domainId);
        existingDomain.setDomainName("anusha");
        existingDomain.setProviderName("sameera");
        existingDomain.setDomainUrl("https://example.com");
        existingDomain.setUserName("swati");
        existingDomain.setPassword("23456");
        existingDomain.setDuration("1year");
        existingDomain.setClientName("naziya");
        existingDomain.setRegistrationDate("2023-12-01");
        existingDomain.setExpiryDate("2024-12-05");
        existingDomain.setRegistrationMobileNumber("1234567890");
        existingDomain.setRegistrationName("bhavani");
        existingDomain.setRegistrationEmail("bhavani@gmail.com");
        existingDomain.setPasswordsEntity(passwordsEntity);

        DomainUpdateRequest updatedDomain = new DomainUpdateRequest();

        updatedDomain.setDomainName("book");
        updatedDomain.setProviderName("rani");
        updatedDomain.setDomainUrl("www.google.com");
        updatedDomain.setUserName("vamsi");
        updatedDomain.setPassword("itsme");
        updatedDomain.setDuration("1year");
        updatedDomain.setClientName("bheemesh");
        updatedDomain.setRegistrationDate("2023-12-01");
        updatedDomain.setExpiryDate("2024-12-05");
        updatedDomain.setRegistrationMobileNumber("9876543210");
        updatedDomain.setRegistrationName("sai");
        updatedDomain.setRegistrationEmail("sai@gmail.com");
        updatedDomain.setPasswordUpdateRequest(passwordsResponse);

        Mockito.when(domainRepository.findByDomainId(ArgumentMatchers.anyString())).thenReturn(existingDomain);
        Mockito.when(domainMappers.updateEntityFromRequest(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(existingDomain);
        Mockito.when(domainMappers.requestToUpdatePasswordEntity(ArgumentMatchers.any())).thenReturn(passwordsEntity);
        Mockito.when(passwordEncoder.encryptPassword(ArgumentMatchers.anyString())).thenReturn(passwordsEntity.getPassword());
        Mockito.when(passwordEncoder.encryptPassword(ArgumentMatchers.anyString())).thenReturn(existingDomain.getPassword());


        // Call the method being tested
        ResponseEntity result = domainService.updateDomain(domainId, updatedDomain);

        // Assert the result
        assertNotNull(result);
        // Verify that the existingDomain properties have been updated
        assertEquals("book",updatedDomain.getDomainName());
        assertEquals("rani", updatedDomain.getProviderName());
        assertEquals("www.google.com", updatedDomain.getDomainUrl());
        assertEquals("vamsi", updatedDomain.getUserName());
        assertEquals("itsme", updatedDomain.getPassword());
        assertEquals("1year", updatedDomain.getDuration());
        assertEquals("bheemesh", updatedDomain.getClientName());
        assertEquals("2023-12-01", updatedDomain.getRegistrationDate());
        assertEquals("2024-12-05", updatedDomain.getExpiryDate());
        assertEquals("9876543210", updatedDomain.getRegistrationMobileNumber());
        assertEquals("sai",updatedDomain.getRegistrationName());
        assertEquals("sai@gmail.com", updatedDomain.getRegistrationEmail());
    }


    @Test
    public void deleteDomainById() {

        DomainEntity entity = new DomainEntity();
        entity.setDomainId("DOM001");
        entity.setDomainName("balaji");
        entity.setProviderName("subbu");
        entity.setDomainUrl("www.keerthi.com");
        entity.setUserName("vijay");
        entity.setPassword("1909");
        entity.setDuration("1year");
        entity.setClientName("kondalu");
        entity.setRegistrationDate("2022-10-15");
        entity.setExpiryDate("2023-10-14");
        entity.setRegistrationMobileNumber("1234567890");
        entity.setRegistrationName("arthi");
        entity.setRegistrationEmail("arthi@gmail.com");

        PasswordsEntity passwordsEntity = new PasswordsEntity();
        passwordsEntity.setPasswordId("PWO001");
        passwordsEntity.setDaysLeft(122L);
        passwordsEntity.setPassword("anusha");
        passwordsEntity.setExpiryDate("12/05/2023");
        passwordsEntity.setUserName("akhil");
        passwordsEntity.setRegistrationDate("12/01/2023");

        entity.setPasswordsEntity(passwordsEntity);

        Mockito.when(domainRepository.findByDomainId("DOM001")).thenReturn(entity);
//        Mockito.when(passwordRepository.findHighestPasswordId()).thenReturn(String.valueOf(passwordsEntity));
        Mockito.doNothing().when(passwordRepository).delete(passwordsEntity);
        Mockito.doNothing().when(domainRepository).delete(entity);

        // Call the service method
        ResponseEntity result = domainService.deleteDomainById("DOM001");

        // Verify repository method invocations
        Mockito.verify(domainRepository, Mockito.times(1)).findByDomainId("DOM001");
        Mockito.verify(passwordRepository, Mockito.times(1)).delete(passwordsEntity);
        Mockito.verify(domainRepository, Mockito.times(1)).delete(entity);

        assertNotNull(result);
        assertEquals("DOM001", entity.getDomainId());
        assertEquals("balaji", entity.getDomainName());
        assertEquals("subbu", entity.getProviderName());
        assertEquals("www.keerthi.com", entity.getDomainUrl());
        assertEquals("vijay", entity.getUserName());
        assertEquals("1909", entity.getPassword());
        assertEquals("1year", entity.getDuration());
        assertEquals("kondalu", entity.getClientName());
        assertEquals("2022-10-15", entity.getRegistrationDate());
        assertEquals("2023-10-14", entity.getExpiryDate());
        assertEquals("1234567890", entity.getRegistrationMobileNumber());
        assertEquals("arthi", entity.getRegistrationName());
        assertEquals("arthi@gmail.com", entity.getRegistrationEmail());
    }
}