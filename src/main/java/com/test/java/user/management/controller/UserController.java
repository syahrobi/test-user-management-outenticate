package com.test.java.user.management.controller;

import com.test.java.user.management.dto.UpdateRequestDto;
import com.test.java.user.management.model.Users;
import com.test.java.user.management.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping()
    public Users update(@RequestBody UpdateRequestDto updateRequestDto) {
        return usersService.update(updateRequestDto);
    }

    @GetMapping
    public List<Users> allUsers() {
        return usersService.allUsers();
    }
}
