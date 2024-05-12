package com.test.java.user.management.service;

import com.test.java.user.management.dto.UpdateRequestDto;
import com.test.java.user.management.model.Users;

import java.util.List;

public interface UsersService {
    List<Users> allUsers();

    Users update(UpdateRequestDto updateRequestDto);
}
