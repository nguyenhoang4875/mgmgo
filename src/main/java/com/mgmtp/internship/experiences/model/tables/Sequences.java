/*
 * This file is generated by jOOQ.
 */
package com.mgmtp.internship.experiences.model.tables;


import javax.annotation.Generated;

import org.jooq.Sequence;
import org.jooq.impl.SequenceImpl;


/**
 * Convenience access to all sequences in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>public.activity_id_seq</code>
     */
    public static final Sequence<Long> ACTIVITY_ID_SEQ = new SequenceImpl<Long>("activity_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.image_id_seq</code>
     */
    public static final Sequence<Long> IMAGE_ID_SEQ = new SequenceImpl<Long>("image_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.rating_id_seq</code>
     */
    public static final Sequence<Long> RATING_ID_SEQ = new SequenceImpl<Long>("rating_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));

    /**
     * The sequence <code>public.user_id_seq</code>
     */
    public static final Sequence<Long> USER_ID_SEQ = new SequenceImpl<Long>("user_id_seq", Public.PUBLIC, org.jooq.impl.SQLDataType.BIGINT.nullable(false));
}
