package com.drone.delivery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.drone.delivery.dto.DroneDto;
import com.drone.delivery.entity.Drones;

@Mapper
public interface DroneMapper {
	
	DroneMapper INSTANCE = Mappers.getMapper(DroneMapper.class);
	
	Drones toEntity(DroneDto droneDto);
	
	DroneDto toDto(Drones drone);

}
