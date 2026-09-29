package com.drone.delivery.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("customers")
public class Customer {

	@Id
	private UUID id;

	private String name;

	@Column("business_type")
	private String businessType;

	@Transient
	private List<CustomerLocation> customerCustomerLocations = new ArrayList<>();

	@Transient
	private List<Dispatches> customerDispatches = new ArrayList<>();

}