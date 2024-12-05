package com.muratkistan.spring_security_6_jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muratkistan.spring_security_6_jwt.model.User;

public interface UserRepository  extends JpaRepository<User, Integer> {
        
    Optional<User> findByUsername(String username);
    
    boolean existsByUsername(String username);
  
    void deleteByUsername(String username);

}
