/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.controller;

import com.musala.dronedelivery.common.ModelType;
import com.musala.dronedelivery.common.StateType;
import com.musala.dronedelivery.entity.Drone;
import com.musala.dronedelivery.resource.DroneResource;
import com.musala.dronedelivery.response.DroneResponse;
import com.musala.dronedelivery.service.DroneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class DroneController {
    private final DroneService droneService;
    private final ModelMapper modelMapper;

    /**
     * Create new drones
     * Assumptions :
     * 1. Initial drone state = IDLE (always){ added via business logic}
     * 2. Initial battery capacity = 100 (always) { added via business logic}
     * @param droneResource
     * @return
     */
    @Operation(description = "Create new drone" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a drone"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Unexpected system exception"),
            @ApiResponse(responseCode = "406", description = "Data integrity exception from data source")
    })
    @PostMapping(value = "drones")
    public ResponseEntity<DroneResponse> saveDrones(@Valid @RequestBody DroneResource droneResource){
        Drone drone = modelMapper.map(droneResource, Drone.class);
        DroneResponse droneResponse = droneService.createDrone(drone);
        return ResponseEntity.status(HttpStatus.CREATED).body(droneResponse);
    }
}
