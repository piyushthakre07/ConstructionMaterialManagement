package com.app.beans;

import java.util.Set;

import lombok.Data;

@Data
public class UnitsBean {

	private long unitId;

	private String unitName;
	
	private Set<ItemsBean> items;
}
