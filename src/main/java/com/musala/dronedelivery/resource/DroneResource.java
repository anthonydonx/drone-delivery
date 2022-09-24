/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.resource;

import com.musala.dronedelivery.common.ModelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneResource extends BaseResource{
    @NotNull(message = "Serial number required")
    @Length(max = 100,message = "maximum allowed 100 characters for serial number")
    private String serialNumber;
   // @ValueOfEnum(enumClass = ModelType.class ,message = "Invalid model provided")
    private ModelType model;
    @Range(min = 1,max = 500)
    private Integer weightLimit;
    private Integer batteryCapacity;
}
