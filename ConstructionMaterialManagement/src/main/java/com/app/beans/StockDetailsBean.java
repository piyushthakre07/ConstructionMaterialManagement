package com.app.beans;

import lombok.Data;


@Data
public class StockDetailsBean {
	
	private long stockDetailsId;

	private ItemsBean item;
	
	private String creditOrDebit;

	private Integer quantity;

	private String transactionType;
	
}
