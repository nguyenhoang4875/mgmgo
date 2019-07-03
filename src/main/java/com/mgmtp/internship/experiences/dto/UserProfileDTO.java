package com.mgmtp.internship.experiences.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * User profile Dto.
 *
 * @author thuynh
 */
public class UserProfileDTO {

    private Long imageId;

    @NotBlank(message = "Display Name may not be blank")
    @Size(max = 30, message = "You can't not write more than 30 characters")
    private String displayName;

    public UserProfileDTO() {
    }

    public UserProfileDTO(Long imageId, String displayName) {
        this.imageId = imageId;
        this.displayName = displayName;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileDTO that = (UserProfileDTO) o;
        return Objects.equals(imageId, that.imageId) &&
                Objects.equals(displayName, that.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, displayName);
    }
}
