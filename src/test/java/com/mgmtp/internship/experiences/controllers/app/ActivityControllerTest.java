package com.mgmtp.internship.experiences.controllers.app;

import com.mgmtp.internship.experiences.dto.ActivityDetailDTO;
import com.mgmtp.internship.experiences.services.ActivityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit Test for Activity Controller
 *
 * @author vhduong
 */
@RunWith(MockitoJUnitRunner.class)
public class ActivityControllerTest {
    private static final long ACTIVITY_ID = 1;
    private static final ActivityDetailDTO EXPECTED_ACTIVITY_DETAIL_DTO = new ActivityDetailDTO(ACTIVITY_ID, "name", "des", 5);

    private MockMvc mockMvc;

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private ActivityController activityController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(activityController).build();
    }

    @Test
    public void shouldGetActivityShowOnActivityPage(){
        Mockito.when(activityService.findById(ACTIVITY_ID)).thenReturn(EXPECTED_ACTIVITY_DETAIL_DTO);

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/activity/1"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.model().attribute("activityInfo", EXPECTED_ACTIVITY_DETAIL_DTO))
                    .andExpect(MockMvcResultMatchers.view().name("activity/detail"));
        } catch (Exception e) {
        }
    }

    @Test
    public void shouldShowEditPage(){
        Mockito.when(activityService.findById(ACTIVITY_ID)).thenReturn(EXPECTED_ACTIVITY_DETAIL_DTO);

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/activity/update/1"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.model().attribute("activityInfo", EXPECTED_ACTIVITY_DETAIL_DTO))
                    .andExpect(MockMvcResultMatchers.view().name("activity/update"));
        } catch (Exception e) {
        }
    }

    @Test
    public void shouldReturnShowActivityIfEditSuccess(){
        Mockito.when(activityService.updateActivity(1, EXPECTED_ACTIVITY_DETAIL_DTO.getName(), EXPECTED_ACTIVITY_DETAIL_DTO.getDescription())).thenReturn(1);
        try {
            mockMvc.perform(post("/activity/update/1").param("id", "1").param("name", "name").param("description", "des"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.redirectedUrl("/activity/1"));
        } catch (Exception e) {
        }
    }

}
