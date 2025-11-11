package com.drone.delivery.mapper;

import com.drone.delivery.dto.DispatchCartDto;
import com.drone.delivery.entity.DispatchCart;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-11T13:04:56-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class DispatchCartMapperImpl implements DispatchCartMapper {

    @Override
    public DispatchCart toEntity(DispatchCartDto dispatchCartDto) {
        if ( dispatchCartDto == null ) {
            return null;
        }

        DispatchCart.DispatchCartBuilder dispatchCart = DispatchCart.builder();

        dispatchCart.cost( dispatchCartDto.getCost() );
        dispatchCart.dispatchId( dispatchCartDto.getDispatchId() );
        dispatchCart.id( dispatchCartDto.getId() );
        dispatchCart.productId( dispatchCartDto.getProductId() );
        dispatchCart.quantity( dispatchCartDto.getQuantity() );
        dispatchCart.totalWeight( dispatchCartDto.getTotalWeight() );
        dispatchCart.unitWeight( dispatchCartDto.getUnitWeight() );

        return dispatchCart.build();
    }

    @Override
    public DispatchCartDto toDto(DispatchCart dispatchCart) {
        if ( dispatchCart == null ) {
            return null;
        }

        DispatchCartDto.DispatchCartDtoBuilder dispatchCartDto = DispatchCartDto.builder();

        dispatchCartDto.cost( dispatchCart.getCost() );
        dispatchCartDto.dispatchId( dispatchCart.getDispatchId() );
        dispatchCartDto.id( dispatchCart.getId() );
        dispatchCartDto.productId( dispatchCart.getProductId() );
        dispatchCartDto.quantity( dispatchCart.getQuantity() );
        dispatchCartDto.totalWeight( dispatchCart.getTotalWeight() );
        dispatchCartDto.unitWeight( dispatchCart.getUnitWeight() );

        return dispatchCartDto.build();
    }
}
