package com.drone.delivery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.drone.delivery.entity.Drones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispatchDto {
	
	
	private UUID id;

    private String origin;

    
    private String target;

    
    private BigDecimal paymentValue;

 
    private Integer paymentMethod;

    
    private LocalDateTime startDate;

   
    private LocalDateTime endDate;

    
    private BigDecimal kmDone;

    private UUID customerId;

  
    private Drones drone;
    
    private UUID droneId;

    
    private List<DispatchCartDto> lstDispatchCartDto = new ArrayList<>();
 
    
    //private Set<DispatchComments> dispatchDispatchComments = new HashSet<>();
    
    private LocalDate creationDate;
    private UUID unid;

}
