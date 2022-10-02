/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.util;

import org.springframework.stereotype.Service;

/**
functionality of Image storage
 * medication images uploading functionality, better to use image storage service like S3 bucket etc
 * for here just sent temp url without actual implementation
 */

public interface ImageStorageService {

    String HTTPS_WWW_TEMPURL_COM = "https://www.tempurl.com";

    default String tempUrl(){
        return HTTPS_WWW_TEMPURL_COM;
    }

    String saveImage();
}
