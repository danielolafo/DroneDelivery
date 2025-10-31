package com.drone.delivery.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.drone.delivery.entity.Drones;

//@Repository
public interface DroneRepository extends R2dbcRepository<Drones, UUID> {

}
