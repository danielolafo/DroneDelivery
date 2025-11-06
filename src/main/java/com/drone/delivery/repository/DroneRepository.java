package com.drone.delivery.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.drone.delivery.entity.Drones;

import reactor.core.publisher.Mono;

//@Repository
public interface DroneRepository extends R2dbcRepository<Drones, UUID> {
	
	@Query(value="""
			SELECT D.* FROM DRONES D 
			LEFT JOIN DISPATCHES DC ON D.ID = DC.DRONE_ID 
			WHERE DC.DRONE_ID = NULL AND DC.END_DATE IS NULL 
			ORDER BY RANDOM() 
			LIMIT 1
			""")
	public Mono<Drones> findAvailableDrone();

}
