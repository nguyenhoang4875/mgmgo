package com.mgmtp.internship.experiences.controllers.app;

import com.mgmtp.internship.experiences.config.security.CustomUserDetails;
import com.mgmtp.internship.experiences.dto.ActivityDetailDTO;
import com.mgmtp.internship.experiences.services.ActivityService;
import com.mgmtp.internship.experiences.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit Test for Activity Controller
 *
 * @author vhduong
 */
@RunWith(MockitoJUnitRunner.class)
public class ActivityControllerTest {

    private static final String ACTIVITY_INFO_ATTRIBUTE = "activityInfo";
    private static final String USERNAME = "username";
    private static final long ACTIVITY_ID = 1;
    private static final ActivityDetailDTO EXPECTED_ACTIVITY_DETAIL_DTO = new ActivityDetailDTO(ACTIVITY_ID, "name", "des", 5);
    private static final CustomUserDetails EXPECTED_CUSTOM_USER_DETAIL = new CustomUserDetails(1L, USERNAME, "pass", Collections.emptyList());


    private MockMvc mockMvc;

    @Mock
    private ActivityService activityService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ActivityController activityController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(activityController).build();
    }

    @Test
    public void shouldGetActivityShowOnActivityPage() {
        Mockito.when(activityService.findById(ACTIVITY_ID)).thenReturn(EXPECTED_ACTIVITY_DETAIL_DTO);

        try {
            mockMvc.perform(get("/activity/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute(ACTIVITY_INFO_ATTRIBUTE, EXPECTED_ACTIVITY_DETAIL_DTO))
                    .andExpect(view().name("activity/detail"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldShowErrorPageIfWrongActivityId() {
        Mockito.when(activityService.findById(ACTIVITY_ID)).thenReturn(null);

        try {
            mockMvc.perform(get("/activity/1"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("error"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldShowEditPage() {
        Mockito.when(activityService.findById(ACTIVITY_ID)).thenReturn(EXPECTED_ACTIVITY_DETAIL_DTO);

        try {
            mockMvc.perform(get("/activity/update/1"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute(ACTIVITY_INFO_ATTRIBUTE, EXPECTED_ACTIVITY_DETAIL_DTO))
                    .andExpect(view().name("activity/update"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnShowActivityIfUpdateSuccess() {
        int updateSuccess = 1;
        Mockito.when(userService.getCurrentUser()).thenReturn(EXPECTED_CUSTOM_USER_DETAIL);
        EXPECTED_ACTIVITY_DETAIL_DTO.setUpdatedByUserId(EXPECTED_CUSTOM_USER_DETAIL.getId());
        Mockito.when(activityService.update(EXPECTED_ACTIVITY_DETAIL_DTO)).thenReturn(updateSuccess);

        try {
            mockMvc.perform(post("/activity/update")
                    .param("id", ACTIVITY_ID + "")
                    .param("name", EXPECTED_ACTIVITY_DETAIL_DTO.getName())
                    .param("description", EXPECTED_ACTIVITY_DETAIL_DTO.getDescription()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/activity/" + EXPECTED_ACTIVITY_DETAIL_DTO.getId()))
                    .andExpect(redirectedUrl("/activity/" + EXPECTED_ACTIVITY_DETAIL_DTO.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldShowMessageErrorIfUpdateExistName() {
        Mockito.when(userService.getCurrentUser()).thenReturn(EXPECTED_CUSTOM_USER_DETAIL);
        EXPECTED_ACTIVITY_DETAIL_DTO.setCreatedByUserId(EXPECTED_CUSTOM_USER_DETAIL.getId());
        Mockito.when(activityService.checkExistName(EXPECTED_ACTIVITY_DETAIL_DTO.getName())).thenReturn(true);

        try {
            mockMvc.perform(post("/activity/update")
                    .param("id", ACTIVITY_ID + "")
                    .param("name", EXPECTED_ACTIVITY_DETAIL_DTO.getName())
                    .param("description", EXPECTED_ACTIVITY_DETAIL_DTO.getDescription()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(flash().attribute("error", "This name already exists"))
                    .andExpect(flash().attribute(ACTIVITY_INFO_ATTRIBUTE, EXPECTED_ACTIVITY_DETAIL_DTO))
                    .andExpect(view().name("redirect:/activity/update/" + EXPECTED_ACTIVITY_DETAIL_DTO.getId()))
                    .andExpect(redirectedUrl("/activity/update/" + EXPECTED_ACTIVITY_DETAIL_DTO.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldShowMessageIfUpdateFail(){
        Mockito.when(userService.getCurrentUser()).thenReturn(EXPECTED_CUSTOM_USER_DETAIL);
        Mockito.when(activityService.update(EXPECTED_ACTIVITY_DETAIL_DTO)).thenThrow(DataIntegrityViolationException.class);
        try {
            mockMvc.perform(post("/activity/update")
                    .param("id", ACTIVITY_ID + "")
                    .param("name", EXPECTED_ACTIVITY_DETAIL_DTO.getName())
                    .param("description", EXPECTED_ACTIVITY_DETAIL_DTO.getDescription()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(flash().attribute("error", "Can't update Activity. Try again!"))
                    .andExpect(flash().attribute(ACTIVITY_INFO_ATTRIBUTE, EXPECTED_ACTIVITY_DETAIL_DTO))
                    .andExpect(view().name("redirect:/activity/update/" + EXPECTED_ACTIVITY_DETAIL_DTO.getId()))
                    .andExpect(redirectedUrl("/activity/update/" + EXPECTED_ACTIVITY_DETAIL_DTO.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldShowCreatePage() {
        try {
            mockMvc.perform(get("/activity/create"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute(ACTIVITY_INFO_ATTRIBUTE, new ActivityDetailDTO()))
                    .andExpect(view().name("activity/create"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldReturnHomePageIfCreateSuccess() {
        int insertSuccess = 1;
        Mockito.when(userService.getCurrentUser()).thenReturn(EXPECTED_CUSTOM_USER_DETAIL);
        EXPECTED_ACTIVITY_DETAIL_DTO.setCreatedByUserId(EXPECTED_CUSTOM_USER_DETAIL.getId());

        Mockito.when(activityService.create(EXPECTED_ACTIVITY_DETAIL_DTO)).thenReturn(insertSuccess);

        try {
            mockMvc.perform(post("/activity/create")
                    .param("id", ACTIVITY_ID + "")
                    .param("name", EXPECTED_ACTIVITY_DETAIL_DTO.getName())
                    .param("description", EXPECTED_ACTIVITY_DETAIL_DTO.getDescription()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/"))
                    .andExpect(redirectedUrl("/"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void shouldShowMessageErrorIfCreateExistName() {
        Mockito.when(userService.getCurrentUser()).thenReturn(EXPECTED_CUSTOM_USER_DETAIL);
        EXPECTED_ACTIVITY_DETAIL_DTO.setCreatedByUserId(EXPECTED_CUSTOM_USER_DETAIL.getId());
        Mockito.when(activityService.checkExistName(EXPECTED_ACTIVITY_DETAIL_DTO.getName())).thenReturn(true);

        try {
            mockMvc.perform(post("/activity/create")
                    .param("id", ACTIVITY_ID + "")
                    .param("name", EXPECTED_ACTIVITY_DETAIL_DTO.getName())
                    .param("description", EXPECTED_ACTIVITY_DETAIL_DTO.getDescription()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(flash().attribute("error", "This name already exists"))
                    .andExpect(flash().attribute(ACTIVITY_INFO_ATTRIBUTE, EXPECTED_ACTIVITY_DETAIL_DTO))
                    .andExpect(view().name("redirect:/activity/create"))
                    .andExpect(redirectedUrl("/activity/create"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldShowMessageIfCreateFail(){
        Mockito.when(userService.getCurrentUser()).thenReturn(EXPECTED_CUSTOM_USER_DETAIL);
        Mockito.when(activityService.create(EXPECTED_ACTIVITY_DETAIL_DTO)).thenThrow(DataIntegrityViolationException.class);
        try {
            mockMvc.perform(post("/activity/create")
                    .param("id", ACTIVITY_ID + "")
                    .param("name", EXPECTED_ACTIVITY_DETAIL_DTO.getName())
                    .param("description", EXPECTED_ACTIVITY_DETAIL_DTO.getDescription()))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is3xxRedirection())
                    .andExpect(flash().attribute("error", "Can't create Activity. Try again!"))
                    .andExpect(flash().attribute(ACTIVITY_INFO_ATTRIBUTE, EXPECTED_ACTIVITY_DETAIL_DTO))
                    .andExpect(view().name("redirect:/activity/create"))
                    .andExpect(redirectedUrl("/activity/create"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
