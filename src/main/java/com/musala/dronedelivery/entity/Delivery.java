/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "delivery")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Delivery extends BaseEntity{
    @Id
    @GeneratedValue(generator = "medication_delivery-generator",strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "medication_delivery-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "med-del"),
            strategy = "com.musala.dronedelivery.common.CustomIdGenerator")
    private String id;
    @OneToMany(fetch = FetchType.EAGER,targetEntity = Medication.class,mappedBy = "delivery")
    private Set<Medication> medications;
    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

}
