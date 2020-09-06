package com.app.module.stockdetails.service;

import java.util.List;

import com.app.beans.StatusBean;
import com.app.beans.StockDetailsBean;

public interface IStockService {

	StatusBean saveOrUpdateStock(StockDetailsBean stockDetailsBean);

	List<StockDetailsBean> getItemsWiseStockDetails();

	StockDetailsBean getStockDetailsByItemId(Long itemId);

}
