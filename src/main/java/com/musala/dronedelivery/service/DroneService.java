package com.musala.dronedelivery.service;

import com.musala.dronedelivery.repository.DroneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DroneService {
    private final DroneRepository droneRepository;

}
