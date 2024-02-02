package com.example.springproject.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        if(userName.equalsIgnoreCase("anusha")){
            return new User("Anusha","anusha",new ArrayList<>());
        }
        else {
            throw new UsernameNotFoundException("User Not found exception..........");
        }

    }
}
