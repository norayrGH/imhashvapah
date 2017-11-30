package com.example.imhashvapahversion1.version1.config;

import com.example.imhashvapahversion1.version1.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final SimpleGrantedAuthority PERSON = new SimpleGrantedAuthority(  "PERSON");
    public static final SimpleGrantedAuthority ORGANIZATION = new SimpleGrantedAuthority(  "ORGANIZATION");
    public static final SimpleGrantedAuthority ACOUNTMANT = new SimpleGrantedAuthority(  "ACOUNTMANT");

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
              .userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http

            .authorizeRequests()
              .antMatchers("*/**")
              .permitAll()
              .antMatchers("/")
              .authenticated()
              .and()
              .formLogin()
              .loginPage("/account/login")

              .permitAll()
                .successForwardUrl("/")
      ;
    }
}
