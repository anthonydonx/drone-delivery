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

package com.musala.dronedelivery.entity;

import com.musala.dronedelivery.common.MedicationStatus;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "medication")
@Data
public class Medication extends BaseEntity{
    @Id
    @GeneratedValue(generator = "medication-generator",strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "medication-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "medication"),
            strategy = "com.musala.dronedelivery.common.CustomIdGenerator")
    private String id;
    private String name;
    private BigDecimal weight;
    private String code;
    private String imageUrl;
    // maintain medication status @MedicationStatus
    private MedicationStatus status;
}
