package com.mgmtp.internship.experiences.services.impl;

import com.mgmtp.internship.experiences.config.security.CustomUserDetails;
import com.mgmtp.internship.experiences.dto.UserProfileDTO;
import com.mgmtp.internship.experiences.repositories.UserRepository;
import com.mgmtp.internship.experiences.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserRepository userRepository;

    @Override
    public CustomUserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return null;
        Object principal = authentication.getPrincipal();
        return (CustomUserDetails) principal;
    }

    @Override
    public boolean updateProfile(long userId, UserProfileDTO profile) {
        if (userRepository.updateProfile(userId, profile)) {
            CustomUserDetails currentUser = getCurrentUser();
            if (currentUser == null) return false;
            currentUser.getUserProfile().setDisplayName(profile.getDisplayName());
            return true;
        }
        return false;
    }

    @Override
    public boolean checkExitDisplayName(String displayName) {
        return userRepository.checkExitDisplayName(displayName);
    }

}
