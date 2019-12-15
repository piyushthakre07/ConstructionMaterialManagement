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

import lombok.Data;

@Entity
@Data
@Table(name = "material_category_master")
public class MaterialCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "material_category_id", unique = true, nullable = false)
	private long materialCategoryId;

	@Column(name = "material_category_name", length = 50)
	private String materialCategoryName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialCategory")
	private Set<Items> items;
	
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
