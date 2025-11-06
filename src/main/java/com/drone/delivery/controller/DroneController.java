package com.drone.delivery.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drone.delivery.dto.DroneDto;
import com.drone.delivery.service.DroneService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/drone")
public class DroneController {
	
	private DroneService droneService;
	
	public DroneController(DroneService droneService) {
		this.droneService = droneService;
	}
	
	@PostMapping()
	public Mono<DroneDto> create(@Valid @RequestBody DroneDto droneDto){
		return this.droneService.create(droneDto);
	}
	

}
