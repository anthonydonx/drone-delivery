/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.dronedelivery.common.ModelType;
import com.musala.dronedelivery.common.StateType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicationDeliveryResponse extends BaseResponse{
    private String id;
    private String serialNumber;
    private ModelType model;
    private Integer weightLimit;
    private Integer batteryCapacity;
    private StateType state;
    private List<MedicationResponse> medications;
    private DroneResponse drone;
}
