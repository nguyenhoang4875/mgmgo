package com.mgmtp.internship.experiences.services.impl;

import com.mgmtp.internship.experiences.dto.ImageDTO;
import com.mgmtp.internship.experiences.repositories.ImageRepository;
import com.mgmtp.internship.experiences.repositories.UserRepository;
import com.mgmtp.internship.experiences.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


/**
 * Activity Service interface.
 *
 * @author htnguyen
 */

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ImageDTO findImageById(long imageId) {
        return imageRepository.findImageById(imageId);
    }

    public long insertImage(byte[] imageData) {
        return imageRepository.insert(imageData);
    }

    @Override
    @Transactional
    public long updateUserImage(long userId, byte[] data) {
        Long oldImageId = userRepository.getImageId(userId);
        long imageId = insertImage(data);
        userRepository.setImageId(userId,imageId);
        if(oldImageId!=null) imageRepository.deleteImage(oldImageId);
        return imageId;
    }

    @Override
    public boolean validateProfilePicture(InputStream inputStream) throws IOException {
        BufferedImage img = ImageIO.read(inputStream);
        if(img==null) throw new IOException("File is not an image");
        return img.getWidth()<=150 && img.getHeight()<=150;
    }
}
