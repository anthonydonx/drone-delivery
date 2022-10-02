/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.dronedelivery.common.MedicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicationResponse extends BaseResponse{
    private String id;
    private String name;
    private BigDecimal weight;
    private String code;
    private String imageUrl;
    private MedicationStatus status;
}
