package com.drone.delivery.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.drone.delivery.entity.Dispatches;
import com.drone.delivery.entity.DroneMaintenance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneDto {

	private UUID id;

   
    private String name;

    private String code;
    
    private BigDecimal capacity;

    private BigDecimal batteryAutonomy;

    private Set<DroneMaintenance> droneDroneMaintenances = new HashSet<>();

    private Set<Dispatches> droneDispatches = new HashSet<>();
	
}
