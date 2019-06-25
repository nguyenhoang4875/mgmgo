package com.mgmtp.internship.experiences.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Activity detail.
 *
 * @author httbui.ext
 */
public class ActivityDetailDTO {


    private long id;

    @NotNull(message = "Name may not be null")
    @NotBlank(message = "Name may not be blank")
    @Size(max = 100, message = "You can't not write more than 100 characters")
    private String name;


    @NotNull(message = "Description may not be null")
    @NotBlank(message = "Description may not be blank")
    @Size(max = 100000, message = "You can't not write more than 10000 characters")
    private String description;

    public ActivityDetailDTO() {
    }

    public ActivityDetailDTO(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
