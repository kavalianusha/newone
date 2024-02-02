package com.example.efilingbazaar.service;

import com.example.efilingbazaar.entity.LogEntity;
import com.example.efilingbazaar.entity.LoginEntity;
import com.example.efilingbazaar.entity.OtpEntity;
import com.example.efilingbazaar.repository.LogRepository;
import com.example.efilingbazaar.repository.LoginRepository;
import com.example.efilingbazaar.repository.OtpRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    private LoginRepository loginRepository;
    @Mock
    private OtpRepository otpRepository;
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private Map<String, LocalDateTime> otpExpiryMap;
    @Mock
    private LogRepository logRepository;


    LoginService service = new LoginService(loginRepository,logRepository, otpRepository, javaMailSender);

    public LoginServiceTest() {
        otpExpiryMap = null;
    }

    @Before
    public void setUp() throws Exception {
        ReflectionTestUtils.setField(service, "loginRepository", loginRepository);
        ReflectionTestUtils.setField(service, "otpRepository", otpRepository);
        ReflectionTestUtils.setField(service, "javaMailSender", javaMailSender);
        ReflectionTestUtils.setField(service, "otpExpiryMap", otpExpiryMap);
        ReflectionTestUtils.setField(service, "logRepository", logRepository);


    }

    @Test
    public void sendOtp() {
        LoginEntity login = new LoginEntity();
        login.setEmailId("mamathasman14@gmail.com");
        login.setPassword("mamatha");
        Mockito.when(loginRepository.findByEmailIdAndPassword(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString())).thenReturn(login);
        service.sendOtp("mamathasmna14@gmail.com", "mamatha");
        verify(loginRepository, times(1)).
                findByEmailIdAndPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        verify(otpRepository, times(1)).
                findByEmailId(ArgumentMatchers.anyString());
        verify(otpRepository, times(1)).
                save(ArgumentMatchers.any());
    }

    @Test
    public void login() {

        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setEmailId("mamathasman14@gmail.com");
        otpEntity.setOtp("987654");

        Mockito.when(otpRepository.findByEmailIdAndOtp(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString())).thenReturn(otpEntity);
        Mockito.when(otpExpiryMap.get(ArgumentMatchers.anyString())).thenReturn(LocalDateTime.now().plusDays(4));
        service.login("mamathasman14@gmail.com", "987654");
        verify(otpRepository, times(1)).
                findByEmailIdAndOtp(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
        verify(otpExpiryMap, times(1)).
                get(ArgumentMatchers.anyString());

        verify(otpExpiryMap, times(1)).
                remove(ArgumentMatchers.anyString());
        verify(otpRepository, times(1)).
                save(ArgumentMatchers.any());
        verify(otpExpiryMap, times(1)).
                remove(ArgumentMatchers.anyString());
    }

    @Test
    public void logout() {
        LoginEntity login1 = new LoginEntity();
        login1.setEmailId("mamathasman14@gmail.com");
        login1.setEmployeeId("EMP001");
        Timestamp expectedTimestamp = Timestamp.valueOf("2023-08-29 12:00:00");
        login1.setLastLoginTime(expectedTimestamp);

        Mockito.when(loginRepository.findByEmailId(ArgumentMatchers.anyString()))
                .thenReturn(login1);
        service.logout("mamathasman14@gmail.com" );
        verify(loginRepository, times(1))
                .findByEmailId(ArgumentMatchers.anyString());
    }

   /* @Test
    public void updateLastLoginTime() {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setEmailId("mamathasman14@gmail.com");
        Mockito.when(loginRepository.findByEmailId(ArgumentMatchers.anyString())).thenReturn(loginEntity);
        LogEntity log = new LogEntity();
        log.setEmailId("mamathasman14@gmail.com");
        log.setEmployeeId(loginEntity.getEmployeeId());
        log.setAction("loginEntity");
        service.updateLastLoginTime("mamathasman14@gmail.com");
        verify(loginRepository, times(1)).save(ArgumentMatchers.any());
    }*/

    @Test
    public void updateSessionLastLogoutTimeInLogEntityEntry() {
        LogEntity log = new LogEntity();
        log.setEmployeeId("EMP001");
        log.setSessionLastLogoutTime("2023-08-29 12:00:00");
        Timestamp expectedTimestamp = Timestamp.valueOf("2023-08-29 12:00:00");

        Mockito.when(logRepository.findByEmployee(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString())).thenReturn(log);
        service.updateSessionLastLogoutTimeInLogEntityEntry("EMP001", expectedTimestamp);
        verify(logRepository, times(1)).save(log);


    }

    @Test
    public void createLogEntityEntry() {
        LogEntity logEntity = new LogEntity();
        logEntity.setEmployeeId("EMP001");
        logEntity.setEmailId("mamathasman14@gmail.com");
        logEntity.setSessionLastLoginTime("2023-08-29 12:00:00");
        logEntity.setAction("expectedAction");
        service.createLogEntityEntry("mamathasman14@gmail.com",
                "EMP001", "expectedAction");
        // verify(logRepository, times(1)).save(logEntity);
        logRepository.save(logEntity);

    }
}