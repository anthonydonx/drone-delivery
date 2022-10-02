/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.util;

import org.springframework.stereotype.Service;

/**
 *  * medication images uploading functionality, better to use image storage service like S3 bucket etc
 *  * for here just sent temp url without actual implementation
 */
@Service
public class ImageStorageServiceImpl implements ImageStorageService{
    /**
     * @return
     */
    @Override
    public String tempUrl() {
        return ImageStorageService.super.tempUrl();
    }

    /**
     * @return
     */
    @Override
    public String saveImage() {
        return null;
    }
}
