package com.drone.delivery.dto;

import java.math.BigDecimal;
import java.util.List;

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

    private Integer productId;

    private Integer dispatchId;
    
    private List<ProductDto> lstProductDtos;
	

}
