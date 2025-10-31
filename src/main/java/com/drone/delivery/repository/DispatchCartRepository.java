package com.drone.delivery.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.drone.delivery.entity.DispatchCart;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DispatchCartRepository extends R2dbcRepository<DispatchCart, UUID> {
	
	public Flux<DispatchCart> findByDispatchId(UUID dispatchId);
	
	public Mono<DispatchCart> findByDispatchIdAndProductId(UUID dispatchId, UUID productId);

}
