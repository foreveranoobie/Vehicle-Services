package com.test.filter.security;

import com.test.entity.security.EndpointAuthDescription;
import com.test.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndpointAuthorizationFilter extends GenericFilterBean {

    private Map<EndpointAuthDescription, List<String>> authoritiesPerEndpoint;
    private SecurityContextRepository securityContextRepository;

    public EndpointAuthorizationFilter(SecurityContextRepository securityContextRepository) {
        this.authoritiesPerEndpoint = new HashMap<>();
        this.securityContextRepository = securityContextRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        SecurityContext securityContext = securityContextRepository.loadDeferredContext((HttpServletRequest) request).get();
        if (isAuthorised(securityContext.getAuthentication(), (HttpServletRequest) request)) {
            chain.doFilter(request, response);
        } else {
            throw new UnauthorizedException("User is not authorized to access this endpoint");
        }
    }

    private boolean isAuthorised(Authentication authentication, HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String requestMethod = request.getMethod();
        EndpointAuthDescription foundEndpointToCheck = null;
        for (EndpointAuthDescription endpoint : authoritiesPerEndpoint.keySet()) {
            if (endpoint.isMethodAllowed(requestMethod) && requestUri.matches(endpoint.getEndpoint())) {
                foundEndpointToCheck = endpoint;
                break;
            }
        }
        if(foundEndpointToCheck != null) {
            if(authentication != null){
                List<String> allowedEndpointAuthorities = authoritiesPerEndpoint.get(foundEndpointToCheck);
                return authentication.getAuthorities()
                        .stream()
                        .anyMatch(userAuthority -> allowedEndpointAuthorities.contains(userAuthority.getAuthority()));
            } else {
                return false;
            }
        }
        return true;
    }

    private String getEndpointFromUrl(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return url.getFile();
    }

    public EndpointAuthorizationFilter authorizeEndpoint(EndpointAuthDescription endpoint, String... authorities) {
        authoritiesPerEndpoint.put(endpoint, List.of(authorities));
        return this;
    }
}
