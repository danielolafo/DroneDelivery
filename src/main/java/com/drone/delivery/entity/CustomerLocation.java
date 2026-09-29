package com.drone.delivery.entity;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("customer_locations")
public class CustomerLocation {

	@Id
	private UUID id;

	private String address;

	private Integer city;

	@Column("customer_id")
	private UUID customerId;

}