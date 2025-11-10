package com.drone.delivery.mapper;

import com.drone.delivery.dto.DispatchCartDto;
import com.drone.delivery.entity.DispatchCart;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T18:20:41-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class DispatchCartMapperImpl implements DispatchCartMapper {

    @Override
    public DispatchCart toEntity(DispatchCartDto dispatchCartDto) {
        if ( dispatchCartDto == null ) {
            return null;
        }

        DispatchCart.DispatchCartBuilder dispatchCart = DispatchCart.builder();

        dispatchCart.id( dispatchCartDto.getId() );
        dispatchCart.unitWeight( dispatchCartDto.getUnitWeight() );
        dispatchCart.quantity( dispatchCartDto.getQuantity() );
        dispatchCart.totalWeight( dispatchCartDto.getTotalWeight() );
        dispatchCart.cost( dispatchCartDto.getCost() );
        dispatchCart.productId( dispatchCartDto.getProductId() );
        dispatchCart.dispatchId( dispatchCartDto.getDispatchId() );

        return dispatchCart.build();
    }

    @Override
    public DispatchCartDto toDto(DispatchCart dispatchCart) {
        if ( dispatchCart == null ) {
            return null;
        }

        DispatchCartDto.DispatchCartDtoBuilder dispatchCartDto = DispatchCartDto.builder();

        dispatchCartDto.id( dispatchCart.getId() );
        dispatchCartDto.unitWeight( dispatchCart.getUnitWeight() );
        dispatchCartDto.quantity( dispatchCart.getQuantity() );
        dispatchCartDto.totalWeight( dispatchCart.getTotalWeight() );
        dispatchCartDto.cost( dispatchCart.getCost() );
        dispatchCartDto.productId( dispatchCart.getProductId() );
        dispatchCartDto.dispatchId( dispatchCart.getDispatchId() );

        return dispatchCartDto.build();
    }
}
