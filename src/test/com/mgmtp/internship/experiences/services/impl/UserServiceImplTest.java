package com.mgmtp.internship.experiences.services.impl;

import com.mgmtp.internship.experiences.config.security.CustomUserDetails;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for user service.
 *
 * @author thuynh
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void shouldReturnCustomUserDetail() {
        CustomUserDetails expectedUser = new CustomUserDetails(1L, "username", "pass", Collections.emptyList());
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getPrincipal()).thenReturn(expectedUser);

        CustomUserDetails actualUser = userService.getCurrentUser();

        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void shouldReturnNullIfIfNotLogged() {
        CustomUserDetails expectedUser = null;
        Authentication authentication = null;
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        CustomUserDetails actualUser = userService.getCurrentUser();

        Assert.assertEquals(expectedUser, actualUser);
    }
}

