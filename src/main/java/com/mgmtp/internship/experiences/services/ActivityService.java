package com.mgmtp.internship.experiences.services;

import com.mgmtp.internship.experiences.dto.ActivityDTO;
import com.mgmtp.internship.experiences.dto.ActivityDetailDTO;

import java.util.List;

/**
 * Activity Service interface.
 *
 * @author thuynh
 */
public interface ActivityService {
    List<ActivityDTO> findAll();

    ActivityDetailDTO findById(long activityId);

    int updateActivity(long activityId, String newName, String newDescription);
}
