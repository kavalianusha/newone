package com.pathbreaker.accesstoken.serviceimpl;

import com.pathbreaker.accesstoken.entity.Admin;
import com.pathbreaker.accesstoken.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;
    @Autowired
    public CustomUserDetailsServiceImpl(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Admin admin = adminRepository.findByUserName(userName);

        if (admin != null && userName.equals(admin.getUserName())) {

                return new User(
                        admin.getUserName(),
                        admin.getPassword(),
                        new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("USER Not found exception..........");
        }
    }

}
