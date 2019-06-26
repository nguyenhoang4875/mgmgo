package com.mgmtp.internship.experiences.services.impl;

import com.mgmtp.internship.experiences.config.security.CustomUserDetails;
import com.mgmtp.internship.experiences.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * User service.
 *
 * @author thuynh
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public CustomUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return null;
        Object principal = authentication.getPrincipal();
        return (CustomUserDetails) principal;
    }

}
