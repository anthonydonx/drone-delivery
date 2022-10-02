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
public enum StateType {
    IDLE("idle"), LOADING("loading"), LOADED("loaded"), DELIVERING("delivering"), DELIVERED("delivered"), RETURNING("returning");

    private String stateCode;

    StateType(String stateCode) {
        this.stateCode = stateCode;
    }
    public String getStateCode(){
        return stateCode;
    }
}
