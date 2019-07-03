package com.mgmtp.internship.experiences.services;

import com.mgmtp.internship.experiences.dto.ImageDTO;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;


/**
 * Activity Service interface.
 *
 * @author htnguyen
 */
public interface ImageService {

    ImageDTO findImageById(long imageId);

    long insertImage(byte[] imageData);

    @Transactional
    long updateUserImage(long userId, byte[] data);

    boolean validateProfilePicture(InputStream inputStream) throws IOException;
}
