package com.freelance.dto.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    BUYER("buyer"),
    SELLER("seller");

    private final String role;

    @JsonCreator
    UserRole(String role) {
        this.role = role;
    }

    @JsonValue
    public String getValue() {
        return role;
    }
}
