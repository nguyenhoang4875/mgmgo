package com.mgmtp.internship.experiences.services.impl;

import com.mgmtp.internship.experiences.dto.ImageDTO;
import com.mgmtp.internship.experiences.repositories.ImageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceImplTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    public void shouldReturnImageById() {
        long imageId = 1;
        ImageDTO expectedImageDTO = new ImageDTO(imageId, new byte[]{(byte) 0xe0, 0x4f});
        Mockito.when(imageRepository.findImageById(imageId)).thenReturn(expectedImageDTO);

        ImageDTO actualImageDTO = imageService.findImageById(imageId);

        Assert.assertEquals(actualImageDTO, expectedImageDTO);
    }

    @Test
    public void shouldReturnTrueIfAddImageSuccessfully() {
        long activityId = 1;
        Long expectedImageId = 1L;
        Mockito.when(imageRepository.updateActivityImage(activityId, new byte[]{(byte) 0xe0, 0x4f})).thenReturn(expectedImageId);

        Long actualImageId = imageService.updateActivityImage(activityId, new byte[]{(byte) 0xe0, 0x4f});

        Assert.assertEquals(expectedImageId, actualImageId);
    }

    @Test
    public void shouldReturnFalseIfAddImageFaile() {
        long activityId = 1;
        Mockito.when(imageRepository.updateActivityImage(activityId, new byte[]{(byte) 0xe0, 0x4f})).thenReturn(null);

        Long actualResult = imageService.updateActivityImage(activityId, new byte[]{(byte) 0xe0, 0x4f});

        Assert.assertEquals(actualResult, null);
    }

}
