package com.app.beans;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UnitConverterBean {

	private long baseUnitId;
	private long convertedUnitId; 
	private float value;
}
