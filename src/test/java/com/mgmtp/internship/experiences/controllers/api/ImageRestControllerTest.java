package com.mgmtp.internship.experiences.controllers.api;

import com.mgmtp.internship.experiences.dto.ImageDTO;
import com.mgmtp.internship.experiences.exceptions.ApiException;
import com.mgmtp.internship.experiences.services.impl.ImageServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class ImageRestControllerTest {

    @Mock
    private ImageServiceImpl imageService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ImageRestController imageRestController;

    @Test
    public void shouldReturnImageById() {
        long imageId = 1;
        ImageDTO expectedImageDTO = new ImageDTO(imageId, new byte[]{(byte) 0xd4, 0x4f});
        Mockito.when(imageService.findImageById(imageId)).thenReturn(expectedImageDTO);

        byte[] actualImage = imageRestController.getImageById(imageId).getBody();

        Assert.assertEquals(actualImage, expectedImageDTO.getImage());
    }

    @Test(expected = ApiException.class)
    public void shouldThrowNotFoundExceptionIfReturnNullImage() {
        long imageId = 1;
        Mockito.when(imageService.findImageById(imageId)).thenReturn(null);
        imageRestController.getImageById(imageId).getBody();
    }

    @Test(expected = ApiException.class)
    public void shouldThrowApiExceptionIfAddImageFailed() throws IOException {
        long activityId = 1;
        MockMultipartFile photo = new MockMultipartFile("data", "data.jpg", "image/jpg", new byte[]{0x4f, 0x3f});
        Mockito.when(imageService.updateActivityImage(activityId, photo.getBytes())).thenReturn(null);
        imageRestController.addImage(activityId, photo);
    }

}
