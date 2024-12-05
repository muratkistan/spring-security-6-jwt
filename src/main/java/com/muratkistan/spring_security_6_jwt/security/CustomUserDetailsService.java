package com.muratkistan.spring_security_6_jwt.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.muratkistan.spring_security_6_jwt.model.User;
import com.muratkistan.spring_security_6_jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
  
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      var user = userRepository.findByUsername(username);
  
      if (!user.isPresent()) {
        throw new UsernameNotFoundException("User:  '" + username + "' not found");
      }
  
      return org.springframework.security.core.userdetails.User//
          .withUsername(username)
          .password(user.get().getPassword())
          .authorities(user.get().getUserRoles())
          .accountExpired(false)
          .accountLocked(false)
          .credentialsExpired(false)
          .disabled(false)
          .build();
    }
  
  }