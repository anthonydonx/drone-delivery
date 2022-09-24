/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.resource;

import com.musala.dronedelivery.common.ModelType;
import com.musala.dronedelivery.common.StateType;
import com.musala.dronedelivery.common.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneResource extends BaseResource{
    @NotNull(message = "Serial number required")
    @Max(value = 100,message = "maximum allowed 100 characters for serial number")
    private String serialNumber;
    @ValueOfEnum(enumClass = ModelType.class ,message = "Invalid model provided")
    private ModelType model;
    private Integer weightLimit;
    private Integer batteryCapacity;
}
