package com.example.hostinghub.service;


import com.example.hostinghub.entity.DomainEntity;
import com.example.hostinghub.entity.HostingEntity;
import com.example.hostinghub.entity.ItReturnsEntity;
import com.example.hostinghub.entity.PasswordsEntity;
import com.example.hostinghub.mappers.PasswordsMapper;
import com.example.hostinghub.passwordencrypt.PasswordEncoder;
import com.example.hostinghub.repository.PasswordRepository;
import com.example.hostinghub.response.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PasswordsServiceTest {
    
    @Mock
    private PasswordRepository passwordRepository;
    @Mock
    private PasswordsMapper passwordsMapper;

    @Mock
    private PasswordEncoder passwordEncoder;
    
    public PasswordsService passwordsService = new PasswordsService(passwordRepository,passwordsMapper,passwordEncoder);
    
    @Before
    public void setUp(){
        ReflectionTestUtils.setField(passwordsService, "passwordRepository", passwordRepository);
        ReflectionTestUtils.setField(passwordsService, "passwordsMapper", passwordsMapper);
        ReflectionTestUtils.setField(passwordsService,"passwordEncoder",passwordEncoder);
    }

    @Test
    public void getAllPasswords() {

        HostingEntity hostingEntity = new HostingEntity();
        hostingEntity.setDaysLeft(122L);
        hostingEntity.setPassword("anusha");

        DomainEntity domainEntity = new DomainEntity();
        domainEntity.setDaysLeft(122L);
        domainEntity.setPassword("anusha");

        ItReturnsEntity itReturnsEntity = new ItReturnsEntity();
        itReturnsEntity.setDaysLeft(122L);
        itReturnsEntity.setPassword("anusha");

        DomainResponseView domainResponseView = new DomainResponseView();
        domainResponseView.setDaysLeft(122L);
        domainResponseView.setPassword("anusha");

        HostingResponseView hostingResponseView = new HostingResponseView();
        hostingResponseView.setDaysLeft(122L);
        hostingResponseView.setPassword("anusha");

        ItReturnsResponseView itReturnsResponseView = new ItReturnsResponseView();
        itReturnsResponseView.setDaysLeft(122L);
        itReturnsResponseView.setPassword("anusha");

        PasswordsResponseView passwordsResponse = new PasswordsResponseView();

        passwordsResponse.setPasswordId("PWD001");
        passwordsResponse.setDaysLeft(122L);
        passwordsResponse.setPassword("anusha");
        passwordsResponse.setExpiryDate("12/05/2023");
        passwordsResponse.setUserName("akhil");
        passwordsResponse.setRegistrationDate("12/01/2023");
        passwordsResponse.setDomainResponseView(domainResponseView);
        passwordsResponse.setHostingResponseView(hostingResponseView);
        passwordsResponse.setItReturnsResponseView(itReturnsResponseView);

        PasswordsResponseView passwordsResponse2 = new PasswordsResponseView();

        passwordsResponse2.setPasswordId("PWD001");
        passwordsResponse2.setDaysLeft(122L);
        passwordsResponse2.setPassword("anusha");
        passwordsResponse2.setExpiryDate("12/05/2023");
        passwordsResponse2.setUserName("akhil");
        passwordsResponse2.setRegistrationDate("12/01/2023");
        passwordsResponse2.setDomainResponseView(domainResponseView);
        passwordsResponse2.setHostingResponseView(hostingResponseView);
        passwordsResponse2.setItReturnsResponseView(itReturnsResponseView);

        List<PasswordsResponseView> passwords = new ArrayList<>();
        passwords.add(passwordsResponse);
        passwords.add(passwordsResponse2);

        PasswordsEntity passwordsEntity = new PasswordsEntity();
        passwordsEntity.setPasswordId("PWD001");
        passwordsEntity.setDaysLeft(122L);
        passwordsEntity.setPassword("anusha");
        passwordsEntity.setExpiryDate("2023-12-05");
        passwordsEntity.setUserName("akhil");
        passwordsEntity.setRegistrationDate("2023-12-01");
        passwordsEntity.setDomainEntity(domainEntity);
        passwordsEntity.setHostings(hostingEntity);
        passwordsEntity.setItReturns(itReturnsEntity);

        PasswordsEntity passwordsEntity1 = new PasswordsEntity();
        passwordsEntity1.setPasswordId("PWD001");
        passwordsEntity1.setDaysLeft(122L);
        passwordsEntity1.setPassword("anusha");
        passwordsEntity1.setExpiryDate("2023-12-05");
        passwordsEntity1.setUserName("akhil");
        passwordsEntity1.setRegistrationDate("2023-12-01");
        passwordsEntity1.setDomainEntity(domainEntity);
        passwordsEntity1.setHostings(hostingEntity);
        passwordsEntity1.setItReturns(itReturnsEntity);

        List<PasswordsEntity> passwordsEntitiesList = new ArrayList<>();
        passwordsEntitiesList.add(passwordsEntity);
        passwordsEntitiesList.add(passwordsEntity1);

        domainEntity.setPasswordsEntity(passwordsEntity);
        hostingEntity.setPasswordsEntity(passwordsEntity);
        itReturnsEntity.setPasswordsEntity(passwordsEntity);


        Mockito.when(passwordRepository.findAll()).thenReturn(passwordsEntitiesList);
        Mockito.when(passwordsMapper.entityToResponse(ArgumentMatchers.any())).thenReturn(passwordsResponse);
        Mockito.when(passwordEncoder.decryptPassword(ArgumentMatchers.anyString())).thenReturn(passwordsResponse.getPassword());

        Mockito.when(passwordsMapper.responseToDomainView(ArgumentMatchers.any())).thenReturn(domainResponseView);
        Mockito.when(passwordEncoder.decryptPassword(ArgumentMatchers.anyString())).thenReturn(domainResponseView.getPassword());

        Mockito.when(passwordsMapper.responseToHostingView(ArgumentMatchers.any())).thenReturn(hostingResponseView);
        Mockito.when(passwordEncoder.decryptPassword(ArgumentMatchers.anyString())).thenReturn(hostingResponseView.getPassword());

        Mockito.when(passwordsMapper.repsonseToItReturnsView(ArgumentMatchers.any())).thenReturn(itReturnsResponseView);
        Mockito.when(passwordEncoder.decryptPassword(ArgumentMatchers.anyString())).thenReturn(itReturnsResponseView.getPassword());

        List<PasswordsResponseView> result = passwordsService.getAllPasswords();
        System.out.println("the result is :" +result);
        assertEquals(2, result.size());

        assertNotNull(result);
        System.out.println(result);

        // Example assertions for other properties if needed
        assertEquals("PWD001", result.get(0).getPasswordId());
        assertEquals("anusha", result.get(0).getPassword());
        assertEquals("akhil",result.get(0).getUserName());

    }

}