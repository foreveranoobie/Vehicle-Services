package com.test.unit.filter;

import com.test.entity.Authority;
import com.test.entity.User;
import com.test.entity.security.EndpointAuthDescription;
import com.test.exception.UnauthorizedException;
import com.test.filter.security.EndpointAuthorizationFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.SecurityContextRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EndpointAuthorizationFilterUnitTest {
    @Mock
    private SecurityContextRepository securityContextRepository;

    @InjectMocks
    private EndpointAuthorizationFilter sut;

    @Test
    public void shouldContinueFilterChain_whenDoFilter_givenRequestEndpointPermittedToAll() throws ServletException, IOException {
        //given
        sut.authorizeEndpoint(new EndpointAuthDescription("/some-endpoint", Arrays.asList("GET", "POST")), "ADMIN");

        HttpServletRequest givenRequest = new MockHttpServletRequest("GET", "/test-endpoint");
        FilterChain givenFilterChain = mock(FilterChain.class);
        SecurityContext mockSecurityContext = mock(SecurityContext.class);
        DeferredSecurityContext mockDeferredSecurityContext = mock(DeferredSecurityContext.class);
        doReturn(mockDeferredSecurityContext).when(securityContextRepository).loadDeferredContext(eq(givenRequest));
        doReturn(mockSecurityContext).when(mockDeferredSecurityContext).get();

        //when
        sut.doFilter(givenRequest, null, givenFilterChain);

        //then
        verify(givenFilterChain).doFilter(eq(givenRequest), eq(null));
    }

    @Test
    public void shouldContinueFilterChain_whenDoFilter_givenAuthorizedEndpointAndUnauthorizedMethod() throws ServletException, IOException {
        //given
        sut.authorizeEndpoint(new EndpointAuthDescription("/some-endpoint/[0-9]+", Arrays.asList("DELETE", "PUT")), "ADMIN");

        HttpServletRequest givenRequest = new MockHttpServletRequest("GET", "/some-endpoint/123");
        FilterChain givenFilterChain = mock(FilterChain.class);
        SecurityContext mockSecurityContext = mock(SecurityContext.class);
        DeferredSecurityContext mockDeferredSecurityContext = mock(DeferredSecurityContext.class);
        doReturn(mockDeferredSecurityContext).when(securityContextRepository).loadDeferredContext(eq(givenRequest));
        doReturn(mockSecurityContext).when(mockDeferredSecurityContext).get();

        //when
        sut.doFilter(givenRequest, null, givenFilterChain);

        //then
        verify(givenFilterChain).doFilter(eq(givenRequest), eq(null));
    }

    @Test
    public void shouldContinueFilterChain_whenDoFilter_givenAuthorizedRequestAndCorrectUserRole() throws ServletException, IOException {
        //given
        sut.authorizeEndpoint(new EndpointAuthDescription("/some-endpoint/[0-9]+", Arrays.asList("DELETE", "PUT")),
                "ADMIN", "USER");

        HttpServletRequest givenRequest = new MockHttpServletRequest("PUT", "/some-endpoint/123");
        FilterChain givenFilterChain = mock(FilterChain.class);
        SecurityContext mockSecurityContext = mock(SecurityContext.class);
        DeferredSecurityContext mockDeferredSecurityContext = mock(DeferredSecurityContext.class);
        Set<Authority> userAuthorities = Set.of(new Authority("TEST"), new Authority("ADMIN"));
        Authentication authentication = new AnonymousAuthenticationToken("123",
                new User("test", "test",
                userAuthorities, true, true, true, true),
                userAuthorities);
        doReturn(authentication).when(mockSecurityContext).getAuthentication();
        doReturn(mockDeferredSecurityContext).when(securityContextRepository).loadDeferredContext(eq(givenRequest));
        doReturn(mockSecurityContext).when(mockDeferredSecurityContext).get();

        //when
        sut.doFilter(givenRequest, null, givenFilterChain);

        //then
        verify(givenFilterChain).doFilter(eq(givenRequest), eq(null));
    }

    @Test(expected = UnauthorizedException.class)
    public void shouldThrowUnauthorizedException_whenDoFilter_givenAbsentUser() throws ServletException, IOException {
        //given
        sut.authorizeEndpoint(new EndpointAuthDescription("/some-endpoint/[0-9]+", Arrays.asList("DELETE", "PUT")),
                "ADMIN", "USER");

        HttpServletRequest givenRequest = new MockHttpServletRequest("PUT", "/some-endpoint/123");
        FilterChain givenFilterChain = mock(FilterChain.class);
        SecurityContext mockSecurityContext = mock(SecurityContext.class);
        DeferredSecurityContext mockDeferredSecurityContext = mock(DeferredSecurityContext.class);
        doReturn(mockDeferredSecurityContext).when(securityContextRepository).loadDeferredContext(eq(givenRequest));
        doReturn(mockSecurityContext).when(mockDeferredSecurityContext).get();

        //when
        sut.doFilter(givenRequest, null, givenFilterChain);

        //then - expecting exception to be thrown
    }

    @Test(expected = UnauthorizedException.class)
    public void shouldThrowUnauthorizedException_whenDoFilter_givenUnauthorizedUserRole() throws ServletException, IOException {
        //given
        sut.authorizeEndpoint(new EndpointAuthDescription("/some-endpoint/[0-9]+", Arrays.asList("DELETE", "PUT")),
                "ADMIN", "USER");

        HttpServletRequest givenRequest = new MockHttpServletRequest("PUT", "/some-endpoint/123");
        FilterChain givenFilterChain = mock(FilterChain.class);
        SecurityContext mockSecurityContext = mock(SecurityContext.class);
        DeferredSecurityContext mockDeferredSecurityContext = mock(DeferredSecurityContext.class);
        Set<Authority> userAuthorities = Set.of(new Authority("OWNER"), new Authority("TEST"));
        Authentication authentication = new AnonymousAuthenticationToken("123",
                new User("test", "test",
                        userAuthorities, true, true, true, true),
                userAuthorities);
        doReturn(authentication).when(mockSecurityContext).getAuthentication();
        doReturn(mockDeferredSecurityContext).when(securityContextRepository).loadDeferredContext(eq(givenRequest));
        doReturn(mockSecurityContext).when(mockDeferredSecurityContext).get();

        //when
        sut.doFilter(givenRequest, null, givenFilterChain);

        //then - expecting exception to be thrown
    }
}
