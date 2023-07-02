package com.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout(withDefaults())
                .formLogin(withDefaults());
    }
}
