package com.drone.delivery.dto;

import java.math.BigDecimal;

import com.drone.delivery.entity.Products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DispatchCartDto {
	
	private Integer id;

    private BigDecimal unitWeight;

    private BigDecimal quantity;

    private BigDecimal totalWeight;

    private BigDecimal cost;

    private Integer product;

    private Integer dispatchId;
	

}
