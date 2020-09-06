package com.app.beans;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ItemsBean {

	private long itemId;

	private String itemName;

	private MaterialCategoryBean materialCategory;
	
	private List<MaterialCategoryBean> items;

	private UnitsBean unit;

}
