package com.mgmtp.internship.experiences.controllers.api;

import com.mgmtp.internship.experiences.config.security.CustomUserDetails;
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

    private static final String USERNAME = "username";
    private static final String RATING_PARAM = "rating";
    private static final String URL = "/rating/activity/1";
    private static final int ACTIVITY_ID = 1;

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
        CustomUserDetails customUserDetails = new CustomUserDetails(1L, USERNAME, "pass", Collections.emptyList());
        Mockito.when(userService.getCurrentUser()).thenReturn(customUserDetails);
        Mockito.when(ratingService.getRateByUserId(ACTIVITY_ID, customUserDetails.getId())).thenReturn(expectedRating);

        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"rating\":" + expectedRating + "}"));
    }

    @Test
    public void shouldReturnErrorOnGetUserRatingIfNotLogged() throws Exception {
        Mockito.when(userService.getCurrentUser()).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"FAILED\",\"message\":\"Please login to perform this operation.\"}"));
    }

    @Test
    public void shouldReturnAverageRatingIfEditSuccess() throws Exception {
        double expectedRating = 5.0;
        int updateSuccess = 1;
        int rating = 5;
        CustomUserDetails customUserDetails = new CustomUserDetails(1L, USERNAME, "pass", Collections.emptyList());
        Mockito.when(userService.getCurrentUser()).thenReturn(customUserDetails);
        Mockito.when(ratingService.editRateByUserId(ACTIVITY_ID, customUserDetails.getId(), rating)).thenReturn(updateSuccess);
        Mockito.when(ratingService.getRate(ACTIVITY_ID)).thenReturn(expectedRating);

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .param(RATING_PARAM, String.valueOf(rating)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"rating\":" + expectedRating + "}"));
    }

    @Test
    public void shouldReturnErrorOnUpdateRatingIfNotLogged() throws Exception {
        int rating = 4;
        Mockito.when(userService.getCurrentUser()).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .param(RATING_PARAM, String.valueOf(rating)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"FAILED\",\"message\":\"Please login to perform this operation.\"}"));
    }

    @Test
    public void shouldReturnServerErrorOnUpdateIfEditFail() throws Exception {
        int updateFail = 0;
        int rating = 5;
        CustomUserDetails customUserDetails = new CustomUserDetails(1L, USERNAME, "pass", Collections.emptyList());
        Mockito.when(userService.getCurrentUser()).thenReturn(customUserDetails);
        Mockito.when(ratingService.editRateByUserId(ACTIVITY_ID, customUserDetails.getId(), rating)).thenReturn(updateFail).thenThrow(ApiException.class);

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .param(RATING_PARAM, String.valueOf(rating)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":\"FAILED\",\"message\":\"Something went wrong! Please try again.\"}"));
    }

}
