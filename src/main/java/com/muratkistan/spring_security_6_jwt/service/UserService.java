package com.muratkistan.spring_security_6_jwt.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.muratkistan.spring_security_6_jwt.dto.AuthenticationRequest;
import com.muratkistan.spring_security_6_jwt.dto.RegisterRequest;
import com.muratkistan.spring_security_6_jwt.exception.CustomException;
import com.muratkistan.spring_security_6_jwt.model.User;
import com.muratkistan.spring_security_6_jwt.repository.UserRepository;
import com.muratkistan.spring_security_6_jwt.security.AuthenticationResponse;
import com.muratkistan.spring_security_6_jwt.security.token.TokenProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final TokenProvider tokenProvider;

        public AuthenticationResponse register(RegisterRequest request) {

                if (!userRepository.existsByUsername(request.getUsername())) {
                        var user = User.builder()
                                        .username(request.getUsername())
                                        .email(request.getEmail())
                                        .password(passwordEncoder.encode(request.getPassword()))
                                        .userRoles(request.getRoles())
                                        .build();
                        userRepository.save(user);
                        var jwtToken = tokenProvider.generateToken(user);
                        return AuthenticationResponse.builder()
                                        .accessToken(jwtToken)
                                        .build();
                } else {
                        throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
                }
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));
                var user = userRepository.findByUsername(request.getUsername());
                var jwtToken = tokenProvider.generateToken(user.get());
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }

}
