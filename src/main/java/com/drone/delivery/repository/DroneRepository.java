package com.drone.delivery.repository;

import java.time.LocalDateTime;
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
			WHERE DC.end_date > TO_DATE('RRRR-MM-DDThh24:mi:ss',:startDate) 
			ORDER BY RANDOM() 
			LIMIT 1
			""")
	public Mono<Drones> getAvailableDrone(LocalDateTime startDate);

}
