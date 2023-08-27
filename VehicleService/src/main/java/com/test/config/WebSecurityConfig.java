package com.test.config;

import com.test.filter.security.CustomDaoAuthenticationProvider;
import com.test.filter.security.EndpointAuthorizationFilter;
import com.test.filter.security.JsonBodyUsernamePasswordAuthenticationFilter;
import com.test.service.impl.UserManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.context.*;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AbstractAuthenticationProcessingFilter authenticationProcessingFilter,
                                           SecurityContextRepository securityContextRepository,
                                           EndpointAuthorizationFilter endpointAuthorizationFilter) throws Exception {
        XorCsrfTokenRequestAttributeHandler delegate = new XorCsrfTokenRequestAttributeHandler();
        // set the name of the attribute the CsrfToken will be populated on
        delegate.setCsrfRequestAttributeName("_csrf");
        // Use only the handle() method of XorCsrfTokenRequestAttributeHandler and the
        // default implementation of resolveCsrfTokenValue() from CsrfTokenRequestHandler
        CsrfTokenRequestHandler requestHandler = delegate::handle;

        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer
                        .csrfTokenRequestHandler(requestHandler)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(req -> req
                        .requestMatchers(HttpMethod.POST, "/vehicle-service").hasAnyRole("SERVICE_OWNER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/vehicle-service/*").hasAnyRole("SERVICE_OWNER", "ADMIN")
                        .requestMatchers("/vehicle-service/*").authenticated()
                        .anyRequest().permitAll()
                )
                .addFilterAfter(authenticationProcessingFilter, LogoutFilter.class)
                .addFilterBefore(endpointAuthorizationFilter, SecurityContextHolderFilter.class)
                .securityContext((securityContext) ->
                        securityContext.securityContextRepository(securityContextRepository)) // Add Security Context Holder Repository
                .logout(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AbstractAuthenticationProcessingFilter authenticationFilter(ProviderManager providerManager,
                                                                       SecurityContextRepository securityContextRepository){
        JsonBodyUsernamePasswordAuthenticationFilter filter =
                new JsonBodyUsernamePasswordAuthenticationFilter(providerManager);
        filter.setFilterProcessesUrl("/login");
        filter.setAuthenticationSuccessHandler((request, response, authentication) -> {});
        filter.setSecurityContextRepository(securityContextRepository);
        return filter;
    }

    @Bean
    public ProviderManager providerManager(AuthenticationProvider authenticationProvider,
                                           AuthenticationEventPublisher authenticationEventPublisher){
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setAuthenticationEventPublisher(authenticationEventPublisher);
        return providerManager;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserManager userManager){
        DaoAuthenticationProvider authenticationProvider = new CustomDaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userManager);
        authenticationProvider.setPasswordEncoder(encoder());
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(ApplicationContext applicationContext){
        return new DefaultAuthenticationEventPublisher(applicationContext);
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public EndpointAuthorizationFilter endpointAuthorizationFilter(SecurityContextRepository securityContextRepository){
        return new EndpointAuthorizationFilter(securityContextRepository);
    }
}
