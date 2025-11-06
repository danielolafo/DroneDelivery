package com.drone.delivery.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.drone.delivery.entity.Dispatches;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DispatchRepository extends R2dbcRepository<Dispatches, UUID> {
	
	Flux<Dispatches> findByCustomerId(UUID customerId);
	
	public Mono<Dispatches> findByOriginAndTargetAndStartDate(String origin, String target, LocalDateTime startDate);
	
	public Mono<Dispatches> findByOriginAndTarget(String origin, String target);
	

}
