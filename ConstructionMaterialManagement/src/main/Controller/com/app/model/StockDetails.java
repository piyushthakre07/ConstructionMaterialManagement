package com.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "stock_details")
public class StockDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stock_details_id", unique = true, nullable = false)
	private long stockDetailsId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id")
	private Items item;
	
	@Column(name = "credit_or_debit", length = 10)
	private String creditOrDebit;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "transaction_type", length = 10)
	private String transactionType;
	
	@Column(name = "created_by")
	private String createdBy;

	@CreationTimestamp
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@UpdateTimestamp
	@Column(name = "modify_date")
	private Date modifyDate;
	
	
}
