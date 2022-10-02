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

package com.musala.dronedelivery.controller;

import com.musala.dronedelivery.common.StateType;
import com.musala.dronedelivery.entity.Delivery;
import com.musala.dronedelivery.entity.Drone;
import com.musala.dronedelivery.resource.DeliveryResource;
import com.musala.dronedelivery.resource.DroneResource;
import com.musala.dronedelivery.response.DroneBatteryResponse;
import com.musala.dronedelivery.response.DroneResponse;
import com.musala.dronedelivery.response.MedicationDeliveryResponse;
import com.musala.dronedelivery.service.DeliveryService;
import com.musala.dronedelivery.service.DroneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final ModelMapper modelMapper;

    /**
     * Create medication-delivery
     * Assumptions :
     * 1. Drones able to load multiple medication items for one delivery (max 500)
     * 2. Drone should be idle status for loading medication
     */
    @Operation(description = "Create new medication-delivery")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a medication-delivery"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Unexpected system exception"),
            @ApiResponse(responseCode = "406", description = "Data integrity exception from data source")
    })
    @PostMapping(value = "deliveries")
    public ResponseEntity<MedicationDeliveryResponse> saveDelivery(@Valid @RequestBody DeliveryResource deliveryResource) {
        Delivery delivery = modelMapper.map(deliveryResource, Delivery.class);
        MedicationDeliveryResponse response = deliveryService.createDelivery(delivery);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
