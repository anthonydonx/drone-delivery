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
import com.musala.dronedelivery.entity.Drone;
import com.musala.dronedelivery.resource.DroneResource;
import com.musala.dronedelivery.response.DroneBatteryResponse;
import com.musala.dronedelivery.response.DroneResponse;
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
    /**
     * Get drones by status (rather than get only available drones)
     * Can use for checking available drones for loading as requirement document
     * This endpoint use to get drones by its status rather than only available drones
     *
     * Assumptions :
     * 1. Handled only 10  drones so that assumed  pagination and sorting NOT required
     */
    @GetMapping(value = "drones/state/{state}")
    public ResponseEntity<List<DroneResponse>>dronesByStatus(@PathVariable StateType state){
        List<DroneResponse> droneResponses=droneService.getDronesByStatus(state);
        return ResponseEntity.ok(droneResponses);
    }

    /**
     * get drone better level by drone Id
     *
     * Assumption :-
     * 1. Get only battery percentage for specific drone id via @projection view rather than fetch all information from data source
     * @param droneId
     * @return
     */
    @GetMapping(value = "drones/battery/{droneId}")
    public ResponseEntity<DroneBatteryResponse>dronesBattery(@PathVariable String droneId){
        DroneBatteryResponse droneBatteryResponse=droneService.getBatteryLevelByDroneId(droneId);
       return ResponseEntity.ok(droneBatteryResponse);
    }
}
