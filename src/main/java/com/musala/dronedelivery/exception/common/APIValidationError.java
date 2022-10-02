/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.exception.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  @author anthonydonx
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class APIValidationError extends APISubError{
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    APIValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
