package com.drone.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartHistory {
	
	private DispatchDto dispatchDto;
	private DispatchCartDto dispatchCartDto;

}
