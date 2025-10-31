package com.drone.delivery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.drone.delivery.dto.ProductDto;
import com.drone.delivery.entity.Products;

@Mapper
public interface ProductMapper {
	
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	Products toEntity(ProductDto productDto);
	
	ProductDto toDto(Products products);

}
