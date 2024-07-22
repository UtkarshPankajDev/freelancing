package com.freelance.repository;

import com.freelance.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    UserDetails findByUsername(String username);
}
