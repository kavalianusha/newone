package com.example.hostinghub.scheduler;

import com.example.hostinghub.entity.PasswordsEntity;
import com.example.hostinghub.mappers.DomainMappers;
import com.example.hostinghub.mappers.PasswordsMapper;
import com.example.hostinghub.passwordencrypt.PasswordEncoder;
import com.example.hostinghub.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@EnableScheduling
public class CronScheduler {


    private final DomainRepository domainRepository;
    private final HostingRepository hostingRepository;

    private final ItReturnsRepository itReturnsRepository;

    private final PasswordRepository passwordRepository;

    private final EmailSender emailSender;
    @Autowired
    public CronScheduler(DomainRepository domainRepository,
                         HostingRepository hostingRepository,
                         ItReturnsRepository itReturnsRepository,
                         EmailSender emailSender,
                         PasswordRepository passwordRepository) {
        this.domainRepository = domainRepository;
        this.hostingRepository = hostingRepository;
        this.itReturnsRepository = itReturnsRepository;
        this.emailSender = emailSender;
        this.passwordRepository = passwordRepository;
    }

    @Scheduled(fixedDelay = 12 * 60 * 60 * 1000)
    public void scheduleTask() {
        System.out.println("The schedule task is executed - " + System.currentTimeMillis() / 1000);

        System.out.println("The EMAILS are");

        domainRepository.findAll().forEach(domain -> {
            System.out.println("the domain Email id is : " + domain.getEmailId());

            LocalDate expiryDate = LocalDate.parse(domain.getExpiryDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("the expriy date is : " + expiryDate);

            long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
            System.out.println("the days left are : " + daysLeft);

            if ((daysLeft == 7 || daysLeft == 5 || daysLeft == 4 || daysLeft == 3 || daysLeft == 1)) {

                emailSender.sendEmailForDomain(domain.getEmailId(), domain.getUserName(), daysLeft);
            }
        });
        System.out.println("Total domain count: " + domainRepository.count());


        hostingRepository.findAll().forEach(hostingEntity -> {
            System.out.println("The Hosting Email Id is : " + hostingEntity.getEmailId());

            LocalDate expiryDate = LocalDate.parse(hostingEntity.getExpiryDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("the expriy date is : " + expiryDate);

            long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
            System.out.println("the days left are : " + daysLeft);

            if ((daysLeft == 7 || daysLeft == 5 || daysLeft == 4 || daysLeft == 3 || daysLeft == 1)) {

                emailSender.sendEmailForHosting(hostingEntity.getEmailId(), hostingEntity.getUserName(), daysLeft);
            }
        });
        System.out.println("Total hosting count: " + hostingRepository.count());

        itReturnsRepository.findAll().forEach(itReturns -> {
            System.out.println("the It Retruns Email Id is : " + itReturns.getEmailId());

            LocalDate expiryDate = LocalDate.parse(itReturns.getExpiryDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("the expriy date is : " + expiryDate);

            long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
            System.out.println("the days left are : " + daysLeft);

            if ((daysLeft == 7 || daysLeft == 5 || daysLeft == 4 || daysLeft == 3 || daysLeft == 1)) {

                emailSender.sendEmailForItReturns(itReturns.getEmailId(), itReturns.getUserName(), daysLeft);
            }
        });
        System.out.println("Total It returns count: " + itReturnsRepository.count());


            passwordRepository.findAll().forEach(passwordsEntity -> {
            System.out.println("the password details are " + passwordsEntity.getUserName());

            LocalDate expiryDate = LocalDate.parse(passwordsEntity.getExpiryDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("the expriy date is : " + expiryDate);

            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            long daysLeft = calculateDaysLeft(passwordsEntity,currentDate, dateFormatter);
            if ((daysLeft == 7 || daysLeft == 5 || daysLeft == 4 || daysLeft == 3 || daysLeft == 1)) {
                if (passwordsEntity.getDomainEntity() != null && passwordsEntity.getDomainEntity().getEmailId() != null){
                    System.out.println("Entered into the domain entity email Id");
                    emailSender.sendEmailForDomainPassword(passwordsEntity.getDomainEntity().getEmailId(), passwordsEntity.getUserName(), daysLeft);
                }
                else if(passwordsEntity.getHostings() != null && passwordsEntity.getHostings().getEmailId() != null){
                    System.out.println("Entered into the domain entity email Id");
                    emailSender.sendEmailForHostingPassword(passwordsEntity.getHostings().getEmailId(), passwordsEntity.getUserName(), daysLeft);
                }
                else if(passwordsEntity.getItReturns() != null && passwordsEntity.getItReturns().getEmailId() != null){
                    System.out.println("Entered into the domain entity email Id");
                    emailSender.sendEmailForItReturnsPassword(passwordsEntity.getItReturns().getEmailId(), passwordsEntity.getUserName(), daysLeft);
                }
                else {
                    System.out.println("Not a our services password");
                }
            }
        });
        System.out.println("Total passwords count: " + passwordRepository.count());
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
