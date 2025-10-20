package com.drone.delivery.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	private Integer id;

    private String name;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private BigDecimal stockQuantity;

    private String brand;

    //private Set<DispatchCartDto> productDispatchCarts = new HashSet<>();


}
