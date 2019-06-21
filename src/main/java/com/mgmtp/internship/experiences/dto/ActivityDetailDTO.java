package com.mgmtp.internship.experiences.dto;

/**
 * Activity detail.
 *
 * @author httbui.ext
 */
public class ActivityDetailDTO {
    private long id;
    private String name;
    private String description;
    private String categoryName;

    public ActivityDetailDTO() {
    }

    public ActivityDetailDTO(long id, String name, String description, String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryName = categoryName;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
