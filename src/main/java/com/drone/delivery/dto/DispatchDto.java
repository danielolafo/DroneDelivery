package com.drone.delivery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.drone.delivery.entity.Drone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispatchDto {
	
	
	private Integer id;

    private String origin;

    
    private String target;

    
    private BigDecimal paymentValue;

 
    private Integer paymentMethod;

    
    private LocalDate startDate;

   
    private LocalDate endDate;

    
    private BigDecimal kmDone;

    private Integer customerId;

  
    private Drone drone;
    
    private Integer droneId;

    
    private List<DispatchCartDto> lstDispatchCartDto = new ArrayList<>();
 
    
    //private Set<DispatchComments> dispatchDispatchComments = new HashSet<>();

}
