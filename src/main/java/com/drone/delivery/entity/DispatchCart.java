package com.drone.delivery.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("dispatch_cart")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DispatchCart {

	@Id
	private UUID id;

	@Column("unit_weight")
	private BigDecimal unitWeight;

	private BigDecimal quantity;

	@Column("total_weight")
	private BigDecimal totalWeight;

	private BigDecimal cost;

	@Column("product_id")
	private UUID productId;

	@Column("dispatch_id")
	private UUID dispatchId;

}