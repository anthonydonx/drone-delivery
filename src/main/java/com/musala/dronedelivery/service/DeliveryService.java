/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.service;

import com.musala.dronedelivery.common.MedicationStatus;
import com.musala.dronedelivery.common.StateType;
import com.musala.dronedelivery.config.ApplicationProperties;
import com.musala.dronedelivery.entity.Delivery;
import com.musala.dronedelivery.entity.Drone;
import com.musala.dronedelivery.entity.Medication;
import com.musala.dronedelivery.exception.common.UserInputError;
import com.musala.dronedelivery.repository.DeliveryRepository;
import com.musala.dronedelivery.repository.DroneRepository;
import com.musala.dronedelivery.repository.MedicationRepository;
import com.musala.dronedelivery.response.MedicationDeliveryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DeliveryService {
    public static final String DRONE_NOT_AVAILABLE_AT_THE_MOMENT = "Drone not available at the moment";
    public static final int MIN_BATTERY_LEVEL = 25;
    private final MedicationRepository medicationRepository;
    private final DroneRepository droneRepository;
    private final ApplicationProperties applicationProperties;
    private final DeliveryRepository deliveryRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public MedicationDeliveryResponse createDelivery(Delivery delivery) {
        // find drone information
        Drone drone = droneRepository.findById(delivery.getId()).orElseThrow(() -> new EntityNotFoundException("Unable to find drone id"));

        // drone battery check
        if(drone.getBatteryCapacity()< MIN_BATTERY_LEVEL){
            throw new UserInputError("Battery level is below 25%");
        }
        // check drone is idle
        if (drone.getState() != StateType.IDLE) {
            // return exception if drone not idle
            log.error(DRONE_NOT_AVAILABLE_AT_THE_MOMENT);
            throw new UserInputError(DRONE_NOT_AVAILABLE_AT_THE_MOMENT);
        }
        // Get all medication by ids
        List<String> inputMedicationIds = delivery.getMedications().stream().map(Medication::getId).toList();
        // Fetch medication objects for all ids and filtered status == PENDING
        List<Medication> medications = medicationRepository.findAllById(inputMedicationIds).stream().filter(medication -> medication.getStatus() == MedicationStatus.PENDING).toList();

        // * Fetched medications size should be equal for user provided medication id size,
        //  * if not means one or more provided medication ids are invalid or not in pending status
        if (medications.size() != inputMedicationIds.size())
            throw new UserInputError("One or more medications are not found or not available for load");

        // check medications total value < 500
        double totalWeight = medications.stream().mapToDouble(value -> value.getWeight().doubleValue()).sum();
        if (totalWeight > applicationProperties.getMaxMedicationWeight().doubleValue())
            throw new UserInputError("Provided medications exceeds maximum loadable weight limit");

        // update drone status
        drone.setState(StateType.LOADED);

        // all good till now, time to create delivery and saved in data source
        Delivery deliveryToSave = new Delivery();
        deliveryToSave.setDrone(drone);
        deliveryToSave.setMedications(new HashSet<>(medications));
        deliveryRepository.save(deliveryToSave);
        // update foreign key
        medications.forEach(medication -> {
            medication.setDelivery(deliveryToSave);
            medication.setStatus(MedicationStatus.LOADED);
        });

        // update drone status
        droneRepository.save(drone);

        // for demonstration should update medication with delivery id
        medicationRepository.saveAll(medications);
        return modelMapper.map(deliveryToSave, MedicationDeliveryResponse.class);
    }
}
