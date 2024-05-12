package com.test.java.user.management.service;

import com.test.java.user.management.dto.LoginRequestDto;
import com.test.java.user.management.dto.RegisterRequestDto;
import com.test.java.user.management.model.Users;

public interface AuthenticationService {

    Users register(RegisterRequestDto registerRequestDto);

    Users authenticate(LoginRequestDto loginRequestDto);
}
