package com.drone.delivery.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message="The cart must be attached to a dispatch")
    private Integer dispatchId;
    
    @NotEmpty(message="The cart must have at least one product")
    @Size(min=1, message="The cart must have at least one product")
    private List<ProductDto> lstProductDtos;
	

}
