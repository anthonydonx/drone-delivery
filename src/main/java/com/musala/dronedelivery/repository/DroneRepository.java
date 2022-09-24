package com.musala.dronedelivery.repository;


import com.musala.dronedelivery.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author anthonydonx
 * @see org.springframework.data.jpa.repository.JpaRepository
 */
@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {
}