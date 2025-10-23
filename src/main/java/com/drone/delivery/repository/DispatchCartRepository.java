package com.drone.delivery.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.drone.delivery.entity.DispatchCart;

import reactor.core.publisher.Flux;

@Repository
public interface DispatchCartRepository extends R2dbcRepository<DispatchCart, Integer> {
	
	public Flux<DispatchCart> findByDispatchId(Integer dispatchId);

}
