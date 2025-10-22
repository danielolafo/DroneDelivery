package com.drone.delivery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.drone.delivery.dto.DispatchDto;
import com.drone.delivery.entity.Dispatches;

@Mapper
public interface DispatchMapper {
	
	public DispatchMapper INSTANCE = Mappers.getMapper(DispatchMapper.class);
	
	public Dispatches toEntity(DispatchDto dispatchDto);
	
	public DispatchDto toDto(Dispatches dispatches);

}
