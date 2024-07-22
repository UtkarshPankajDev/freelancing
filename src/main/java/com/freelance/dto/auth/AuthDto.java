package com.freelance.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {

    private Long roleId;

    private String username;

    private String password;

    private UserRole role;
}
