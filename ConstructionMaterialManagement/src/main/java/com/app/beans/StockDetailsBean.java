package com.app.beans;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class StockDetailsBean {
	
	private long stockDetailsId;

	private ItemsBean item;
	
	private String creditOrDebit;

	private Integer quantity;

	private String transactionType;
	
}
