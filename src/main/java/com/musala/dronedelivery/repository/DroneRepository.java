/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.repository;


import com.musala.dronedelivery.common.StateType;
import com.musala.dronedelivery.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author anthonydonx
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see DroneBatteryView
 */
@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

    @Query(value = "select d from Drone d where d.state=:state")
    List<Drone> getDroneByState(StateType state);

    // Interface projection

    Optional<DroneBatteryView> findViewById(String droneId);
}