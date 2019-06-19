package com.mgmtp.internship.experiences.services.impl;

import com.mgmtp.internship.experiences.dto.ActivityDTO;
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
    public void shouldReturnAllActivities(){
        List<ActivityDTO> expectedListActivityDTO = Arrays.asList(new ActivityDTO(1, "name"));
        Mockito.when(activityRepository.findAll()).thenReturn(expectedListActivityDTO);

        List<ActivityDTO> actualListActivityDTO = activityService.findAll();

        Assert.assertEquals(expectedListActivityDTO, actualListActivityDTO);
    }
}
