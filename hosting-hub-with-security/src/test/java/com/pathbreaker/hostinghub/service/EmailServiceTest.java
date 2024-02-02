package com.pathbreaker.hostinghub.service;

import com.pathbreaker.hostinghub.entity.EmailEntity;
import com.pathbreaker.hostinghub.mappers.EmailMapper;
import com.pathbreaker.hostinghub.passwordencrypt.PasswordEncoder;
import com.pathbreaker.hostinghub.repository.EmailRepository;
import com.pathbreaker.hostinghub.request.EmailRequest;
import com.pathbreaker.hostinghub.request.EmailUpdateRequest;
import com.pathbreaker.hostinghub.response.EmailResponse;
import com.pathbreaker.hostinghub.response.ResultResponse;
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
public class EmailServiceTest {

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private EmailMapper emailMapper;

    @Mock
    private  PasswordEncoder passwordEncoder;

    public EmailService emailService = new EmailService(emailRepository, emailMapper,passwordEncoder);


    @Before
    public void setUp() {
        ReflectionTestUtils.setField(emailService, "emailRepository", emailRepository);
        ReflectionTestUtils.setField(emailService, "emailMapper", emailMapper);
        ReflectionTestUtils.setField(emailService, "passwordEncoder",passwordEncoder);
    }

    @Test
    public void testRegisterEmail() {
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setEmail("raj@gmail.com");
        emailRequest.setUsername("tappu");
        emailRequest.setPassword("tappu7");

        EmailEntity expectedEmailEntity = new EmailEntity();
        expectedEmailEntity.setEmailId("EM001");
        expectedEmailEntity.setEmail("raj@gmail.com");
        expectedEmailEntity.setUsername("tappu");
        expectedEmailEntity.setPassword("tappu7");

        Mockito.when(emailRepository.findHighestEmailId()).thenReturn("EM001");
        Mockito.when(emailMapper.entityToRequest(ArgumentMatchers.any())).thenReturn(expectedEmailEntity);
        Mockito.when(emailRepository.save(ArgumentMatchers.any())).thenReturn(expectedEmailEntity);

        ResultResponse result = emailService.registerEmail(emailRequest);

        assertNotNull(result);


        assertEquals("EM001", expectedEmailEntity.getEmailId());
        assertEquals("raj@gmail.com", expectedEmailEntity.getEmail());
        assertEquals("tappu", expectedEmailEntity.getUsername());
        //assertEquals("tappu7", expectedEmailEntity.getPassword());
    }


    @Test
    public void testGetEmailById() {
        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setEmailId("EM001");
        emailEntity.setEmail("keerthi@gmail.com");
        emailEntity.setUsername("raj");
        emailEntity.setPassword("raj7");

        Mockito.when(emailRepository.findById("EM001")).thenReturn(Optional.of(emailEntity));

        ResponseEntity<?> result = emailService.getEmailById("EM001");

        assertNotNull(result);
        assertEquals("EM001", emailEntity.getEmailId());
        assertEquals("keerthi@gmail.com", emailEntity.getEmail());
        assertEquals("raj", emailEntity.getUsername());
    }

    @Test
    public void testGetAllEmails() {
        // Create mock EmailEntities
        EmailEntity emailEntity1 = new EmailEntity();
        emailEntity1.setEmailId("EM001");
        emailEntity1.setEmail("anusha@gmail.com");
        emailEntity1.setUsername("anusha");
        emailEntity1.setPassword("anushaaki");

        EmailEntity emailEntity2 = new EmailEntity();
        emailEntity2.setEmailId("EM002");
        emailEntity2.setEmail("vamsi@gmail.com");
        emailEntity2.setUsername("vamsi");
        emailEntity2.setPassword("vamsi09");

        List<EmailEntity> emailEntities = new ArrayList<>();
        emailEntities.add(emailEntity1);
        emailEntities.add(emailEntity2);

        Mockito.when(emailRepository.findAll()).thenReturn(emailEntities);

        List<EmailResponse> result = emailService.getAllEmails();

        assertNotNull(result);



        assertEquals("EM001", emailEntity1.getEmailId());
        assertEquals("anusha@gmail.com", emailEntity1.getEmail());
        assertEquals("anusha", emailEntity1.getUsername());
        //assertEquals("anushaaki", emailEntity1.getPassword());


        assertEquals("EM002", emailEntity2.getEmailId());
        assertEquals("vamsi@gmail.com", emailEntity2.getEmail());
        assertEquals("vamsi", emailEntity2.getUsername());
        //assertEquals("vamsi09", emailEntity2.getPassword());
    }


    @Test
    public void testUpdateEmail() {
        String EmailId = "EM001";
        EmailUpdateRequest emailRequest = new EmailUpdateRequest();
        emailRequest.setEmail("arthi@gmail.com");
        emailRequest.setUsername("pavan");
        emailRequest.setPassword("pavan7");

        EmailEntity emailEntity = new EmailEntity();

        Mockito.when(emailRepository.findByEmailId(EmailId)).thenReturn(emailEntity);
        Mockito.when(emailMapper.updateEntityFromRequest(emailRequest, emailEntity)).thenReturn(emailEntity);

        ResultResponse result = emailService.updateEmail(EmailId, emailRequest);

        assertNotNull(result);
        assertEquals("Update successful", result.getResult());
        assertEquals("arthi@gmail.com", emailRequest.getEmail());
        assertEquals("pavan", emailRequest.getUsername());
        assertEquals("pavan7", emailRequest.getPassword());
    }

    @Test
    public void testDeleteEmail() {

        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setEmailId("EM001");
        emailEntity.setEmail("arthi@gmail.com");
        emailEntity.setUsername("pavan");
        emailEntity.setPassword("pavan7");


        Mockito.when(emailRepository.findById("EM001")).thenReturn(Optional.of(emailEntity));


        ResultResponse result = emailService.deleteEmail("EM001");


        assertEquals("Delete successful", result.getResult());
        assertEquals("EM001", emailEntity.getEmailId());
        assertEquals("arthi@gmail.com", emailEntity.getEmail());
        assertEquals("pavan", emailEntity.getUsername());
        assertEquals("pavan7", emailEntity.getPassword());
    }
}