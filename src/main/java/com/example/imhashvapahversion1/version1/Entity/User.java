package com.example.imhashvapahversion1.version1.Entity;

import com.example.imhashvapahversion1.version1.Entity.enums.Role;
import com.example.imhashvapahversion1.version1.config.WebSecurityConfig;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name="tbl_user")
public class User implements UserDetails, Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "col_username" , unique = true)
    private String email;
    @Column(name = "col_password")
    private String password;
    @Column(name = "col_enabled")
    private boolean enabled;
    @Column(name = "col_locked")
    private boolean locked;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;


    public User() {

        roles = new ArrayList<>();
    }

    public User(String email, String password, boolean enabled, boolean locked, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.locked = locked;
        this.roles = roles;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));
        }

        return authorities;

    }


    @Override
    public String getPassword() {


        return password ;
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
        return true;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
