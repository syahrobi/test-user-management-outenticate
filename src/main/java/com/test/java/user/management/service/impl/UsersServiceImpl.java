package com.test.java.user.management.service.impl;

import com.test.java.user.management.dto.UpdateRequestDto;
import com.test.java.user.management.model.Users;
import com.test.java.user.management.repository.UsersRepository;
import com.test.java.user.management.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Users> allUsers() {
        List<Users> users = new ArrayList<>();

        usersRepository.findAll().forEach(users::add);

        return users;
    }

    public Users update(UpdateRequestDto updateRequestDto) {
        Optional<Users> usersOptional = usersRepository.findByEmail(updateRequestDto.getEmail());
        if (usersOptional.isPresent()) {
            usersOptional.get().setUsername(updateRequestDto.getUsername());
            usersOptional.get().setEmail(updateRequestDto.getEmail());
            usersOptional.get().setUpdateDate(OffsetDateTime.now());
            usersOptional.get().setUpdateBy("system");
            usersOptional.get().setPassword(passwordEncoder.encode(updateRequestDto.getPassword()));
            return usersRepository.save(usersOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found!");
        }

    }
}