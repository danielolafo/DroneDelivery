package com.drone.delivery.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="dispatches")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dispatches {
	
	@Id
    @Column(nullable = false, updatable = false)
//    @SequenceGenerator(
//            name = "dispatches_seq1",
//            sequenceName = "dispatches_seq1",
//            allocationSize = 1,
//            initialValue = 10000
//    )
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Integer id;

    @Column(length = 100)
    private String origin;

    @Column(length = 100)
    private String target;

    @Column(precision = 20, scale = 2)
    private BigDecimal paymentValue;

    @Column(nullable = false)
    private Integer paymentMethod;

    @Column(nullable = false)
    
    private LocalDate startDate;

    @Column
    
    private LocalDate endDate;

    @Column(precision = 3, scale = 2)
    private BigDecimal kmDone;
    
    @Column(name="customer_id")
    private Integer customerId;
    
    @Column(name="drone_id")
    private Integer droneId;
    
    @Column(name="creation_date")
    private LocalDate creationDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id", nullable = false)
//    private Customer customer;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "drone_id", nullable = false)
//    private Drone drone;
//
    
    /*
    @Transient
    private List<DispatchCart> dispatchDispatchCarts = new ArrayList<>();
//
    @Transient
    private List<DispatchComments> dispatchDispatchComments = new ArrayList<>();
    */

}
