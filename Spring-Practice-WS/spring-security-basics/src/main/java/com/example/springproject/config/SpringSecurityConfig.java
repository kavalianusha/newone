package com.example.springproject.config;
							  
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.
AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@SuppressWarnings("deprecation")
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception{
			auth.inMemoryAuthentication().withUser("Anusha").password("1309").roles(
			"ADMIN"); 
			auth.inMemoryAuthentication().withUser("Mamatha").password("1234").roles(
					"USER"); 
		}
	 
	 //this is security for all the controller api's
	 
//	 @Override					  				  
//     protected void configure(HttpSecurity http) throws Exception{
//
//         http.csrf().disable();
//           http.authorizeRequests().anyRequest().fullyAuthenticated().
//                 httpBasic();
//         }
	 
	 //security for the particular controller api's
	 
//	 @Override					  				  
//     protected void configure(HttpSecurity http) throws Exception{
//
//        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/result/**")
//             .fullyAuthenticated().
//                 httpBasic();
//         }
	 
	 //security based on the role 
		/*
		 * @Override protected void configure(HttpSecurity http) throws Exception {
		 * http.csrf().disable(); http.authorizeRequests().antMatchers("/result/**")
		 * .hasAnyRole("USER").anyRequest().fullyAuthenticated().and() .httpBasic(); }
		 */
							  
							 
     @Bean 
     public static NoOpPasswordEncoder passwordEncoder() {
    	 return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance(); 
    	 }
							
}
							 