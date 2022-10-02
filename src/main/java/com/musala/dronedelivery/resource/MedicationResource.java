/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.resource;

import com.musala.dronedelivery.config.ApplicationProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationResource extends BaseResource{

    // allowed only letters, numbers, ‘-‘, ‘_’
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$",message = "Invalid medication name")
    private String name;
    // maximum value validation as a property value in service
    @Range(min = 0)
    private BigDecimal weight;
    //allowed only upper case letters, underscore and numbers
    @Pattern(regexp = "^[A-Z0-9_.-]*$",message = "Invalid medication code")
    private String code;
    private MultipartFile image;
}
