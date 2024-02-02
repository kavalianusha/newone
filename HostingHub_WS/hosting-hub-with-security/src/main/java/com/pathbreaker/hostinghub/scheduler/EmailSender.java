package com.pathbreaker.hostinghub.scheduler;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    private final JavaMailSender javaMailSender;

    public  EmailSender(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmailForDomain(String emailId,String userName, long daysLeft) {
        System.out.println("Entered into the mail sender method ");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("Hello "+userName+ " a gentle reminder from the Domain Updation from PBT");
        mailMessage.setText(userName+"Please update your Domain Service is going to expiry within "+daysLeft+" days \n" +
                "so that you will not loose our service \n ThankYou!!\n PBT Team");

        javaMailSender.send(mailMessage);
    }

    public void sendEmailForHosting(String emailId,String userName, long daysLeft) {
        System.out.println("Entered into the mail sender method ");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("Hello "+userName+ " a gentle reminder from the Hosting Updation from PBT");
        mailMessage.setText(userName+"Please update your Hosting service it is going to expiry within "+daysLeft+" days \n" +
                "so that you will not loose our service \n ThankYou!!\n PBT Team");

        javaMailSender.send(mailMessage);
    }

    public void sendEmailForItReturns(String emailId,String userName, long daysLeft) {
        System.out.println("Entered into the mail sender method ");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("Hello "+userName+ " a gentle reminder from the It Returns Updation from PBT");
        mailMessage.setText(userName+"Please update your It Returns service is going to expiry within "+daysLeft+" days \n" +
                "so that you will not loose our service \n ThankYou!!\n PBT Team");

        javaMailSender.send(mailMessage);
    }

    public void sendEmailForDomainPassword(String emailId,String userName, long daysLeft) {
        System.out.println("Entered into the mail sender method Domain Password");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("Hello "+userName+ " a gentle reminder from the Domain Password Updation from PBT");
        mailMessage.setText(userName+"Please update your Domain Password if is going to expiry within "+daysLeft+" days \n" +
                "so that you will not loose our service \n ThankYou!!\n PBT Team");

        javaMailSender.send(mailMessage);
    }
    public void sendEmailForHostingPassword(String emailId,String userName, long daysLeft) {
        System.out.println("Entered into the mail sender method of Hosting Password ");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("Hello "+userName+ " a gentle reminder from the Hosting Password Updation from PBT");
        mailMessage.setText(userName+"Please update your Hosting Password it is going to expiry within "+daysLeft+" days \n" +
                "so that you will not loose our service \n ThankYou!!\n PBT Team");

        javaMailSender.send(mailMessage);
    }

    public void sendEmailForItReturnsPassword(String emailId,String userName, long daysLeft) {
        System.out.println("Entered into the mail sender method of IT returns password ");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailId);
        mailMessage.setSubject("Hello "+userName+ " a gentle reminder from the It Returns Password Updation from PBT");
        mailMessage.setText(userName+"Please update your It Returns Password it is going to expiry within "+daysLeft+" days \n" +
                "so that you will not loose our service \n ThankYou!!\n PBT Team");

        javaMailSender.send(mailMessage);
    }
}
