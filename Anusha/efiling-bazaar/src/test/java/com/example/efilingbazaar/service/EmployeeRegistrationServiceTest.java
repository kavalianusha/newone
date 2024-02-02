
package com.example.efilingbazaar.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.efilingbazaar.entity.EmployeeRegistrationEntity;
import com.example.efilingbazaar.entity.LoginEntity;
import com.example.efilingbazaar.entity.OtpEntity;
import com.example.efilingbazaar.entity.RoleEntity;
import com.example.efilingbazaar.mapper.EmployeeRegistrationMapper;
import com.example.efilingbazaar.repository.EmployeeRegistrationRepository;
import com.example.efilingbazaar.repository.LoginRepository;
import com.example.efilingbazaar.repository.OtpRepository;
import com.example.efilingbazaar.repository.RoleRepository;
import com.example.efilingbazaar.request.EmployeeRegistrationRequest;
import com.example.efilingbazaar.response.EmployeeRegistrationResponse;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeRegistrationServiceTest {

    @Mock
    private EmployeeRegistrationRepository registrationRepository;

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private OtpRepository otpRepository;

    @Mock
    private EmployeeRegistrationMapper employeeMapper;

    @Mock
    private JavaMailSender javaMailSender;


    @InjectMocks
    EmployeeRegistrationService service = new EmployeeRegistrationService
            (registrationRepository, loginRepository, roleRepository, otpRepository, employeeMapper, javaMailSender);


    @SuppressWarnings("deprecation")
    @Before
    public void init() {
        ReflectionTestUtils.setField(service, "employeeMapper", employeeMapper);
        ReflectionTestUtils.setField(service, "registrationRepository", registrationRepository);
        ReflectionTestUtils.setField(service, "loginRepository", loginRepository);
        ReflectionTestUtils.setField(service, "roleRepository", roleRepository);
        ReflectionTestUtils.setField(service, "otpRepository", otpRepository);
        ReflectionTestUtils.setField(service, "employeeMapper", employeeMapper);
        ReflectionTestUtils.setField(service, "javaMailSender", javaMailSender);

    }

    @Test
    public void testRegisterEmployeeFromRequest() {
        // Prepare mock data
        EmployeeRegistrationRequest request = new EmployeeRegistrationRequest();
        request.setAddress("Hyderabad");
        request.setContactNumber("9876543212");
        request.setEmailId("mamathasman14@gmail.com");
        request.setEmployeeId("EMP002");
        request.setFirstName("mamatha");
        request.setIdProof("ASNDH09887");
        request.setLastName("dokku");
        request.setMiddleName("sman");
        request.setPassword("mamatha@123");
        request.setQualification("btech");
        request.setRoleName("employee");
        request.setSkillset("java");
        request.setStatus(true);

        EmployeeRegistrationEntity registrationEntity = new EmployeeRegistrationEntity();
        registrationEntity.setAddress("Hyderabad");
        registrationEntity.setContactNumber("9876543212");
        registrationEntity.setEmailId("mamathasman14@gmail.com");
        registrationEntity.setEmployeeId("EMP002");
        registrationEntity.setFirstName("mamatha");
        registrationEntity.setIdProof("ASNDH09887");
        registrationEntity.setLastName("dokku");
        registrationEntity.setMiddleName("sman");
        registrationEntity.setPassword("mamatha@123");
        registrationEntity.setQualification("b.tech");
        registrationEntity.setRoleName("employee");
        registrationEntity.setSkillset("java");
        registrationEntity.setStatus(true);

        // Mock mapper behavior
        Mockito.when(employeeMapper.requestToEntity(request)).thenReturn(registrationEntity);
        Mockito.when(registrationRepository.existsByIdProof(ArgumentMatchers.anyString())).thenReturn(Boolean.FALSE);
        Mockito.when(registrationRepository.existsByEmailId(ArgumentMatchers.anyString())).thenReturn(Boolean.FALSE);
        Mockito.when(registrationRepository.findHighestemployeeId()).thenReturn("EMP001");
        //Mockito.when(registrationRepository.save(registrationEntity)).thenReturn(registrationEntity);
        Mockito.when(registrationRepository.save(Mockito.any(EmployeeRegistrationEntity.class)))
                .thenReturn(registrationEntity);

        LoginEntity login = new LoginEntity();
        login.setEmailId("mamathasman14@gmail.com");
        login.setEmployeeId("EMP001");
        login.setPassword("mamatha");
        Timestamp expectedTimestamp = Timestamp.valueOf("2023-08-29 12:00:00");
        login.setLastLoginTime(expectedTimestamp);
        Timestamp expectedTimestamp1 = Timestamp.valueOf("2023-08-29 18:00:00");
        login.setLastLogoutTime(expectedTimestamp1);
        login.setRoleName("employee");
        login.setStatus(true);
        Mockito.when(employeeMapper.entityToLoginEntity(ArgumentMatchers.any())).thenReturn(login);
        Mockito.when(loginRepository.save(Mockito.any(LoginEntity.class))).thenReturn(new LoginEntity());

        RoleEntity role = new RoleEntity();
        //role.setRoleId(9);
        role.setRoleName("employee");
        Mockito.when(employeeMapper.entityToRoleEntity(ArgumentMatchers.any())).thenReturn(role);
        Mockito.when(roleRepository.findByRoleName(ArgumentMatchers.anyString())).thenReturn(null);
        Mockito.when(roleRepository.save(Mockito.any(RoleEntity.class))).thenReturn(new RoleEntity());

        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setOtp("987654");
        otpEntity.setEmailId("mamathasman14@gmail.com");
        Mockito.when(employeeMapper.entityToOtpEntity(ArgumentMatchers.any())).thenReturn(otpEntity);
        Mockito.when(otpRepository.save(Mockito.any(OtpEntity.class))).thenReturn(new OtpEntity());


        EmployeeRegistrationEntity entity = service.registerEmployeeFromRequest(request);
        Assert.assertNotNull(entity);
        Assert.assertEquals("mamatha", request.getFirstName());

        // Add more assertions based on your expectations
    }

    @Test(expected = RuntimeException.class)
    public void registerEmployeeFromRequest_Exception(){
        EmployeeRegistrationRequest request = new EmployeeRegistrationRequest();


        EmployeeRegistrationEntity registrationEntity = new EmployeeRegistrationEntity();
        registrationEntity.setAddress("Hyderabad");
        registrationEntity.setContactNumber("9876543212");
        registrationEntity.setEmailId("mamathasman14@gmail.com");
        registrationEntity.setEmployeeId("EMP002");
        registrationEntity.setFirstName("mamatha");
        registrationEntity.setIdProof("ASNDH09887");
        registrationEntity.setLastName("dokku");
        registrationEntity.setMiddleName("sman");
        registrationEntity.setPassword("mamatha@123");
        registrationEntity.setQualification("b.tech");
        registrationEntity.setRoleName("employee");
        registrationEntity.setSkillset("java");
        registrationEntity.setStatus(true);

        // Mock mapper behavior
        Mockito.when(employeeMapper.requestToEntity(request)).thenReturn(registrationEntity);
        Mockito.when(registrationRepository.existsByIdProof(ArgumentMatchers.anyString())).thenReturn(Boolean.TRUE);

        service.registerEmployeeFromRequest(request);

    }


    @Test public void testGetAllEmployees() {
        // Prepare mock data
        EmployeeRegistrationEntity entity1 = new EmployeeRegistrationEntity();
        entity1.setAddress("hyd");
        entity1.setContactNumber("9876543210");
        entity1.setEmailId("mamathasman14@gmail.com");
        entity1.setEmployeeId("EMP001");
        entity1.setFirstName("mamatha");
        entity1.setIdProof("ASDF34");
        entity1.setLastName("dokku");
        entity1.setMiddleName("sman");
        entity1.setPassword("mamatha@123");
        entity1.setQualification("btech");
        entity1.setRoleName("employee");
        entity1.setSkillset("java");
        entity1.setStatus(false);
        EmployeeRegistrationEntity entity2 = new EmployeeRegistrationEntity();
        entity2.setAddress("ngkl");
        entity2.setContactNumber("987683210");
        entity2.setEmailId("mamathadokku@gmail.com");
        entity2.setEmployeeId("EMP002");
        entity2.setFirstName("manisha");
        entity2.setIdProof("FGHH6734");
        entity2.setLastName("polmuri");
        entity2.setMiddleName("sman");
        entity2.setPassword("manisha@123");
        entity2.setQualification("b.com");
        entity2.setRoleName("user");
        entity2.setSkillset("python");
        entity2.setStatus(true);
        EmployeeRegistrationEntity entity3 = new EmployeeRegistrationEntity();
        entity3.setAddress("hyd");
        entity3.setContactNumber("9876543210");
        entity3.setEmailId("mamathasman14@gmail.com");
        entity3.setEmployeeId("EMP001");
        entity3.setFirstName("mamatha");
        entity3.setIdProof("ASDF34");
        entity3.setLastName("dokku");
        entity3.setMiddleName("sman");
        entity3.setPassword("mamatha@123");
        entity3.setQualification("btech");
        entity3.setRoleName("employee");
        entity3.setSkillset("java");
        entity3.setStatus(false);

        List<EmployeeRegistrationEntity> entityList = new ArrayList<>();
        entityList.add(entity1);
        entityList.add(entity2);
        entityList.add(entity3);

        // Mock repository behavior
        Mockito.when(registrationRepository.findAll()).thenReturn(entityList);
        EmployeeRegistrationResponse response1 = new EmployeeRegistrationResponse();
        response1.setFirstName("manisha1");
        EmployeeRegistrationResponse response2 = new EmployeeRegistrationResponse();
        response2.setLastName("dokku");
        response2.setFirstName("manisha");
        EmployeeRegistrationResponse response3 = new EmployeeRegistrationResponse();
        response3.setAddress("hyd");
        // Mock mapper behavior
        Mockito.when(employeeMapper.convertToResponseEmployeeDTO(entityList.get(0))). thenReturn(response1);
        Mockito.when(employeeMapper.convertToResponseEmployeeDTO(entityList.get(1))). thenReturn(response2);
        Mockito.when(employeeMapper.convertToResponseEmployeeDTO(entityList.get(2))). thenReturn(response3);

        // Call the method to be tested using the service instance
        List<EmployeeRegistrationResponse> result = service.getAllEmployees();
        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("manisha", result.get(1).getFirstName());



    }

    @Test
    public void testGetEmployeeById_Success() {
        // Prepare mock data
        String employeeId = "EMP001";
        EmployeeRegistrationEntity existingEmployee = new EmployeeRegistrationEntity();
        existingEmployee.setEmployeeId(employeeId);
        EmployeeRegistrationResponse expectedResponse = new EmployeeRegistrationResponse();
        expectedResponse.setFirstName("mamatha");

        // Mock repository behavior
        Mockito.when(registrationRepository.findByEmployeeId(employeeId)).thenReturn(existingEmployee);

        // Mock mapper behavior
        Mockito.when(employeeMapper.mapEntityToResponse(existingEmployee)).thenReturn(expectedResponse);

        // Call the method to be tested using the service instance
        EmployeeRegistrationResponse actualResponse = service.getEmployeeById(employeeId);

        // Assertions
        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(expectedResponse.getFirstName(), actualResponse.getFirstName());
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        // Prepare mock data
        String employeeId = "EMP001";

        // Mock repository behavior
        Mockito.when(registrationRepository.findByEmployeeId(employeeId)).thenReturn(null);

        // Call the method to be tested using the service instance
        EmployeeRegistrationResponse actualResponse = service.getEmployeeById(employeeId);

        // Assertion
        Assert.assertNull(actualResponse);
    }

    @Test
    public void testUpdateEmployee_Success() {
        // Prepare mock data
        String employeeId = "EMP001";
        EmployeeRegistrationEntity existingEmployee = new EmployeeRegistrationEntity();
        existingEmployee.setEmployeeId(employeeId);
        existingEmployee.setAddress("hyd");
        existingEmployee.setSkillset("java");
        existingEmployee.setStatus(true);

        EmployeeRegistrationEntity updatedRegistration = new EmployeeRegistrationEntity();
        updatedRegistration.setAddress("ngkl");
        updatedRegistration.setSkillset("spring");
        updatedRegistration.setStatus(false);

        // Mock repository behavior
        Mockito.when(registrationRepository.findByEmployeeId(employeeId)).thenReturn(existingEmployee);
        Mockito.when(registrationRepository.save(Mockito.any(EmployeeRegistrationEntity.class)))
                .thenReturn(existingEmployee);

        // Call the method to be tested using the service instance
        ResponseEntity<?> responseEntity = service.updateEmployee(employeeId, updatedRegistration);

        // Assertions
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals("Employee details updated successfully", responseEntity.getBody());
        Assert.assertEquals("ngkl", existingEmployee.getAddress());
        Assert.assertEquals("spring", existingEmployee.getSkillset());
        Assert.assertFalse(existingEmployee.isStatus());
    }

    @Test
    public void testUpdateEmployee_NotFound() {
        // Prepare mock data
        String employeeId = "EMP001";
        EmployeeRegistrationEntity updatedRegistration = new EmployeeRegistrationEntity();

        // Mock repository behavior
        Mockito.when(registrationRepository.findByEmployeeId(employeeId)).thenReturn(null);

        // Call the method to be tested using the service instance
        ResponseEntity<?> responseEntity = service.updateEmployee(employeeId, updatedRegistration);

        // Assertions
        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateEmployee_Exception() {
        // Prepare mock data
        String employeeId = "EMP001";
        EmployeeRegistrationEntity updatedRegistration = new EmployeeRegistrationEntity();

        // Mock repository behavior
        Mockito.when(registrationRepository.findByEmployeeId(employeeId)).thenThrow(new RuntimeException());

        // Call the method to be tested using the service instance
        service.updateEmployee(employeeId, updatedRegistration);
    }

    @Test
    public void testDeleteEmployee_Success() {
        // Prepare mock data
        String employeeId = "EMP001";
        EmployeeRegistrationEntity existingEmployee = new EmployeeRegistrationEntity();
        existingEmployee.setEmployeeId(employeeId);

        // Mock repository behavior
        Mockito.when(registrationRepository.findByEmployeeId(employeeId)).thenReturn(existingEmployee);

        // Call the method to be tested using the service instance
        ResponseEntity<Void> responseEntity = service.deleteEmployee(employeeId);

        // Assertions
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Mockito.verify(registrationRepository, Mockito.times(1)).delete(existingEmployee);
    }

    @Test
    public void testDeleteEmployee_NotFound() {

        String employeeId = "EMP001";

        // Mock repository behavior
        Mockito.when(registrationRepository.findByEmployeeId(employeeId)).thenReturn(null);

        // Call the method to be tested using the service instance
        ResponseEntity<Void> responseEntity = service.deleteEmployee(employeeId);

        // Assertions
        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Mockito.verify(registrationRepository, Mockito.never()).delete(Mockito.any());
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteEmployee_Exception() {
        // Prepare mock data
        String employeeId = "EMP001";

        // Mock repository behavior
        Mockito.when(registrationRepository.findByEmployeeId(employeeId)).thenThrow(new RuntimeException());

        // Call the method to be tested using the service instance
        service.deleteEmployee(employeeId);
    }
}