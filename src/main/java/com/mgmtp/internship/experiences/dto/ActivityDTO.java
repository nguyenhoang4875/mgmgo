package com.mgmtp.internship.experiences.dto;

/**
 * Activity DTO.
 *
 * @author thuynh
 */
public class ActivityDTO {
    private long id;
    private String name;
    private Long imageId;

    public ActivityDTO() {
    }

    public ActivityDTO(long id, String name, Long imageId) {
        this.id = id;
        this.name = name;
        this.imageId = imageId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
