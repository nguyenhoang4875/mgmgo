package com.mgmtp.internship.experiences.repositories;

import com.mgmtp.internship.experiences.dto.ActivityDTO;
import com.mgmtp.internship.experiences.dto.ActivityDetailDTO;
import org.jooq.DSLContext;
import org.jooq.SelectConditionStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.mgmtp.internship.experiences.model.tables.Tables.CATEGORY;
import static com.mgmtp.internship.experiences.model.tables.tables.Activity.ACTIVITY;

/**
 * Activity Repository.
 *
 * @author thuynh
 */
@Component
public class ActivityRepository {

    @Autowired
    private DSLContext dslContext;

    public List<ActivityDTO> findAll() {
        return dslContext.selectFrom(ACTIVITY)
                .orderBy(ACTIVITY.ID)
                .fetch().stream()
                .map(activityRecord -> new ActivityDTO(activityRecord))
                .collect(Collectors.toList());
    }

    public ActivityDetailDTO findById(long activityId) {
        SelectConditionStep activity = dslContext
                .select(CATEGORY.NAME.as("categoryName"), ACTIVITY.DESCRIPTION, ACTIVITY.ID, ACTIVITY.NAME)
                .from(ACTIVITY).join(CATEGORY)
                .on(ACTIVITY.CATEGORY_ID.eq(CATEGORY.ID))
                .where(ACTIVITY.ID.eq(activityId));

        if (activity == null) {
            return null;
        }
        return (ActivityDetailDTO) activity.fetchOneInto(ActivityDetailDTO.class);
    }

}
