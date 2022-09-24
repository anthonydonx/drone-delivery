package com.musala.dronedelivery.entity;

import com.musala.dronedelivery.common.ModelType;
import com.musala.dronedelivery.common.StateType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * @author anthonydonx
 * SerialNumber :
 * Column(length=100) only to specify table column properties doesn't provide validations
 * size provide validation
 */

@Entity
@Table(name = "drone")
@Data
public class Drone extends BaseEntity{
    @Id
    @GeneratedValue(generator = "prod-generator",strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "prod-generator",
            parameters = @Parameter(name = "prefix", value = "drone"),
            strategy = "com.musala.dronedelivery.common.CustomIdGenerator")
    private String id;

    @Column(length = 100,unique = true,nullable = false)
    @Size(max = 100)
    private String serialNumber;

    private ModelType model;

    @Max(500)
    private Integer weightLimit;

    @Max(100)
    private Integer batteryCapacity;

    private StateType state;

}