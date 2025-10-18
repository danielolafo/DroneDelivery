package com.drone.delivery.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="dronedeliv.DISPATCHES")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dispatch {
	
	@Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;

    @OneToMany(mappedBy = "dispatch")
    private Set<DispatchCart> dispatchDispatchCarts = new HashSet<>();

    @OneToMany(mappedBy = "dispatch")
    private Set<DispatchComment> dispatchDispatchComments = new HashSet<>();

}
