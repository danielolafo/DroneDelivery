package com.drone.delivery.mapper;

import com.drone.delivery.dto.ProductDto;
import com.drone.delivery.entity.Products;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-11T13:04:56-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Products toEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Products.ProductsBuilder products = Products.builder();

        products.brand( productDto.getBrand() );
        products.id( productDto.getId() );
        products.name( productDto.getName() );
        products.quantity( productDto.getQuantity() );
        products.stockQuantity( productDto.getStockQuantity() );
        products.unitPrice( productDto.getUnitPrice() );

        return products.build();
    }

    @Override
    public ProductDto toDto(Products products) {
        if ( products == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.brand( products.getBrand() );
        productDto.id( products.getId() );
        productDto.name( products.getName() );
        productDto.quantity( products.getQuantity() );
        productDto.stockQuantity( products.getStockQuantity() );
        productDto.unitPrice( products.getUnitPrice() );

        return productDto.build();
    }
}
