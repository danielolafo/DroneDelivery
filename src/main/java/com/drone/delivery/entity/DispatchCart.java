package com.drone.delivery.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="dispatch_cart")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DispatchCart {
	
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

    @Column(precision = 3, scale = 2)
    private BigDecimal unitWeight;

    @Column(precision = 3, scale = 2)
    private BigDecimal quantity;

    @Column(precision = 3, scale = 2)
    private BigDecimal totalWeight;

    @Column(precision = 20, scale = 2)
    private BigDecimal cost;
    
    @Column(name="product_id")
    private Integer productId;
    
    @Column(name="dispatch_id")
    private Integer dispatchId;

//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private Products productId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "dispatch_id", nullable = false)
//    private Dispatches dispatchId;

}
