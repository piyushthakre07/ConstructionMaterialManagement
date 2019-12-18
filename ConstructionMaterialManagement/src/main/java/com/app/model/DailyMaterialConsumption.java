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
@Table(name = "daily_material_consumption")
public class DailyMaterialConsumption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "material_consumption_id", unique = true, nullable = false)
	private long materialConsumptionId;
	
	@Column(name = "consumption_date")
	private Date consumptionDate;
	
	@Column(name = "work_type", length = 500)
	private String workType;
	
	@Column(name = "consumption_quantity", length = 500)
	private Integer consumptionQuantity;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "contractor_id")
	private Contractor contractor;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "site_id")
	private Sites sites;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "material_category_id")
	private MaterialCategory materialCategory;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id")
	private Items item;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "approve_by_user")
	private User approveBy;

	@Column(name = "remark", length = 500)
	private String remark;

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
