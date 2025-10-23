package com.drone.delivery.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;

public class Customer {

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

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String businessType;

    //@OneToMany(mappedBy = "customer")
    @Transient
    private List<CustomerLocation> customerCustomerLocations = new ArrayList<>();

    //@OneToMany(mappedBy = "customer")
    @Transient
    private List<Dispatches> customerDispatches = new ArrayList<>();
	
}
