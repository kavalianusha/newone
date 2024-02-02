/*
package com.pathbreaker.hostinghub.scheduler;

import com.pathbreaker.hostinghub.repository.DomainRepository;
import com.pathbreaker.hostinghub.repository.HostingRepository;
import com.pathbreaker.hostinghub.repository.ItReturnsRepository;
import com.pathbreaker.hostinghub.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class CornsSchedulers {

        private final DomainRepository domainRepository;
        private final HostingRepository hostingRepository;
        private final ItReturnsRepository itReturnsRepository;
        private final PasswordRepository passwordRepository;
        private final EmailSender emailSender;


    @Autowired
    public CornsSchedulers(DomainRepository domainRepository,
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

    @Scheduled(fixedDelay = 10)
    public void performTask(){
        System.out.println("the task is performed");

        domainRepository.findDomainsExpiringToday();

        domainRepository.findDomainsExpiringToday().forEach(domainEntity -> {
            System.out.println("The Domain Expiry Today List are :");
        });
    }
}
*/
