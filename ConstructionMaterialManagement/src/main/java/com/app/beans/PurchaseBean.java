package com.app.beans;

import lombok.Data;


@Data
public class PurchaseBean {
	
	private long purchaseId;
	
	private Integer quantity;

	private String remark;

	private ItemsBean item;

	private VendorBean vendor;
	
}
