package com.test.java.user.management.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
