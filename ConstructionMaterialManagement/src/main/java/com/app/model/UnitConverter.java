package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "unit_converter_master")
public class UnitConverter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "base_unit_id", unique = true, nullable = false)
	private long baseUnitId;
	
	@Column(name = "converted_unit_id")
	private long convertedUnitId; 
	
	@Column(name = "value")
	private float value;
	
}
