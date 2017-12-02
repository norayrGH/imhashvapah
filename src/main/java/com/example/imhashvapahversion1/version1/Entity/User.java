package com.example.imhashvapahversion1.version1.Entity;

import com.example.imhashvapahversion1.version1.config.WebSecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
@Entity
public class User implements UserDetails,Serializable {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column( name = "col_username" )
    private String email;
    @Column( name = "col_password" )
    private String password;
    @Column( name = "col_enabled" )
    private boolean enabled;
    @Column( name = "col_locked" )
    private boolean locked;
   @Transient

    private Collection<? extends GrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>() {{
        add(WebSecurityConfig.ACCOUNTANT);
        add(WebSecurityConfig.ORGANIZATION);
        add(WebSecurityConfig.PERSON);
    }};

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                ;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled ;
    }
}
