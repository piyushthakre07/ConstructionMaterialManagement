package com.app.beans;

import lombok.Data;

@Data
public class ItemsBean {

	private long itemId;

	private String itemName;

	private MaterialCategoryBean materialCategory;

	private UnitsBean unit;

}
