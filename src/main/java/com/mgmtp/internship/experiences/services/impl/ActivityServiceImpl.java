package com.mgmtp.internship.experiences.services.impl;


import com.mgmtp.internship.experiences.dto.ActivityDTO;
import com.mgmtp.internship.experiences.repositories.ActivityRepository;
import com.mgmtp.internship.experiences.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Activity service for Activity DTO.
 *
 * @author thuynh
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public List<ActivityDTO> findAll() {
        return activityRepository.findAll();
    }
}
