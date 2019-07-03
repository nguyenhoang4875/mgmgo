package com.mgmtp.internship.experiences.services;

import com.mgmtp.internship.experiences.config.security.CustomUserDetails;
import com.mgmtp.internship.experiences.dto.UserProfileDTO;

/**
 * User service interface.
 *
 * @author thuynh
 */
public interface UserService {
    CustomUserDetails getCurrentUser();

    boolean updateProfile(long userId, UserProfileDTO profile);

    boolean checkExitDisplayName(String displayName);
}
