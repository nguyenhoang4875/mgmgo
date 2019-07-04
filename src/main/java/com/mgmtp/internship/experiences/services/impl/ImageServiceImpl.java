package com.mgmtp.internship.experiences.services.impl;

import com.mgmtp.internship.experiences.dto.ImageDTO;
import com.mgmtp.internship.experiences.repositories.ImageRepository;
import com.mgmtp.internship.experiences.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Activity Service interface.
 *
 * @author htnguyen
 */

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public ImageDTO findImageById(long imageId) {
        return imageRepository.findImageById(imageId);
    }

    @Override
    public Long updateActivityImage(long activityId, byte[] imageData) {
        return imageRepository.updateActivityImage(activityId, imageData);
    }
}
