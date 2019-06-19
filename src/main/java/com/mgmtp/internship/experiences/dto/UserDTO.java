package com.mgmtp.internship.experiences.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * UserDto.
 *
 * @author ntynguyen
 */
public class UserDTO {
    @NotNull
    private String userName;
    @NotNull
    private String password;

    public UserDTO() {
    }

    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userName, userDTO.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}

