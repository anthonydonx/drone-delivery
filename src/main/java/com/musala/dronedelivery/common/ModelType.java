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
