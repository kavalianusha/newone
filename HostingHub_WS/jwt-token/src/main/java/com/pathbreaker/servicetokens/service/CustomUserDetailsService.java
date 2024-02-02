package com.pathbreaker.servicetokens.service;

import com.pathbreaker.servicetokens.entity.UserEntity;
import com.pathbreaker.servicetokens.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUserName(userName);

        if (userEntity != null && userName.equals(userEntity.getUserName())) {

                return new User(
                        userEntity.getUserName(),
                        userEntity.getPassword(),
                        new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User Not found exception..........");
        }
    }

}
