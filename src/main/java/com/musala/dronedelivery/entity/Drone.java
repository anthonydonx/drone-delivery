/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.entity;

import com.musala.dronedelivery.common.ModelType;
import com.musala.dronedelivery.common.StateType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author anthonydonx
 * SerialNumber :
 * Column(length=100) only to specify table column properties doesn't provide validations
 * size provide validation
 */

@Entity
@Table(name = "drone")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Drone extends BaseEntity {
    @Id
    @GeneratedValue(generator = "prod-generator", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "prod-generator",
            parameters = @Parameter(name = "prefix", value = "drone"),
            strategy = "com.musala.dronedelivery.common.CustomIdGenerator")
    private String id;

    @Column(length = 100, unique = true, nullable = false)
    @Size(max = 100)
    private String serialNumber;

    private ModelType model;

    @Max(500)
    private Integer weightLimit;

    @Max(100)
    private Integer batteryCapacity;

    private StateType state;

    @OneToMany(mappedBy = "drone")
    private List<Delivery> deliveries;

}
