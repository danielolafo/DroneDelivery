package com.drone.delivery.service;

import java.time.LocalDateTime;

import com.drone.delivery.dto.DroneDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DroneService {
	
	public Mono<DroneDto> create(DroneDto dorneDto);
	
	public Mono<DroneDto> getAvailable(LocalDateTime startDate);
	
	
	/**
	 * <p>Get all the drones from the database and all the information about the dispatches assigned to each drone with their 
	 * dispatches times and assign a dispatch for the most avaiable drone for a specific given time.</p>
	 * @return
	 * @author Daniel Orlando López Ochoa
	 */
	public Flux<DroneDto> schedule();

}
