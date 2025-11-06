package com.drone.delivery.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Drones")
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
