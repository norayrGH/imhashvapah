package com.example.imhashvapahversion1.version1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    public static final SimpleGrantedAuthority AUTHORITY_ADMIN = new SimpleGrantedAuthority("ROLE_" + WebSecurityConfig.ROLE_ADMIN);
    public static final SimpleGrantedAuthority AUTHORITY_USER  = new SimpleGrantedAuthority("ROLE_" + WebSecurityConfig.ROLE_USER);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("account/login").permitAll()
                .antMatchers("account/register").permitAll()
                .antMatchers("/").hasAnyRole(ROLE_USER, ROLE_ADMIN)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("account/login")
                .successForwardUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("account/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);


    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password("pass")
                .roles(ROLE_ADMIN, ROLE_USER)
                .and()
                .withUser("user")
                .password("pass")
                .roles(ROLE_USER);
    }
}