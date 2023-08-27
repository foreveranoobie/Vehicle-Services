package com.test.controller;

import com.test.dto.UserAuthenticationDto;
import com.test.service.AuthService;
import com.test.service.impl.UserManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public void login(@RequestBody @NotNull @Valid UserAuthenticationDto userAuthenticationDto) {
        if(authService.isAuthenticated()){

        } else {
            authService.authenticate(userAuthenticationDto);
        }
    }

}
