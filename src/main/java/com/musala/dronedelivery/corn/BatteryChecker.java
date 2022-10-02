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

package com.musala.dronedelivery.corn;

import com.musala.dronedelivery.entity.Drone;
import com.musala.dronedelivery.response.DroneBattery;
import com.musala.dronedelivery.service.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@EnableAsync
public class BatteryChecker {
  final
  DroneService droneService;
  final
  ModelMapper modelMapper;

    public BatteryChecker(DroneService droneService, ModelMapper modelMapper) {
        this.droneService = droneService;
        this.modelMapper = modelMapper;
    }

    /**
     * want to support parallel behavior in scheduled tasks, we need to add the @Async annotation
     * asynchronous task will be invoked each second, even if the previous task isn't done
     */
    @Async
    @Scheduled(fixedRateString = "${application.battery-checker.fixed-delay}")
    public void batteryLogger() {
        // get all drones battery percentage
        List<Drone> allDronesBatteryLevel = droneService.getAllDronesBatteryLevel();
        // convert to battery level obj
        List<DroneBattery> droneBatteries = allDronesBatteryLevel.stream().map(drone -> modelMapper.map(drone, DroneBattery.class)).collect(Collectors.toList());
        //log
        log.info(droneBatteries.toString());
    }
}
