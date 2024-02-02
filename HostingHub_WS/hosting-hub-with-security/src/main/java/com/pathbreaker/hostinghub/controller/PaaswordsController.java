package com.pathbreaker.hostinghub.controller;

import com.pathbreaker.hostinghub.response.PasswordsResponseView;
import com.pathbreaker.hostinghub.service.PasswordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api") // Add a base mapping for your API@CrossOrigin(origins = "*")
public class PaaswordsController {

    @Autowired
    private PasswordsService passwordsService;

    @GetMapping("/passwords/all")
    public List<PasswordsResponseView> getAllPasswords() {
        System.out.println("Request reached PasswordsController");
        List<PasswordsResponseView> responses = passwordsService.getAllPasswords();
        System.out.println("PasswordsResponse size: " + responses.size());
        return responses;
    }

}

