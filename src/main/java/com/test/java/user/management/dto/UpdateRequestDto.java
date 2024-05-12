package com.test.java.user.management.dto;

import lombok.Data;

@Data
public class UpdateRequestDto {
    private String username;
    private String email;
    private String password;
}
