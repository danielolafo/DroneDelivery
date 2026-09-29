package com.drone.delivery.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("drone_maintenances")
public class DroneMaintenance {

	@Id
	private UUID id;

	@Column("maintenance_date")
	private LocalDate maintenanceDate;

	private BigDecimal cost;

	@Column("drone_id")
	private UUID droneId;

}