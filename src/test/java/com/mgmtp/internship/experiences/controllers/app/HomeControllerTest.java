package com.mgmtp.internship.experiences.controllers.app;

import com.mgmtp.internship.experiences.dto.ActivityDTO;
import com.mgmtp.internship.experiences.services.impl.ActivityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test for home controller.
 *
 * @author thuynh
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    @Mock
    private ActivityServiceImpl activityService;

    @InjectMocks
    private HomeController homeController;

    @Test
    public void shouldReturnModelHaveListActivity() {
        final Model model = new ExtendedModelMap();
        List<ActivityDTO> expectedActivityDTO = Arrays.asList(new ActivityDTO(1, "abc", 1L));
        Mockito.when(activityService.findAll()).thenReturn(expectedActivityDTO);

        String actualView = homeController.getHome(model);

        assertThat(actualView, is("home/index"));
        assertThat(model.asMap().get("activities"), is(expectedActivityDTO));
    }

}
