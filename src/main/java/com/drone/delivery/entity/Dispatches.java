package com.drone.delivery.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("dispatches")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dispatches {

	@Id
	private UUID id;

	private String origin;

	private String target;

	@Column("payment_value")
	private BigDecimal paymentValue;

	@Column("payment_method")
	private Integer paymentMethod;

	@Column("start_date")
	private LocalDateTime startDate;

	@Column("end_date")
	private LocalDateTime endDate;

	@Column("km_done")
	private BigDecimal kmDone;

	@Column("customer_id")
	private UUID customerId;

	@Column("drone_id")
	private UUID droneId;

	@Column("creation_date")
	private LocalDate creationDate;

}