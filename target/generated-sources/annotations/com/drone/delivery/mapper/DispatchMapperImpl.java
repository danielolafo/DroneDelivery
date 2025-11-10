package com.drone.delivery.mapper;

import com.drone.delivery.dto.DispatchDto;
import com.drone.delivery.entity.Dispatches;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-10T15:56:22-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class DispatchMapperImpl implements DispatchMapper {

    @Override
    public Dispatches toEntity(DispatchDto dispatchDto) {
        if ( dispatchDto == null ) {
            return null;
        }

        Dispatches.DispatchesBuilder dispatches = Dispatches.builder();

        dispatches.id( dispatchDto.getId() );
        dispatches.origin( dispatchDto.getOrigin() );
        dispatches.target( dispatchDto.getTarget() );
        dispatches.paymentValue( dispatchDto.getPaymentValue() );
        dispatches.paymentMethod( dispatchDto.getPaymentMethod() );
        dispatches.startDate( dispatchDto.getStartDate() );
        dispatches.endDate( dispatchDto.getEndDate() );
        dispatches.kmDone( dispatchDto.getKmDone() );
        dispatches.customerId( dispatchDto.getCustomerId() );
        dispatches.droneId( dispatchDto.getDroneId() );
        dispatches.creationDate( dispatchDto.getCreationDate() );

        return dispatches.build();
    }

    @Override
    public DispatchDto toDto(Dispatches dispatches) {
        if ( dispatches == null ) {
            return null;
        }

        DispatchDto.DispatchDtoBuilder dispatchDto = DispatchDto.builder();

        dispatchDto.id( dispatches.getId() );
        dispatchDto.origin( dispatches.getOrigin() );
        dispatchDto.target( dispatches.getTarget() );
        dispatchDto.paymentValue( dispatches.getPaymentValue() );
        dispatchDto.paymentMethod( dispatches.getPaymentMethod() );
        dispatchDto.startDate( dispatches.getStartDate() );
        dispatchDto.endDate( dispatches.getEndDate() );
        dispatchDto.kmDone( dispatches.getKmDone() );
        dispatchDto.customerId( dispatches.getCustomerId() );
        dispatchDto.droneId( dispatches.getDroneId() );
        dispatchDto.creationDate( dispatches.getCreationDate() );

        return dispatchDto.build();
    }
}
