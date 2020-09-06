package com.app.beans;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UnitsBean {

	private long unitId;

	private String unitName;
	
	private Set<ItemsBean> items;
}
