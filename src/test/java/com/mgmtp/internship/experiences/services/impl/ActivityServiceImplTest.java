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

import java.util.Collections;
import java.util.List;

/**
 * Unit test for activity service.
 *
 * @author thuynh
 */
@RunWith(MockitoJUnitRunner.class)
public class ActivityServiceImplTest {
    private static final long ACTIVITY_ID = 1;
    private static final ActivityDetailDTO EXPECTED_ACTIVITY_DETAIL_DTO = new ActivityDetailDTO(ACTIVITY_ID, "new", "Description", 5, 1L);

    private static final String KEY_SEARCH = "abc";
    private static final List<ActivityDTO> EXPECTED_ACTIVITY_DTO = Collections.singletonList(new ActivityDTO(1L, "name", 1L));
    @Mock
    private ActivityRepository activityRepository;

    @InjectMocks
    private ActivityServiceImpl activityService;

    @Test
    public void shouldReturnAllActivities() {
        List<ActivityDTO> expectedListActivityDTO = Collections.singletonList(new ActivityDTO(ACTIVITY_ID, "name", 1L));
        Mockito.when(activityRepository.findAll()).thenReturn(expectedListActivityDTO);

        List<ActivityDTO> actualListActivityDTO = activityService.findAll();

        Assert.assertEquals(expectedListActivityDTO, actualListActivityDTO);
    }

    @Test
    public void shouldReturnActivityById() {
        Mockito.when(activityRepository.findById(ACTIVITY_ID)).thenReturn(EXPECTED_ACTIVITY_DETAIL_DTO);

        ActivityDetailDTO actualActivityDetailDTO = activityService.findById(ACTIVITY_ID);

        Assert.assertEquals(EXPECTED_ACTIVITY_DETAIL_DTO, actualActivityDetailDTO);
    }

    @Test
    public void shouldReturnNullIfActivityNotFound() {
        Mockito.when(activityRepository.findById(ACTIVITY_ID)).thenReturn(null);

        ActivityDetailDTO actualActivityDetailDTO = activityService.findById(ACTIVITY_ID);

        Assert.assertEquals(null, actualActivityDetailDTO);
    }

    @Test
    public void shouldReturn1IfUpdateSuccess() {
        final int UPDATE_SUCCESS = 1;
        Mockito.when(activityRepository.update(EXPECTED_ACTIVITY_DETAIL_DTO)).thenReturn(UPDATE_SUCCESS);

        Assert.assertEquals(UPDATE_SUCCESS, activityService.update(EXPECTED_ACTIVITY_DETAIL_DTO));
    }

    @Test
    public void shouldReturn0IfUpdateFailed() {
        final int UPDATE_FAIL = 0;
        EXPECTED_ACTIVITY_DETAIL_DTO.setUpdatedByUserId(1);
        Mockito.when(activityRepository.update(EXPECTED_ACTIVITY_DETAIL_DTO)).thenReturn(UPDATE_FAIL);

        Assert.assertEquals(UPDATE_FAIL, activityService.update(EXPECTED_ACTIVITY_DETAIL_DTO));
    }

    @Test
    public void shouldReturn1IfInsertSuccess(){
        final int INSERT_SUCCESS = 1;
        Mockito.when(activityRepository.create(EXPECTED_ACTIVITY_DETAIL_DTO)).thenReturn(INSERT_SUCCESS);

        Assert.assertEquals(INSERT_SUCCESS, activityService.create(EXPECTED_ACTIVITY_DETAIL_DTO));
    }

    @Test
    public void shouldReturn0IfInsertFailed(){
        final int INSERT_SUCCESS = 0;
        Mockito.when(activityRepository.create(EXPECTED_ACTIVITY_DETAIL_DTO)).thenReturn(INSERT_SUCCESS);

        Assert.assertEquals(INSERT_SUCCESS, activityService.create(EXPECTED_ACTIVITY_DETAIL_DTO));
    }

    @Test
    public void shouldReturnTrueIfNameExist(){
        Mockito.when(activityRepository.checkExistName(EXPECTED_ACTIVITY_DETAIL_DTO.getName())).thenReturn(true);

        Assert.assertEquals(true, activityService.checkExistName(EXPECTED_ACTIVITY_DETAIL_DTO.getName()));
    }

    @Test
    public void shouldReturnFalseIfNameExist(){
        Mockito.when(activityRepository.checkExistName(EXPECTED_ACTIVITY_DETAIL_DTO.getName())).thenReturn(false);

        Assert.assertEquals(false, activityService.checkExistName(EXPECTED_ACTIVITY_DETAIL_DTO.getName()));
    }

    @Test
    public void shouldReturnTrueIfNameExistWhenUpdate(){
        Mockito.when(activityRepository.checkExistNameForUpdate(EXPECTED_ACTIVITY_DETAIL_DTO.getId(), EXPECTED_ACTIVITY_DETAIL_DTO.getName())).thenReturn(true);

        Assert.assertEquals(true, activityService.checkExistNameForUpdate(EXPECTED_ACTIVITY_DETAIL_DTO.getId(), EXPECTED_ACTIVITY_DETAIL_DTO.getName()));
    }

    @Test
    public void shouldReturnFalseIfNameExistWhenUpdate(){
        Mockito.when(activityRepository.checkExistNameForUpdate(EXPECTED_ACTIVITY_DETAIL_DTO.getId(), EXPECTED_ACTIVITY_DETAIL_DTO.getName())).thenReturn(false);

        Assert.assertEquals(false, activityService.checkExistNameForUpdate(EXPECTED_ACTIVITY_DETAIL_DTO.getId(), EXPECTED_ACTIVITY_DETAIL_DTO.getName()));
    }

    @Test
    public void shouldReturnListActivitiesWhenKeySearchCorrect(){
        Mockito.when(activityRepository.search(KEY_SEARCH)).thenReturn(EXPECTED_ACTIVITY_DTO);

        List<ActivityDTO> actualListActivityDTO = activityService.search(KEY_SEARCH);

        Assert.assertEquals(EXPECTED_ACTIVITY_DTO, actualListActivityDTO);
    }

    @Test
    public void shouldReurnNullWhenKeySearchIncorrect(){
        Mockito.when(activityRepository.search(KEY_SEARCH)).thenReturn(null);

        List<ActivityDTO> actualListActivityDTO = activityService.search(KEY_SEARCH);

        Assert.assertEquals(null, actualListActivityDTO);
    }
}
