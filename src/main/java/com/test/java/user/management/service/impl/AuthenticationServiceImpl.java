package com.test.java.user.management.service.impl;

import com.test.java.user.management.dto.LoginRequestDto;
import com.test.java.user.management.dto.RegisterRequestDto;
import com.test.java.user.management.model.Users;
import com.test.java.user.management.repository.UsersRepository;
import com.test.java.user.management.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    public Users register(RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByUsername(registerRequestDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already used!");
        }

        if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already in use!");
        }

        Users user = new Users();
        user.setUsername(registerRequestDto.getUsername());
        user.setEmail(registerRequestDto.getEmail());
        user.setCreateDate(OffsetDateTime.now());
        user.setCreateBy("system");
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        return userRepository.save(user);
    }

    public Users authenticate(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        return userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow();
    }
}
