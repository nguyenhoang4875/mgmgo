package com.mgmtp.internship.experiences.controllers.api;

import com.mgmtp.internship.experiences.dto.ImageDTO;
import com.mgmtp.internship.experiences.exceptions.ApiException;
import com.mgmtp.internship.experiences.services.ImageService;
import org.jooq.tools.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;


/**
 * Quote api controller.
 *
 * @author htnguyen
 */
@RestController
@RequestMapping("/api/image")
public class ImageRestController extends BaseRestController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getImageById(@PathVariable("id") long id) {
        ImageDTO imageDTO = imageService.findImageById(id);

        if (imageDTO == null) {
            throw new ApiException(NOT_FOUND, "Image not found.");
        }

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(3600 * 24 * 30, TimeUnit.SECONDS))
                .body(imageDTO.getImage());
    }

    @PostMapping("/activity/{activity_id}")
    @ResponseBody
    public Object addImage(@PathVariable("activity_id") long activityId, @RequestParam("image_file") MultipartFile photo) throws IOException, ApiException {
        byte[] imageData = photo.getBytes();
        Long imageId = imageService.updateActivityImage(activityId, imageData);
        if (imageId == null) {
            throw new ApiException(BAD_REQUEST, "Server error.");
        }
        JSONObject jsonObject = new JSONObject();
        String url = "api/image/" + imageId;
        jsonObject.put("imageFromRestAPI", url);
        return jsonObject;
    }


}
