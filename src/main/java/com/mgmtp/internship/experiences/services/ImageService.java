package com.mgmtp.internship.experiences.services;

import com.mgmtp.internship.experiences.dto.ImageDTO;


/**
 * Activity Service interface.
 *
 * @author htnguyen
 */
public interface ImageService {

    ImageDTO findImageById(long imageId);

    Long updateActivityImage(long activityId, byte[] imageData);
}
