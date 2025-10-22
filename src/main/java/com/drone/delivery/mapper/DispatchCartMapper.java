package com.drone.delivery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.drone.delivery.dto.DispatchCartDto;
import com.drone.delivery.entity.DispatchCart;

@Mapper
public interface DispatchCartMapper {
	
	public DispatchCartMapper INSTANCE = Mappers.getMapper(DispatchCartMapper.class);
	
	public DispatchCart toEntity(DispatchCartDto dispatchCartDto);
	
	public DispatchCartDto toDto(DispatchCart dispatchCart);

}
