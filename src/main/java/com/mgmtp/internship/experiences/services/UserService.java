package com.mgmtp.internship.experiences.services;

import com.mgmtp.internship.experiences.config.security.CustomUserDetails;
/**
 * User service interface.
 *
 * @author thuynh
 */
public interface UserService {
    CustomUserDetails getCurrentUser();
}
