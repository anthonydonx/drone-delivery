/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.common;

/**
 * @author anthonydonx
 * model (Lightweight, Middleweight, Cruiserweight, Heavyweight)
 */
public enum ModelType {
    LIGHT_WEIGHT("Lightweight"), MIDDLE_WEIGHT("Middleweight"), CRUISER_WEIGHT("Cruiserweight"), HEAVY_WEIGHT("Heavyweight");
    private String modelCode;
    private ModelType(String modelCode){
        this.modelCode=modelCode;
    }
    public String getModelCode(){
        return modelCode;
    }
}
