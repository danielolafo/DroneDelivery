package com.drone.delivery.repository;

import java.time.LocalDate;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.drone.delivery.entity.Dispatches;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Repository
public interface DispatchRepository extends R2dbcRepository<Dispatches, Integer> {
	
	Flux<Dispatches> findByCustomerId(Integer customerId);
	
	public Mono<Dispatches> findByOriginAndTargetAndStartDate(String origin, String target, LocalDate startDate);
	
	public Mono<Dispatches> findByOriginAndTarget(String origin, String target);

}
