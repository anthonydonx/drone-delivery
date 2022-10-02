/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.exception.common;

import org.springframework.validation.BindingResult;

public class DataValidationError extends RuntimeException{
    private BindingResult result;

    public DataValidationError(BindingResult result) {
        super();
        this.setResult(result);
    }

    public BindingResult getResult() {
        return result;
    }

    public void setResult(BindingResult result) {
        this.result = result;
    }
}
