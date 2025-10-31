package com.drone.delivery.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.drone.delivery.entity.Products;

import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends R2dbcRepository<Products, UUID> {

	public Flux<Products> findByNameAndBrand(String name, String brand);
	
}
