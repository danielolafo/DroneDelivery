package com.drone.delivery.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("drones")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drones {

	@Id
	private UUID id;

	private String name;

	private String code;

	private BigDecimal capacity;

	@Column("battery_autonomy")
	private BigDecimal batteryAutonomy;

	@Transient
	private String status;

}