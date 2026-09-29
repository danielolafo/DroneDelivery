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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("products")
public class Products {

	@Id
	private UUID id;

	private String name;

	private BigDecimal quantity;

	@Column("unit_price")
	private BigDecimal unitPrice;

	@Column("stock_quantity")
	private BigDecimal stockQuantity;

	private String brand;

}