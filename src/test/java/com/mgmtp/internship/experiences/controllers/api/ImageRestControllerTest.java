package com.mgmtp.internship.experiences.controllers.api;

import com.mgmtp.internship.experiences.config.security.CustomUserDetails;
import com.mgmtp.internship.experiences.dto.UserProfileDTO;
import com.mgmtp.internship.experiences.services.ImageService;
import com.mgmtp.internship.experiences.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class ImageRestControllerTest {

    private static final MockMultipartFile FILE = new MockMultipartFile("photo", "test.jpg", "multipart/form-data", new byte[]{1});
    private static final Long OLD_IMAGE_ID = 2L;
    private static final UserProfileDTO USER_PROFILE_DTO = Mockito.spy(new UserProfileDTO(OLD_IMAGE_ID, "display"));
    private static final CustomUserDetails USER_DETAILS = Mockito.spy(new CustomUserDetails(1L, USER_PROFILE_DTO, "username", "pass", Collections.emptyList()));

    private MockMvc mockMvc;

    @Mock
    private ImageService imageService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ImageRestController imageRestController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(imageRestController).build();
    }

    @Test
    public void addUserImageShouldThrowExceptionIfSizeTooLarge() {
        try {
            Mockito.when(imageService.validateProfilePicture(Mockito.any())).thenReturn(false);

            mockMvc.perform(MockMvcRequestBuilders.multipart("/api/image/user").file(FILE))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"FAILED\",\"message\":\"The image is too large.\"}"));
        } catch (Exception e) {
        }
    }

    @Test
    public void addUserImageShouldThrowExceptionIfNotAnImage() {
        try {
            Mockito.when(imageService.validateProfilePicture(Mockito.any())).thenThrow(new IOException());

            mockMvc.perform(MockMvcRequestBuilders.multipart("/api/image/user").file(FILE))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"FAILED\",\"message\":\"Can not process the image.\"}"));
        } catch (Exception e) {
        }
    }

    @Test
    public void addUserImageShouldNotChangeIdIfUpdateFail() {
        try {
            Mockito.when(imageService.validateProfilePicture(Mockito.any())).thenReturn(true);
            Mockito.when(userService.getCurrentUser()).thenReturn(USER_DETAILS);
            Mockito.when(imageService.updateUserImage(USER_DETAILS.getId(), OLD_IMAGE_ID, FILE.getBytes())).thenThrow(new RuntimeException());

            mockMvc.perform(MockMvcRequestBuilders.multipart("/api/image/user").file(FILE))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"FAILED\",\"message\":\"Server error.\"}"));

            Mockito.verify(USER_PROFILE_DTO, Mockito.never()).setImageId(Mockito.anyLong());
        } catch (Exception e) {
        }
    }

    @Test
    public void addUserImageShouldChangeIdIfUpdateSucceed() {

        Long imageId = 1L;
        try {

            Mockito.when(imageService.validateProfilePicture(Mockito.any())).thenReturn(true);
            Mockito.when(userService.getCurrentUser()).thenReturn(USER_DETAILS);
            Mockito.when(imageService.updateUserImage(USER_DETAILS.getId(), OLD_IMAGE_ID, FILE.getBytes())).thenReturn(imageId);

            mockMvc.perform(MockMvcRequestBuilders.multipart("/api/image/user").file(FILE))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string("{\"id\":1}"));

            Mockito.verify(USER_PROFILE_DTO).setImageId(imageId);
        } catch (Exception e) {
        }
    }


}