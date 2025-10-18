package com.drone.delivery.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
public class Drone {
	
	@Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String code;

    @Column(precision = 3, scale = 2)
    private BigDecimal capacity;

    @Column(precision = 3, scale = 2)
    private BigDecimal batteryAutonomy;

    @OneToMany(mappedBy = "drone")
    private Set<DroneMaintenance> droneDroneMaintenances = new HashSet<>();

    @OneToMany(mappedBy = "drone")
    private Set<Dispatch> droneDispatches = new HashSet<>();

}
