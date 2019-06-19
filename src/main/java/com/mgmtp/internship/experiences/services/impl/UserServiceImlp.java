package com.mgmtp.internship.experiences.services.impl;

import com.mgmtp.internship.experiences.exceptions.MD5Exception;
import com.mgmtp.internship.experiences.repositories.UserRepository;
import com.mgmtp.internship.experiences.services.UserService;
import com.mgmtp.internship.experiences.util.MD5Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * userService for veridateUser.
 *
 * @author ttkngo
 */
@Service
public class UserServiceImlp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean validateUser(String username, String password) throws MD5Exception {
        return userRepository.validateUser(username, MD5Encryptor.encrypt(password));
    }

    @Override
    public Boolean checkLogin(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}
