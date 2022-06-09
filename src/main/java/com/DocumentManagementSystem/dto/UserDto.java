package com.DocumentManagementSystem.dto;

import com.DocumentManagementSystem.model.Role;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Enumerated;

@Data
@Accessors(chain = true)
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Role role;
}
