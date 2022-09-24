package com.musala.dronedelivery.service;

import com.musala.dronedelivery.common.StateType;
import com.musala.dronedelivery.entity.Drone;
import com.musala.dronedelivery.repository.DroneRepository;
import com.musala.dronedelivery.response.DroneResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DroneService {
    private final DroneRepository droneRepository;
    private final ModelMapper modelMapper;

    public DroneResponse createDrone(Drone drone) {
        drone.setBatteryCapacity(100);
        drone.setState(StateType.IDLE);
        Drone drone1 = droneRepository.save(drone);
        return  modelMapper.map(drone1,DroneResponse.class);

    }
}
