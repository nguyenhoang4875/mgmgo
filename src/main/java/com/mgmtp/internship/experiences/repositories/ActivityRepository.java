package com.mgmtp.internship.experiences.repositories;

import com.mgmtp.internship.experiences.dto.ActivityDTO;
import com.mgmtp.internship.experiences.dto.ActivityDetailDTO;
import org.jooq.DSLContext;
import org.jooq.Record4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.mgmtp.internship.experiences.model.tables.tables.Activity.ACTIVITY;
import static com.mgmtp.internship.experiences.model.tables.tables.Rating.RATING;
import static org.jooq.impl.DSL.avg;
import static org.jooq.impl.DSL.round;

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
                .map(ActivityDTO::new)
                .collect(Collectors.toList());
    }

    public ActivityDetailDTO findById(long activityId) {
        Record4<Long, String, String, BigDecimal> activity = dslContext.select(ACTIVITY.ID,
                ACTIVITY.NAME, ACTIVITY.DESCRIPTION, round(avg(RATING.VALUE), 1).as("rating"))
                .from(ACTIVITY)
                .leftJoin(RATING)
                .on(ACTIVITY.ID.eq(RATING.ACTIVITY_ID))
                .where(ACTIVITY.ID.eq(activityId))
                .groupBy(ACTIVITY.ID).fetchOne();

        return activity == null ? null : activity.into(ActivityDetailDTO.class);
    }

    public int updateActivity(long activityId, String newName, String newDescription) {
        return dslContext.update(ACTIVITY)
                .set(ACTIVITY.NAME, newName)
                .set(ACTIVITY.DESCRIPTION, newDescription)
                .where(ACTIVITY.ID.eq(activityId)).execute();
    }

}
