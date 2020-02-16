package com.app.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
@Table(name = "contractors_master")
public class Contractor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contractor_id", unique = true, nullable = false)
	private long contractorId;

	@Column(name = "contractor_name", length = 50)
	private String contractorName;
	
	@Column(name = "address", length = 500)
	private String address;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "contractor")
	private Set<Sites> sites;
	
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
