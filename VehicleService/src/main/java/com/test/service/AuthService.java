package com.test.service;

import com.test.dto.UserAuthenticationDto;

public interface AuthService {
    boolean isAuthenticated();

    boolean authenticate(UserAuthenticationDto userAuthenticationDto);
}
