/*
 * This file is generated by jOOQ.
 */
package com.mgmtp.internship.experiences.model.tables.tables.records;


import com.mgmtp.internship.experiences.model.tables.tables.ActivityImage;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * activity image table
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ActivityImageRecord extends TableRecordImpl<ActivityImageRecord> implements Record2<Long, Long> {

    private static final long serialVersionUID = 430766082;

    /**
     * Setter for <code>public.activity_image.activity_id</code>.
     */
    public void setActivityId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.activity_image.activity_id</code>.
     */
    public Long getActivityId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.activity_image.image_id</code>.
     */
    public void setImageId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.activity_image.image_id</code>.
     */
    public Long getImageId() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return ActivityImage.ACTIVITY_IMAGE.ACTIVITY_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return ActivityImage.ACTIVITY_IMAGE.IMAGE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getActivityId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component2() {
        return getImageId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getActivityId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value2() {
        return getImageId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActivityImageRecord value1(Long value) {
        setActivityId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActivityImageRecord value2(Long value) {
        setImageId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActivityImageRecord values(Long value1, Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ActivityImageRecord
     */
    public ActivityImageRecord() {
        super(ActivityImage.ACTIVITY_IMAGE);
    }

    /**
     * Create a detached, initialised ActivityImageRecord
     */
    public ActivityImageRecord(Long activityId, Long imageId) {
        super(ActivityImage.ACTIVITY_IMAGE);

        set(0, activityId);
        set(1, imageId);
    }
}
