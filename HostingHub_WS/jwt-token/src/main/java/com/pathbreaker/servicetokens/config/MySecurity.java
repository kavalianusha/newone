package com.pathbreaker.servicetokens.config;

import com.pathbreaker.servicetokens.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class MySecurity  {


    @Autowired
    private CustomUserDetailsService customerUserDetailsService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
       /* return http
                .csrf()
                .disable()
                .cors()
                .disable().authorizeRequests()
                .requestMatchers("/**","/generate/jwttoken")
                .permitAll().anyRequest()
                .authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build();*/

        return http
                .csrf()
                .disable()
                .authorizeHttpRequests(r -> r.requestMatchers(new AntPathRequestMatcher("/**"
                        ))
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                        .build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(customerUserDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

   @Bean
   public LogoutFilter logoutFilter() {

       SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
       securityContextLogoutHandler.setInvalidateHttpSession(true);

       LogoutFilter logoutFilter = new LogoutFilter("/", securityContextLogoutHandler);
       logoutFilter.setLogoutRequestMatcher(new AntPathRequestMatcher("/logout"));
       return logoutFilter;
   }
    @Bean
    public PasswordEncoder passwordEncoder(){
       return NoOpPasswordEncoder.getInstance();
    }
}
