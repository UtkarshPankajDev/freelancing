package com.freelance.service;

import com.freelance.dto.auth.AuthDto;
import com.freelance.entity.AuthEntity;
import com.freelance.exception.InvalidOperationException;
import com.freelance.repository.AuthRepository;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    AuthRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public UserDetails signUp(AuthDto data) throws InvalidOperationException {
        if (repository.findByUsername(data.getUsername()) != null) {
            throw new InvalidOperationException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        AuthEntity newUser = new AuthEntity(data.getUsername(), encryptedPassword, data.getRole());
        newUser.setRoleId(data.getRoleId());
        return repository.save(newUser);
    }
}
