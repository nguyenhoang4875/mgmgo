package com.mgmtp.internship.experiences.services.impl;

import com.mgmtp.internship.experiences.dto.ActivityDTO;
import com.mgmtp.internship.experiences.dto.ActivityDetailDTO;
import com.mgmtp.internship.experiences.repositories.ActivityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test for activity service.
 *
 * @author thuynh
 */
@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceImplTest {

    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityServiceImpl activityService;

    @Test
    public void shouldReturnAllActivities() {
        List<ActivityDTO> expectedListActivityDTO = Arrays.asList(new ActivityDTO(1, "name"));
        Mockito.when(activityRepository.findAll()).thenReturn(expectedListActivityDTO);

        List<ActivityDTO> actualListActivityDTO = activityService.findAll();

        Assert.assertEquals(expectedListActivityDTO, actualListActivityDTO);
    }

    @Test
    public void shouldReturnActivityById() {
        // pass activityId that exist in the activity list
        long activityId = 1;
        ActivityDetailDTO expectedActivityDetailDTO = new ActivityDetailDTO(activityId, "name", "description", "cate");
        Mockito.when(activityRepository.findById(activityId)).thenReturn(expectedActivityDetailDTO);

        ActivityDetailDTO actualActivityDetailDTO = activityService.findById(activityId);

        Assert.assertEquals(expectedActivityDetailDTO, actualActivityDetailDTO);
    }

    @Test
    public void shouldReturnNullIfActivityNotFound() {
        long invalidId = 1;
        Mockito.when(activityRepository.findById(invalidId)).thenReturn(null);

        ActivityDetailDTO actualActivityDetailDTO = activityService.findById(invalidId);

        Assert.assertEquals(null, actualActivityDetailDTO);
    }
}
