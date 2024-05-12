package com.test.java.user.management.controller;

import com.test.java.user.management.dto.LoginRequestDto;
import com.test.java.user.management.dto.LoginResponseDto;
import com.test.java.user.management.dto.RegisterRequestDto;
import com.test.java.user.management.model.Users;
import com.test.java.user.management.service.AuthenticationService;
import com.test.java.user.management.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public Users register(@RequestBody RegisterRequestDto registerRequestDto) {
        return authenticationService.register(registerRequestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto authenticate(@RequestBody LoginRequestDto loginRequestDto) {
        Users authenticatedUser = authenticationService.authenticate(loginRequestDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(jwtToken);
        loginResponseDto.setExpiresIn(jwtService.getExpirationTime());

        return loginResponseDto;
    }
}
