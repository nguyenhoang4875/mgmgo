package com.mgmtp.internship.experiences.services;

import com.mgmtp.internship.experiences.exceptions.MD5Exception;

import javax.servlet.http.HttpSession;

/**
 * userService for veridateUser.
 *
 * @author ttkngo
 */
public interface UserService {
    Boolean validateUser(String username, String password) throws MD5Exception;

    Boolean checkLogin(HttpSession session);
}
