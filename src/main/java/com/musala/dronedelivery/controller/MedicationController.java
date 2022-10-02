/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.controller;

import com.musala.dronedelivery.entity.Medication;
import com.musala.dronedelivery.exception.common.DataValidationError;
import com.musala.dronedelivery.resource.MedicationResource;
import com.musala.dronedelivery.response.MedicationResponse;
import com.musala.dronedelivery.service.MedicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
@Slf4j
public class MedicationController {
    public static final String VALIDATION_FAILED = "Validation failed due to";
    private final MedicationService medicationService;
    private final ModelMapper modelMapper;

    /**
     * Create new medications
     * Assumptions :
     * 1. Image URL saved in database (better), rather than save image in database as bytes stream
     * NOTE:  assume as use image hosting service like AWS bucket or Google Storage or another cloud service
     * ** Hosting service use as a mockup for demonstration
     * 2. medication status is pending just after creation, waiting for assign drone
     *
     * @param medicationResource
     * @return
     */
    @Operation(description = "Create new medication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Unexpected system exception"),
            @ApiResponse(responseCode = "406", description = "Data integrity exception from data source")
    })
    @PostMapping(value = "medications", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<MedicationResponse> saveMedications(@ModelAttribute @Valid MedicationResource medicationResource, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error(VALIDATION_FAILED + " {}",bindingResult.getFieldError().getField());
            throw new DataValidationError(bindingResult);
        }
        Medication medication = modelMapper.map(medicationResource, Medication.class);
        MedicationResponse medicationResponse=medicationService.createMedication(medication);
        return ResponseEntity.ok(medicationResponse);
    }
}
