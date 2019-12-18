package com.app.beans;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class PurchaseBean {
	
	private long purchaseId;
	
	private Integer quantity;

	private String remark;

	private ItemsBean item;

	private VendorBean vendor;
	
}
