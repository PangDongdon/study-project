package com.study.project.commom.config;

import com.study.project.commom.filter.JwtTokenFilter;
import com.study.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Date: 2020/9/3  14:33
 * @Author: dongdong
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSercurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserService userService;

    private static final String[] ignoreAuthUrls={
            "/agent/v1/login"
    };


    @Override
    protected  void configure(HttpSecurity  httpSecurity) throws  Exception{
        httpSecurity.addFilterBefore(new JwtTokenFilter(userService), UsernamePasswordAuthenticationFilter.class).csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers(ignoreAuthUrls).permitAll().anyRequest().authenticated();

        httpSecurity.exceptionHandling().authenticationEntryPoint(new EntryPointUnauthorizedHandler()).
                accessDeniedHandler(new RequestAccessDeniedHandler());
    }


    public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint{
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
           response.setStatus(401);
        }
    }
    public class RequestAccessDeniedHandler implements  AccessDeniedHandler{
        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
            response.setStatus(403);
        }
    }





}
