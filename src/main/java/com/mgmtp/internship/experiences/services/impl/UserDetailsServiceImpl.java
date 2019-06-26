package com.mgmtp.internship.experiences.services.impl;

import com.mgmtp.internship.experiences.config.security.CustomUserDetails;
import com.mgmtp.internship.experiences.model.tables.tables.records.UserRecord;
import com.mgmtp.internship.experiences.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * User Detail Service Impl.
 *
 * @author ntynguyen
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRecord userRecord = userRepository.findUserByUsername(username);
        if (userRecord == null) {
            throw new UsernameNotFoundException("username not found in database!");
        }
        return new CustomUserDetails(userRecord.getId(), userRecord.getUsername(), userRecord.getPassword(), Collections.emptyList());
    }
}
