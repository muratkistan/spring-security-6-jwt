package com.muratkistan.spring_security_6_jwt.dto;

import java.util.List;

import com.muratkistan.spring_security_6_jwt.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String username;
  private String email;
  private String password;
  private List<Role> roles;
}



