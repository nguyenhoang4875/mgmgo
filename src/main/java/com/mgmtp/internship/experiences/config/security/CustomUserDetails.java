package com.mgmtp.internship.experiences.config.security;

import com.mgmtp.internship.experiences.dto.UserProfileDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Objects;

/**
 * Custom User Details.
 *
 * @author ntynguyen
 */
public class CustomUserDetails extends User {

    private long id;

    private UserProfileDTO userProfile;

    public CustomUserDetails(long id, UserProfileDTO userProfile, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.userProfile = userProfile;
    }

    public CustomUserDetails(long id, UserProfileDTO userProfile, String username, String password, boolean enabled,
                             boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.userProfile = userProfile;
    }

    public long getId() {
        return id;
    }

    public UserProfileDTO getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileDTO userProfile) {
        this.userProfile = userProfile;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomUserDetails that = (CustomUserDetails) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}

