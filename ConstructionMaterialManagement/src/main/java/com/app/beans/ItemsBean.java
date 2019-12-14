package com.app.beans;

import lombok.Data;

@Data
public class ItemsBean {

	private long itemId;

	private String itemName;

	private MaterialCategaryBean materialCategary;

	private UnitsBean unit;

}
