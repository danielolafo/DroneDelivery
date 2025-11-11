package com.drone.delivery.mapper;

import com.drone.delivery.dto.DispatchDto;
import com.drone.delivery.entity.Dispatches;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-11T13:04:56-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class DispatchMapperImpl implements DispatchMapper {

    @Override
    public Dispatches toEntity(DispatchDto dispatchDto) {
        if ( dispatchDto == null ) {
            return null;
        }

        Dispatches.DispatchesBuilder dispatches = Dispatches.builder();

        dispatches.creationDate( dispatchDto.getCreationDate() );
        dispatches.customerId( dispatchDto.getCustomerId() );
        dispatches.droneId( dispatchDto.getDroneId() );
        dispatches.endDate( dispatchDto.getEndDate() );
        dispatches.id( dispatchDto.getId() );
        dispatches.kmDone( dispatchDto.getKmDone() );
        dispatches.origin( dispatchDto.getOrigin() );
        dispatches.paymentMethod( dispatchDto.getPaymentMethod() );
        dispatches.paymentValue( dispatchDto.getPaymentValue() );
        dispatches.startDate( dispatchDto.getStartDate() );
        dispatches.target( dispatchDto.getTarget() );

        return dispatches.build();
    }

    @Override
    public DispatchDto toDto(Dispatches dispatches) {
        if ( dispatches == null ) {
            return null;
        }

        DispatchDto.DispatchDtoBuilder dispatchDto = DispatchDto.builder();

        dispatchDto.creationDate( dispatches.getCreationDate() );
        dispatchDto.customerId( dispatches.getCustomerId() );
        dispatchDto.droneId( dispatches.getDroneId() );
        dispatchDto.endDate( dispatches.getEndDate() );
        dispatchDto.id( dispatches.getId() );
        dispatchDto.kmDone( dispatches.getKmDone() );
        dispatchDto.origin( dispatches.getOrigin() );
        dispatchDto.paymentMethod( dispatches.getPaymentMethod() );
        dispatchDto.paymentValue( dispatches.getPaymentValue() );
        dispatchDto.startDate( dispatches.getStartDate() );
        dispatchDto.target( dispatches.getTarget() );

        return dispatchDto.build();
    }
}
