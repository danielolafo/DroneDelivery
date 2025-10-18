package com.drone.delivery.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.drone.delivery.entity.Dispatch;

import reactor.core.publisher.Flux;

@Repository
public interface DispatchRepository extends R2dbcRepository<Dispatch, Integer> {
	
	Flux<Dispatch> findByCustomer_Id(Integer customerId);

}
