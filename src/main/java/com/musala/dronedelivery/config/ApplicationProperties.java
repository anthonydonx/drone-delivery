/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@ConfigurationProperties(prefix = "application.medication")
@Configuration
@Data
public class ApplicationProperties {
    private BigDecimal maxMedicationWeight;

    @PostConstruct
    public void defaultValue(){
       if( maxMedicationWeight==null)this.setMaxMedicationWeight(BigDecimal.valueOf(500));
    }
}
