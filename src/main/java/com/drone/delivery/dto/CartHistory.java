package com.drone.delivery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.drone.delivery.entity.Customer;
import com.drone.delivery.entity.DispatchComments;
import com.drone.delivery.entity.Drone;

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
