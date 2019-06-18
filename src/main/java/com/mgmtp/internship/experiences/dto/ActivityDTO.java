package com.mgmtp.internship.experiences.dto;

import com.mgmtp.internship.experiences.model.tables.tables.records.ActivityRecord;

/**
 * Activity DTO.
 *
 * @author thuynh
 */
public class ActivityDTO {
    private long id;
    private String name;

    public ActivityDTO() {
    }

    public ActivityDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ActivityDTO(ActivityRecord activityRecord) {
        this.id = activityRecord.getId();
        this.name = activityRecord.getName();
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
}
