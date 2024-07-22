package com.freelance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPartyDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
}
