package com.musala.dronedelivery.service;

import com.musala.dronedelivery.common.StateType;
import com.musala.dronedelivery.entity.Drone;
import com.musala.dronedelivery.repository.DroneBatteryView;
import com.musala.dronedelivery.repository.DroneRepository;
import com.musala.dronedelivery.response.DroneBattery;
import com.musala.dronedelivery.response.DroneBatteryResponse;
import com.musala.dronedelivery.response.DroneResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DroneService {
    public static final String ID = "id";
    private final DroneRepository droneRepository;
    private final ModelMapper modelMapper;

    public DroneResponse createDrone(Drone drone) {
        // set drone initial values
        drone.setBatteryCapacity(100);
        drone.setState(StateType.IDLE);
        log.debug("Drone saved as {}",drone);
        Drone drone1 = droneRepository.save(drone);
        log.info("Drone saved successfully");
        // convert drone obj to @DroneResponse
        return modelMapper.map(drone1, DroneResponse.class);

    }

    /**
     * Get drones by given state
     * 1. get drones by state
     * 2. map list of matching drone to DroneResponse
     *
     * @param state
     * @return
     */
    public List<DroneResponse> getDronesByStatus(StateType state) {
        // get drone by state
        List<Drone> droneByState = droneRepository.getDroneByState(state);
        if(droneByState.isEmpty()) log.warn("Drones not found with given state");
        // convert drone objects to Drone response
        return droneByState.stream().map(drone -> modelMapper.map(drone, DroneResponse.class))
                .collect(Collectors.toList());

    }

    /**
     * Get battery level by drone id
     * Note :
     * Rather than selecting all data from table, use projection to get only specific column
     *
     * @param droneId
     * @return
     */
    public DroneBatteryResponse getBatteryLevelByDroneId(String droneId) {
        // get percentage from datasource
        DroneBatteryView viewById = droneRepository.findViewById(droneId).orElseThrow(() -> new EntityNotFoundException("Unable to find drone id"));
        log.info("fetch drone battery info successfully ");
        // map and return as a @DroneBatteryResponse
        return DroneBatteryResponse.builder().batteryCapacity(viewById.getBatteryCapacity()).build();
    }

    /**
     * get all drones for log available battery percentage
     *
     * @return
     */
    public List<Drone> getAllDronesBatteryLevel(){
        return  droneRepository.findAll(Sort.by(ID)).stream().toList();

    }
}
