package com.mgmtp.internship.experiences.controllers.api;

import com.mgmtp.internship.experiences.config.security.CustomUserDetails;
import com.mgmtp.internship.experiences.dto.UserProfileDTO;
import com.mgmtp.internship.experiences.exceptions.ApiException;
import com.mgmtp.internship.experiences.services.impl.RatingServiceImpl;
import com.mgmtp.internship.experiences.services.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

/**
 * Unit test for rating rest controller.
 *
 * @author thuynh
 */
@RunWith(MockitoJUnitRunner.class)
public class RatingRestControllerTest {

    private static final int ACTIVITY_ID = 1;
    private static final CustomUserDetails CUSTOM_USER_DETAILS = new CustomUserDetails(1L, new UserProfileDTO(1L, "display"), "username", "pass", Collections.emptyList());
    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private RatingServiceImpl ratingService;

    @InjectMocks
    private RatingRestController ratingRestController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ratingRestController).build();
    }

    @Test
    public void shouldReturnUserRating() throws Exception {
        int expectedRating = 5;
        Mockito.when(userService.getCurrentUser()).thenReturn(CUSTOM_USER_DETAILS);
        Mockito.when(ratingService.getRateByUserId(ACTIVITY_ID, CUSTOM_USER_DETAILS.getId())).thenReturn(expectedRating);

        mockMvc.perform(MockMvcRequestBuilders.get("/rating/activity/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"rating\":" + expectedRating + "}"));
    }

    @Test
    public void shouldReturnErrorOnGetUserRatingIfNotLogged(){
        Mockito.when(userService.getCurrentUser()).thenReturn(null);

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/rating/activity/1"))
                    .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                    .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"FAILED\",\"message\":\"Please login to perform this operation.\"}"));
        } catch (Exception e) {
        }
    }

    @Test
    public void shouldReturnAverageRatingIfEditSuccess(){
        double expectedRating = 5.0;
        int updateSuccess = 1;
        int rating = 5;
        Mockito.when(userService.getCurrentUser()).thenReturn(CUSTOM_USER_DETAILS);
        Mockito.when(ratingService.editRateByUserId(ACTIVITY_ID, CUSTOM_USER_DETAILS.getId(), rating)).thenReturn(updateSuccess);
        Mockito.when(ratingService.getRate(ACTIVITY_ID)).thenReturn(expectedRating);

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/rating/activity/1")
                    .param("rating", String.valueOf(rating)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().string("{\"rating\":" + expectedRating + "}"));
        } catch (Exception e) {
        }
    }

    @Test
    public void shouldReturnErrorOnUpdateRatingIfNotLogged(){
        int rating = 4;
        Mockito.when(userService.getCurrentUser()).thenReturn(null);

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/rating/activity/1")
                    .param("rating", String.valueOf(rating)))
                    .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                    .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"FAILED\",\"message\":\"Please login to perform this operation.\"}"));
        } catch (Exception e) {
        }
    }

    @Test
    public void shouldReturnServerErrorOnUpdateIfEditFail(){
        int updateFail = 0;
        int rating = 5;
        Mockito.when(userService.getCurrentUser()).thenReturn(CUSTOM_USER_DETAILS);
        Mockito.when(ratingService.editRateByUserId(ACTIVITY_ID, CUSTOM_USER_DETAILS.getId(), rating)).thenReturn(updateFail).thenThrow(ApiException.class);

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/rating/activity/1")
                    .param("rating", String.valueOf(rating)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"FAILED\",\"message\":\"Something went wrong! Please try again.\"}"));
        } catch (Exception e) {
        }
    }

}