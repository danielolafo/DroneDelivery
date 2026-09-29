package com.drone.delivery.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("dispatch_comments")
public class DispatchComments {

	@Id
	private UUID id;

	@Column("dispatch_comment")
	private String dispatchComment;

	@Column("dispatch_rating")
	private BigDecimal dispatchRating;

	@Column("dispatch_id")
	private UUID dispatchId;

}