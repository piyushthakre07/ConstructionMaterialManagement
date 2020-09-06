package com.app.beans;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class StockDetailsBean {
	
	public StockDetailsBean() {}
	
	public StockDetailsBean(Long itemQuantity, String itemName) {
		super();
		this.itemQuantity = itemQuantity;
		this.itemName = itemName;
	}

	

	public StockDetailsBean(Long itemQuantity, String itemName, Long itemId, String unitName) {
		super();
		this.itemQuantity = itemQuantity;
		this.unitName = unitName;
		this.itemId = itemId;
		this.itemName = itemName;
	}



	private long stockDetailsId;

	private ItemsBean item;
	
	private String creditOrDebit;

	private Integer quantity;
	
	private Long itemQuantity;
	
	private String unitName;
	
	private String transactionType;
	
	private Long itemId;

	private String itemName;
	
}
