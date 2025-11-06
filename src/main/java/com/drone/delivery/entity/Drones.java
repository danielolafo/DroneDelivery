package com.drone.delivery.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name="Drones")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class Drones {
	
	@Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String code;

    @Column(precision = 3, scale = 2)
    private BigDecimal capacity;

    @Column(precision = 3, scale = 2)
    private BigDecimal batteryAutonomy;
    
    @Column(nullable=false)
    private String status;

    /*
    //@OneToMany(mappedBy = "drone")
    @Transient
    private Set<DroneMaintenance> droneDroneMaintenances = new HashSet<>();

    //@OneToMany(mappedBy = "drone")
    @Transient
    private Set<Dispatches> droneDispatches = new HashSet<>();
    */

}
