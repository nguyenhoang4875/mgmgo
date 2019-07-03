package com.mgmtp.internship.experiences.repositories;

import com.mgmtp.internship.experiences.dto.ImageDTO;
import com.mgmtp.internship.experiences.model.tables.tables.records.ImageRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.mgmtp.internship.experiences.model.tables.tables.Image.IMAGE;


/**
 * Activity Service interface.
 *
 * @author htnguyen
 */

@Component
public class ImageRepository {

    @Autowired
    DSLContext dslContext;

    public ImageDTO findImageById(long imageId) {
        ImageRecord image = dslContext
                .selectFrom(IMAGE)
                .where(IMAGE.ID.eq(imageId))
                .fetchOne();

        if (image == null)
            return null;

        return image.into(ImageDTO.class);
    }

    public Long insert(byte[] imageData) {
        return dslContext.insertInto(IMAGE,IMAGE.IMAGE_DATA)
                .values(imageData)
                .returning(IMAGE.ID)
                .fetchOne().getId();
    }

    public int deleteImage(Long oldImageId) {
        return dslContext.deleteFrom(IMAGE)
                .where(IMAGE.ID.eq(oldImageId))
                .execute();
    }
}
