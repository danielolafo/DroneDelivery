package com.drone.delivery.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.drone.delivery.entity.Products;

@Repository
public interface ProductRepository extends R2dbcRepository<Products, Integer> {

}
