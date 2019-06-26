package com.mgmtp.internship.experiences.repositories;

import com.mgmtp.internship.experiences.model.tables.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.mgmtp.internship.experiences.model.tables.tables.User.USER;

/**
 * userRepository for login
 *
 * @author ttkngo
 */
@Component
public class UserRepository {

    @Autowired
    private DSLContext dslContext;

    public UserRecord findUserByUsername(String userName) {
        return dslContext.selectFrom(USER)
                .where(USER.USERNAME.eq(userName)).fetchOne();
    }
}

