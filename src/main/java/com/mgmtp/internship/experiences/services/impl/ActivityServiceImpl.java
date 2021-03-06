package com.mgmtp.internship.experiences.services.impl;


import com.mgmtp.internship.experiences.dto.ActivityDTO;
import com.mgmtp.internship.experiences.dto.ActivityDetailDTO;
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

    @Override
    public ActivityDetailDTO findById(long activityId) {
        return activityRepository.findById(activityId);
    }

    @Override
    public int update(ActivityDetailDTO activityDetailDTO) {
        return activityRepository.update(activityDetailDTO);
    }

    @Override
    public int create(ActivityDetailDTO activityDetailDTO) {
        return activityRepository.create(activityDetailDTO);
    }

    @Override
    public boolean checkExistName(String activityName) {
        return activityRepository.checkExistName(activityName);
    }

    @Override
    public boolean checkExistNameForUpdate(long activityId, String activityName) {
        return activityRepository.checkExistNameForUpdate(activityId, activityName);
    }



    @Override
    public List<ActivityDTO> search(String text) {
        return activityRepository.search(text);
    }
}
