package com.mgmtp.internship.experiences.services;

import com.mgmtp.internship.experiences.dto.ActivityDTO;

import java.util.List;

/**
 * Activity Service interface.
 *
 * @author thuynh
 */
public interface ActivityService {
    List<ActivityDTO> findAll();
}
