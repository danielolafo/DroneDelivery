package com.drone.delivery.service;

import com.drone.delivery.dto.DroneDto;

import reactor.core.publisher.Mono;

public interface DroneService {
	
	public Mono<DroneDto> create(DroneDto dorneDto);
	
	public Mono<DroneDto> getAvailable();

}
