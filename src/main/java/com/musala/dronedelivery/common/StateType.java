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
