/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.common;

/**
 * @author anthonydonx
 * manage drone status
 */
public enum MedicationStatus {
    PENDING("pending"), LOADED("loaded"), DELIVERED("delivered");

    private String statusCode;

    MedicationStatus(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getStatusCode(){
        return statusCode;
    }
}
