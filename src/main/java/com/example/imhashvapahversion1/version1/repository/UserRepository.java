package com.example.imhashvapahversion1.version1.repository;

import com.example.imhashvapahversion1.version1.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends CrudRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.email=:username")
    UserDetails loadUserByUsername(@Param("username") String username);
}
