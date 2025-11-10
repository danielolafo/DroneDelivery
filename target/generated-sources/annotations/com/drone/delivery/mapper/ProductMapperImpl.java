package com.drone.delivery.mapper;

import com.drone.delivery.dto.ProductDto;
import com.drone.delivery.entity.Products;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T18:20:41-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Products toEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Products.ProductsBuilder products = Products.builder();

        products.id( productDto.getId() );
        products.name( productDto.getName() );
        products.quantity( productDto.getQuantity() );
        products.unitPrice( productDto.getUnitPrice() );
        products.stockQuantity( productDto.getStockQuantity() );
        products.brand( productDto.getBrand() );

        return products.build();
    }

    @Override
    public ProductDto toDto(Products products) {
        if ( products == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.id( products.getId() );
        productDto.name( products.getName() );
        productDto.quantity( products.getQuantity() );
        productDto.unitPrice( products.getUnitPrice() );
        productDto.stockQuantity( products.getStockQuantity() );
        productDto.brand( products.getBrand() );

        return productDto.build();
    }
}
