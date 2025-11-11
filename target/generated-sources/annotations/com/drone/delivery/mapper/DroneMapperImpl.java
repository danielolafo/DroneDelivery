package com.drone.delivery.mapper;

import com.drone.delivery.dto.DroneDto;
import com.drone.delivery.entity.Drones;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-11T13:04:56-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class DroneMapperImpl implements DroneMapper {

    @Override
    public Drones toEntity(DroneDto droneDto) {
        if ( droneDto == null ) {
            return null;
        }

        Drones.DronesBuilder drones = Drones.builder();

        drones.batteryAutonomy( droneDto.getBatteryAutonomy() );
        drones.capacity( droneDto.getCapacity() );
        drones.code( droneDto.getCode() );
        drones.id( droneDto.getId() );
        drones.name( droneDto.getName() );

        return drones.build();
    }

    @Override
    public DroneDto toDto(Drones drone) {
        if ( drone == null ) {
            return null;
        }

        DroneDto.DroneDtoBuilder droneDto = DroneDto.builder();

        droneDto.batteryAutonomy( drone.getBatteryAutonomy() );
        droneDto.capacity( drone.getCapacity() );
        droneDto.code( drone.getCode() );
        droneDto.id( drone.getId() );
        droneDto.name( drone.getName() );

        return droneDto.build();
    }
}
