/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.service;

import com.musala.dronedelivery.common.MedicationStatus;
import com.musala.dronedelivery.config.ApplicationProperties;
import com.musala.dronedelivery.entity.Medication;
import com.musala.dronedelivery.exception.common.UserInputError;
import com.musala.dronedelivery.repository.MedicationRepository;
import com.musala.dronedelivery.response.MedicationResponse;
import com.musala.dronedelivery.util.ImageStorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MedicationService {
    private final ApplicationProperties applicationProperties;
    private final ImageStorageService imageStorageService;
    private MedicationRepository medicationRepository;
    private ModelMapper modelMapper;

    public MedicationResponse createMedication(Medication medication) {
        // check maximum weight limit exceeded
        if (medication.getWeight().compareTo(applicationProperties.getMaxMedicationWeight()) == 1) {
            String message = new StringBuilder().append("Medication weight exceeded. max value is ").append(applicationProperties.getMaxMedicationWeight().toPlainString()).toString();
            log.error(message);
            throw new UserInputError(message);
        }
        // medication status is pending just after creation, waiting for assign drone
        medication.setStatus(MedicationStatus.PENDING);
        medication.setImageUrl(imageStorageService.tempUrl());
        Medication saved = medicationRepository.save(medication);
        return modelMapper.map(saved, MedicationResponse.class);
    }
}
