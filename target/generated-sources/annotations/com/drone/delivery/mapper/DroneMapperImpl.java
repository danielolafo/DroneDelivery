package com.drone.delivery.mapper;

import com.drone.delivery.dto.DroneDto;
import com.drone.delivery.entity.Drones;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T18:20:41-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.8 (Eclipse Adoptium)"
)
public class DroneMapperImpl implements DroneMapper {

    @Override
    public Drones toEntity(DroneDto droneDto) {
        if ( droneDto == null ) {
            return null;
        }

        Drones.DronesBuilder drones = Drones.builder();

        drones.id( droneDto.getId() );
        drones.name( droneDto.getName() );
        drones.code( droneDto.getCode() );
        drones.capacity( droneDto.getCapacity() );
        drones.batteryAutonomy( droneDto.getBatteryAutonomy() );

        return drones.build();
    }

    @Override
    public DroneDto toDto(Drones drone) {
        if ( drone == null ) {
            return null;
        }

        DroneDto.DroneDtoBuilder droneDto = DroneDto.builder();

        droneDto.id( drone.getId() );
        droneDto.name( drone.getName() );
        droneDto.code( drone.getCode() );
        droneDto.capacity( drone.getCapacity() );
        droneDto.batteryAutonomy( drone.getBatteryAutonomy() );

        return droneDto.build();
    }
}
