package com.test.filter.security;

import com.google.gson.Gson;
import com.test.dto.UserAuthenticationDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.BufferedReader;
import java.io.IOException;

public class JsonBodyUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JsonBodyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/login", "POST"), authenticationManager);
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        UserAuthenticationDto userAuthenticationDto = getUserAuthenticationDto(request);
        String username = userAuthenticationDto == null ? "" : userAuthenticationDto.getUsername();
        String password = userAuthenticationDto == null ? "" : userAuthenticationDto.getPassword();
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,
                password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        Authentication result = this.getAuthenticationManager().authenticate(authRequest);
/*        SecurityContext securityContext = SecurityContextHolder.getDeferredContext().get();
        securityContext.setAuthentication(result);
        securityContextRepository.saveContext(securityContext, request, response);*/
        return result;
    }

    private void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    private UserAuthenticationDto getUserAuthenticationDto(HttpServletRequest request) {
        BufferedReader reader;
        UserAuthenticationDto userAuthenticationDto;
        try {
            reader = request.getReader();
            Gson gson = new Gson();
            userAuthenticationDto = gson.fromJson(reader, UserAuthenticationDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userAuthenticationDto;
    }
}
