package com.example.springproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springproject.entity.User;
import com.example.springproject.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user = userRepository.findByUserNameIgnoreCase(userName);
		CustomUserDetails userDetails=null;
		if(user!=null) {
			 userDetails= new CustomUserDetails();
			 userDetails.setUser(user);
		}
		else {
			throw new UsernameNotFoundException("User Not exists with name :"+userName);
		}
		return userDetails;
	}

}
