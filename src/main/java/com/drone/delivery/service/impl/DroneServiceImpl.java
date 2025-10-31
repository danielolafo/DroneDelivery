package com.drone.delivery.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.drone.delivery.dto.DroneDto;
import com.drone.delivery.mapper.DroneMapper;
import com.drone.delivery.repository.DroneRepository;
import com.drone.delivery.service.DroneService;

import reactor.core.publisher.Mono;

@Service
public class DroneServiceImpl implements DroneService {
	
	private DroneRepository droneRepository;
	
	public DroneServiceImpl(DroneRepository droneRepository) {
		this.droneRepository = droneRepository;
	}

	@Override
	public Mono<DroneDto> create(DroneDto droneDto) {
		droneDto.setId(UUID.randomUUID());
		return this.droneRepository.save(DroneMapper.INSTANCE.toEntity(droneDto)).map(d->
			DroneMapper.INSTANCE.toDto(d)
		);
	}

}
