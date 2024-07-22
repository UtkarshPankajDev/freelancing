package com.freelance.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    private String username;
    private String password;
    private UserRole role;
    private String firstName;
    private String lastName;
}
