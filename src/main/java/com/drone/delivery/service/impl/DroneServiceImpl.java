package com.drone.delivery.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.drone.delivery.dto.DroneDto;
import com.drone.delivery.mapper.DroneMapper;
import com.drone.delivery.repository.DroneRepository;
import com.drone.delivery.service.DroneService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
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

	@Override
	public Mono<DroneDto> getAvailable(LocalDateTime startDate) {
		log.info("{} {}", Thread.currentThread().getStackTrace()[1].getMethodName(), startDate);
		return this.droneRepository.getAvailableDrone()
		.map(dr-> {
			log.info("drone {}", dr);
			return DroneMapper.INSTANCE.toDto(dr);
		})
		.switchIfEmpty(this.noResult()).log("No available drones");
	}

	@Override
	public Flux<DroneDto> schedule() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Mono<DroneDto> noResult(){
		log.info("{} ", Thread.currentThread().getStackTrace()[1].getMethodName());
		return Mono.just(DroneDto.builder().build());
	}

}
