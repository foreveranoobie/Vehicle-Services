package com.test.service.impl;

import com.test.dto.UserAuthenticationDto;
import com.test.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserManager userManager;

    @Override
    public boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    @Override
    public boolean authenticate(UserAuthenticationDto userAuthenticationDto) {
        UserDetails user = userManager.loadUserByUsername(userAuthenticationDto.getUsername());
        if (user.getPassword().equals(userAuthenticationDto.getPassword())) {

            return true;
        }
        return user.getPassword().equals(userAuthenticationDto.getPassword());
    }
}
