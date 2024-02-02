package com.pathbreaker.services.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@Component
@PropertySource(value={"classpath:application-common.yml"})
public class CustomFilter extends OncePerRequestFilter {

    Logger log = LoggerFactory.getLogger(CustomFilter.class);
    @Value("${service.auth.public-token}")
    private String publicToken;

    @Value("#{'${service.auth.exclusion-list}'.split(',')}")
    private List<String> exclusionList;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        log.info("[CustomFilter] - Inside the do filter method executed..");
        log.info("Local Port : "+request.getLocalPort());
        log.info("server Name : "+request.getServerName());
        log.info("Server Path : "+request.getServletPath());
        log.info("Request Uri is : "+request.getRequestURI());

        System.out.println("the public token :" +publicToken);
        System.out.println("the exclusion list is : "+exclusionList);

        if (!validatePattern(exclusionList,request.getRequestURI())){
            String token = request.getHeader("authorization");
            System.out.println("the token is : "+token);

            if(!StringUtils.hasLength(token)){
                AuthenticationException ex = new AuthenticationException("Authorization header is missing");
                response.sendError(HttpStatus.UNAUTHORIZED.value(),ex.getMessage());
                return;
            }
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7); // Remove the "Bearer " prefix
            } else {
                response.sendError(HttpStatus.UNAUTHORIZED.value(),"Invalid Token format");
                return;
            }
            if (!jwtUtil.isJwtTokenValid(token)) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(),"Token is expired");
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
    private  boolean validatePattern(List<String> values, String path){
        return values.stream().anyMatch(value -> {
            System.out.println(value+" --Path " +path+" -- "+ Pattern.compile(value).matcher(path).matches());
            return Pattern.compile(value).matcher(path).matches();
        });
    }

}
