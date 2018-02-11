package com.example.imhashvapahversion1.version1.config;

import com.example.imhashvapahversion1.version1.Entity.formater.OrganizationFarmater;
import com.example.imhashvapahversion1.version1.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public/**", "/static/**")
                .permitAll()
                .antMatchers("/account/**")
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/public/login")
                .successForwardUrl("/account/login/success")
                .and()
                .logout()
                .logoutUrl("/account/logout")
                .logoutSuccessUrl("/public/login");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FormattingConversionServiceFactoryBean getFormattingConversionServiceFactoryBean(){
        FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean = new FormattingConversionServiceFactoryBean();
        Set<Formatter> formater = new HashSet<>();
        formater.add( new OrganizationFarmater());
        formattingConversionServiceFactoryBean.setFormatters(formater);
        return formattingConversionServiceFactoryBean;
    }
}
