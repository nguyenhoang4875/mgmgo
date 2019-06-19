package com.mgmtp.internship.experiences.repositories;

import static com.mgmtp.internship.experiences.model.tables.tables.User.USER;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * userRepository for login
 *
 * @author ttkngo
 */
@Component
public class UserRepository {
    @Autowired
    private DSLContext dslContext;

    public boolean validateUser(String username, String password){
        return  dslContext.selectFrom(USER)
                .where(USER.USERNAME.eq(username))
                .and(USER.PASSWORD.eq(password))
                .fetchOne(0, boolean.class);
    }
}

