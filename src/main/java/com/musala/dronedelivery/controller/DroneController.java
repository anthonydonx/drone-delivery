/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.controller;

import com.musala.dronedelivery.common.ModelType;
import com.musala.dronedelivery.common.StateType;
import com.musala.dronedelivery.resource.DroneResource;
import com.musala.dronedelivery.response.DroneResponse;
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
public class DroneController {
    @PostMapping(value = "drones")
    public ResponseEntity<DroneResponse> saveDrones(@Valid @RequestBody DroneResource droneResource){
        return ResponseEntity.ok(DroneResponse.builder().build());
    }
}
