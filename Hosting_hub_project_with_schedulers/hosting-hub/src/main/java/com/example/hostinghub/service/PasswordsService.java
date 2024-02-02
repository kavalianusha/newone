package com.example.hostinghub.service;

import com.example.hostinghub.entity.PasswordsEntity;
import com.example.hostinghub.mappers.PasswordsMapper;
import com.example.hostinghub.passwordencrypt.PasswordEncoder;
import com.example.hostinghub.repository.PasswordRepository;
import com.example.hostinghub.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PasswordsService {

    public PasswordRepository passwordRepository;

    public PasswordsMapper passwordsMapper;

    public PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordsService(PasswordRepository passwordRepository,
                            PasswordsMapper passwordsMapper,
                            PasswordEncoder passwordEncoder) {
        this.passwordRepository = passwordRepository;
        this.passwordsMapper = passwordsMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<PasswordsResponseView> getAllPasswords() {
        List<PasswordsEntity> passwords = passwordRepository.findAll();
        log.info("PasswordsEntity size: " + passwords.size());

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<PasswordsResponseView> responses = passwords.stream()
                .map(password -> {
                    long daysLeft = calculateDaysLeft(password, currentDate,dateFormatter);
                    PasswordsResponseView response = passwordsMapper.entityToResponse(password);
                    response.setPassword(passwordEncoder.decryptPassword(response.getPassword()));
                    response.setDaysLeft(daysLeft);


                    if (password.getDomainEntity() != null) {
                        DomainResponseView domainResponse = passwordsMapper.responseToDomainView(password.getDomainEntity());
                        domainResponse.setPassword(passwordEncoder.decryptPassword(domainResponse.getPassword()));
                        response.setDomainResponseView(domainResponse);
                    }

                    if (password.getHostings() != null) {
                        HostingResponseView hostingResponse = passwordsMapper.responseToHostingView(password.getHostings());
                        hostingResponse.setPassword(passwordEncoder.decryptPassword(hostingResponse.getPassword()));
                        response.setHostingResponseView(hostingResponse);
                    }

                    if (password.getItReturns() != null) {
                        ItReturnsResponseView itReturnsResponse = passwordsMapper.repsonseToItReturnsView(password.getItReturns());
                        itReturnsResponse.setPassword(passwordEncoder.decryptPassword(itReturnsResponse.getPassword()));
                        response.setItReturnsResponseView(itReturnsResponse);
                    }

                    return response;
                })
                .collect(Collectors.toList());

        return responses;
    }

    private long calculateDaysLeft(PasswordsEntity password, LocalDate currentDate, DateTimeFormatter dateFormatter) {
        LocalDate expirationDate;

        if (password.getUpdateDate() != null) {
            // Use updateDate if present
            LocalDate lastUpdateDate = LocalDate.parse(password.getUpdateDate(), dateFormatter);

            // Calculate the next update date by adding 3 months to the last update date
            LocalDate nextUpdateDate = lastUpdateDate.plusMonths(3);

            // Use the next update date as the expiration date
            expirationDate = nextUpdateDate.isBefore(LocalDate.parse(password.getExpiryDate(), dateFormatter))
                    ? nextUpdateDate
                    : LocalDate.parse(password.getExpiryDate(), dateFormatter);
        } else {
            // Use either registration date + 3 months or expiryDate, whichever is earlier
            LocalDate registrationDatePlus3Months = LocalDate.parse(password.getRegistrationDate(), dateFormatter).plusMonths(3);
            expirationDate = registrationDatePlus3Months.isBefore(LocalDate.parse(password.getExpiryDate(), dateFormatter))
                    ? registrationDatePlus3Months
                    : LocalDate.parse(password.getExpiryDate(), dateFormatter);
        }

        return Math.max(0, ChronoUnit.DAYS.between(currentDate, expirationDate));
    }
}

